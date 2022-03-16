import React from 'react';
import { NavLink } from 'react-router-dom';

import logo from '../../../images/logo.png';
import '../../../App.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faQuestionCircle } from '@fortawesome/free-solid-svg-icons'

import instructionsPdf from '../../../documents/VMS_VDIS_naudotojo_gidas.pdf';

import Logout from '../../05ReusableComponents/Logout';

export default function UserNavBar(props) {
  return (
    <div className="pb-4" >
      <nav className="navbar navbar-expand-xxl py-4 navbar-light bg-light">

        <div className="container">

          <NavLink className="navbar-brand" to={"/home"}>
            <img className="nav-img" src={logo} alt="logotipas" loading="lazy" />
          </NavLink>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ms-auto ">

              <li className="nav-item me-1">
                <NavLink className="nav-link" id="navUserNewApplication" to={"/prasymai/naujas"}>Sukurti prašymą</NavLink>
              </li>

              <li className="nav-item me-1">
                <NavLink className="nav-link" id="navUserMyApplications" to={"/prasymai"}>Mano prašymai</NavLink>
              </li>

              <li className="nav-item me-1">
                <NavLink className="nav-link" id="navUserMyApplications" to={"/zemelapis"}>Darželių žemėlapis</NavLink>
              </li>

              <li className="nav-item me-1">
                <NavLink className="nav-link" id="navUserDocuments" to={"/pazymos"}>Mano pažymos</NavLink>
              </li>

              <li className="nav-item me-1">
                <NavLink className="nav-link" id="navUserApplicationStats" to={"/statistika"}>Prašymų statistika</NavLink>
              </li>

              <li className="nav-item me-1">
                <NavLink className="nav-link" id="navUserMyAccount" to={"/profilis/atnaujinti"}>Mano paskyra</NavLink>
              </li>

              <li className="nav-item me-1">
                <a className="nav-link"
                  id="navInstructions"
                  target="_blank"
                  rel="noopener noreferrer"
                  href={instructionsPdf}
                  title="Parsisiųsti naudotojo instrukciją"
                >
                  <FontAwesomeIcon icon={faQuestionCircle} />
                </a>
              </li>

              <li className="nav-item nav-item me-2">
                <Logout />
              </li>

            </ul>

          </div>
        </div>
      </nav>
      <div>{props.children}</div>
    </div >
  );
}