import React, { Component } from 'react';
import swal from 'sweetalert';

import http from '../10Services/httpService';
import apiEndpoint from '../10Services/endpoint';
import '../../App.css';

import UserListTable from './UserListTable';
import Pagination from './../08CommonComponents/Pagination';
import { paginate } from './../08CommonComponents/paginate';

export class UserListContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            naudotojai: [],
            pageSize: 10,
            currentPage: 1
        }
    }
    componentDidMount() {
        http
            .get(`${apiEndpoint}/api/users/admin/allusers`)
            .then((response) => {
                this.setState({ naudotojai: this.mapToViewModel(response.data) });

            }).catch(error => {
                console.log("Naudotojai container error", error.response);
                if (error && error.response.status === 401)  
                    swal({
                        title: "Puslapis pasiekiamas tik administratoriaus teises turintiems naudotojams",                        
                        button: "Gerai"
                    }); 
               // this.props.history.replace("/home");
            }
            );

    }

    mapToViewModel(data) {
       
        const naudotojai = data.map(user=>({
            id: user.userId,
            username: user.username,
            role: user.role

        }));       
       
        return  naudotojai;        
    };

    handleDelete = (item) => {
        const username = item.username;
        console.log("Trinti naudotoja", username);

        http
            .delete(`${apiEndpoint}/api/users/admin/delete/${username}`)
            .then((response) => {
                swal({                   
                    text: response.data,
                    button: "Gerai"
                });
                http
                    .get(`${apiEndpoint}/api/users/admin/allusers`)
                    .then((response) => {
                        this.setState({ naudotojai: response.data });

                    });
            }).catch(error => {
                if (error && error.response.status > 400 && error.response.status < 500) 
                swal({                   
                    title: "Veiksmas neleidžiamas",   
                    button: "Gerai"
                });

            });
    }

    handleRestorePassword = (item) => {
        const username = item.username;
        console.log("Atstatyti slaptazodi naudotojui", username);

        http
            .put(`${apiEndpoint}/api/users/admin/password/${username}`)
            .then((response) => {
                swal({                   
                    text: response.data,
                    button: "Gerai"
                });
            }).catch(error => {
                if (error && error.response.status > 400 && error.response.status < 500) 
                swal({                   
                    title: "Veiksmas neleidžiamas",   
                    button: "Gerai"
                });

            }
            );

    }

    handlePageChange = (page) => {
        this.setState({ currentPage: page });
    };


    getPageData = () => {
        const {
            pageSize,
            currentPage,
            naudotojai: allData
        } = this.state;

        const naudotojai = paginate(allData, currentPage, pageSize);

        return { totalCount: allData.length, naudotojai: naudotojai }
    }

    render() {

        const { totalCount, naudotojai } = this.getPageData();

        return (
            <div >
                
                <UserListTable
                    naudotojai={naudotojai}
                    onDelete={this.handleDelete}
                    onRestorePassword={this.handleRestorePassword}
                />

                <Pagination
                    itemsCount={totalCount}
                    pageSize={this.state.pageSize}
                    onPageChange={this.handlePageChange}
                    currentPage={this.state.currentPage}
                />


            </div>
        )
    }
}

export default UserListContainer