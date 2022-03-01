import React, { Component } from "react";
import swal from "sweetalert";
import Pagination from "react-js-pagination";
import http from "../../00Services/httpService";
import apiEndpoint from "../../00Services/endpoint";
import SearchBox from "../../05ReusableComponents/SeachBox";
import UserListTable from "./UsersListTable";

export default class UsersListContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      naudotojai: [],
      pageSize: 10, // FUNCTIONALITY NOT YET IMPLEMENTED
      currentPage: 1,
      totalPages: 0,
      totalElements: 0,
      numberOfElements: 0,
      passwordResetRequests: [],
      searchQuery: ""
    };
  }
  componentDidMount() {
    this.getUserInfo(this.state.currentPage, this.state.pageSize, this.state.searchQuery);
    this.handleSearch = this.handleSearch.bind(this);
  }

  getUserInfo(page, size, filter) {
    var uri = `${apiEndpoint}/api/users/admin/allusers?page=${page - 1}&size=${size}&filter=${filter}`;

    http
      .get(`${apiEndpoint}/passwordresetrequests/getAllRequests`)
      .then((response) => {
        this.setState({
          ...this.state,
          passwordResetRequests: response.data,
        });
      })
      .catch(() => { });

    http
      .get(uri)
      .then((response) => {
        this.setState({
          naudotojai: this.mapToViewModel(
            response.data.content,
            this.state.passwordResetRequests
          ),
          totalPages: response.data.totalPages,
          totalElements: response.data.totalElements,
          numberOfElements: response.data.numberOfElements,
          currentPage: response.data.number + 1,
        });
      })
      .catch(() => { });
  }

  handleSearch(e) {
    this.setState({ searchQuery: e.currentTarget.value });
    this.getUserInfo(1, this.state.pageSize, e.currentTarget.value);
  }

  checkIfUserIsRequestingPassword(UID, passList) {
    return passList.some((element) => element.userId === UID);
  }

  mapToViewModel(data, passList) {
    const naudotojai = data.map((user) => ({
      id: user.userId,
      role: user.role,
      name: user.name,
      surname: user.surname,
      username: user.username,
      isRequestingPasswordReset: this.checkIfUserIsRequestingPassword(
        user.userId,
        passList
      ),
    }));

    return naudotojai;
  }

  handleDelete = (item) => {
    swal({
      text: "Ar tikrai norite ištrinti naudotoją?",
      buttons: ["Ne", "Taip"],
      dangerMode: true,
    }).then((actionConfirmed) => {
      if (actionConfirmed) {
        const { currentPage, numberOfElements } = this.state;
        const page = numberOfElements === 1 ? currentPage - 1 : currentPage;
        http
          .delete(`${apiEndpoint}/api/users/admin/delete/${item.username}`)
          .then((response) => {
            swal({
              text: response.data,
              button: "Gerai",
            })
          }).then(() => {
            this.getUserInfo(page, this.state.pageSize, this.state.searchQuery);
          }).catch(() => { });
      }
    });
  };

  handleRestorePassword = (item) => {
    const username = item.username;

    swal({
      text: "Ar tikrai norite atkurti pirminį slaptažodį?",
      buttons: ["Ne", "Taip"],
      dangerMode: true,
    }).then((actionConfirmed) => {
      if (actionConfirmed) {
        http
          .put(`${apiEndpoint}/api/users/admin/password/${username}`)
          .then((response) => {
            const { currentPage, numberOfElements } = this.state;
            const page = numberOfElements === 1 ? currentPage - 1 : currentPage;
            this.getUserInfo(page);
            swal({
              text: response.data,
              button: "Gerai",
            });
          })
          .catch(() => { });
      }
    });
  };

  handlePageChange = (page) => {
    this.setState({ currentPage: page });
    this.getUserInfo(page, this.state.pageSize, this.state.searchQuery);
  };

  render() {
    return (
      <React.Fragment>
        <SearchBox
          value={this.state.searchQuery}
          onSearch={this.handleSearch}
          placeholder={"Search by username..."}
        />

        <UserListTable
          naudotojai={this.state.naudotojai}
          onDelete={this.handleDelete}
          onRestorePassword={this.handleRestorePassword}
        />

        {this.state.totalPages > 1 &&
          <div className="d-flex justify-content-center">
            <Pagination
              itemClass="page-item"
              linkClass="page-link"
              activePage={this.state.currentPage}
              itemsCountPerPage={this.state.pageSize}
              totalItemsCount={this.state.totalElements}
              pageRangeDisplayed={15}
              onChange={this.handlePageChange.bind(this)}
            />
          </div>
        }
      </React.Fragment>
    );
  }
}