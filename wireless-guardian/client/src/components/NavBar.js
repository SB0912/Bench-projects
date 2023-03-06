import { useContext } from "react";
import { useHistory } from "react-router-dom";
import NavDropdown from 'react-bootstrap/NavDropdown';

function NavBar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <a className="navbar-brand" href="/">
        <img
          src="https://root.cern.ch/img/logos/ROOT_Logo/misc/generic-logo-cyan-512.png"
          width="30"
          height="30"
          className="d-inline-block align-top"
          alt=""
        />
        WG-Manager
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav">
          <li className="nav-item active">
            <a className="nav-link" href="/">
              Home <span className="sr-only">(current)</span>
            </a>
          </li>
          <NavDropdown title="Businesses" id="basic-nav-dropdown">
              <NavDropdown.Item href="BusinessList">View</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="BusinessForm">
                Add/Edit
              </NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="Reseller Orgs" id="basic-nav-dropdown">
              <NavDropdown.Item href="ResellerOrgList">View</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="ResellerOrgForm">
                Add/Edit
              </NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="Sales Reps" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">View</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.2">
                Add/Edit
              </NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="Sites" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">View</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.2">
                Add/Edit
              </NavDropdown.Item>
            </NavDropdown>
        </ul>
      </div>
    </nav>
  );
}

export default NavBar;
