import React, { useEffect } from "react";

import { useState } from "react";
import { useHistory, useParams, Link } from "react-router-dom";
import Accordion from "react-bootstrap/Accordion";

export default function ResellerOrgList(props) {
  const [resellerOrgs, setResellerOrgs] = useState([]);
  const [resellerOrgDetail, setResellerOrgDetail] = useState(null);
  const [errors, setErrors] = useState([]);

  const BACKEND_URL = "http://localhost:8080/api/resellerOrg";

  const getAll = () => {
    fetch(BACKEND_URL)
      .then((response) => {
        return response.json();
      })
      .then((json) => {
        setResellerOrgs(json);
      });
  };

  const getDetails = (resellerOrgId) => {
    fetch(`${"http://localhost:8080/api/resellerOrg"}/${resellerOrgId}`)
      .then((response) => {
        return response.json();
      })
      .then((json) => {
        setResellerOrgDetail(json);
      });
  };

  const handleDelete = (resellerOrgId) => {
    if (window.confirm(`delete reseller ${resellerOrgId}?`)) {
      fetch(`${"http://localhost:8080/api/resellerOrg"}/${resellerOrgId}`, {
        method: "DELETE",
      }).then((response) => {
        if (response.status === 404) {
          setErrors([`cound not find reseller ${resellerOrgId}`]);
        } else {
          getAll();
        }
      });
    }
  };

  useEffect(getAll, []);
  return resellerOrgs.map((resellerOrg) => {
    return (
      <Accordion
        key={resellerOrg.resellerOrgId}
        onClick={() => getDetails(resellerOrg.resellerOrgId)}
      >
        <Accordion.Item eventKey="0">
          <Accordion.Header>{resellerOrg.name}</Accordion.Header>
          <Accordion.Body>
            Parent Org ID: <b>{resellerOrg.parentOrgId}</b>
            <br></br>
            Child Org ID: <b>{resellerOrg.childOrgId}</b>
            <br></br>
            <Accordion defaultActiveKey="0">
              <Accordion.Item>
                <Accordion.Header>
                  sales Reps:
                  <Accordion.Body>
                    <table
                      className="table table-striped"
                      style={{ marginLeft: "150%" }}
                    >
                      <thead className="thead-light">
                        <tr>
                          <th>First Name</th>
                          <th>Last Name</th>
                          <th>City</th>
                          <th>State</th>
                        </tr>
                      </thead>
                      <tbody>
                        {resellerOrgDetail?.salesReps.map((item) => {
                          return (
                            <tr key={item.salesRepId}>
                              <td>{item.firstName}</td>
                              <td>{item.lastName}</td>
                              <td>{item.city}</td>
                              <td>{item.state}</td>
                            </tr>
                          );
                        })}
                      </tbody>
                    </table>
                  </Accordion.Body>
                </Accordion.Header>
              </Accordion.Item>
            </Accordion>
            <Accordion defaultActiveKey="0">
              <Accordion.Item>
                <Accordion.Header>
                  Sites:
                  <Accordion.Body>
                  <table
                      className="table table-striped"
                    >
                      <thead className="thead-light">
                        <tr>
                          <th>Business Id</th>
                          <th>Reseller Id</th>
                          <th>Latitude</th>
                          <th>Longitude</th>
                          <th>Services Sold</th>
                          <th>Revenue</th>
                          <th>Multiple Sites?</th>
                        </tr>
                      </thead>
                      <tbody>
                        {resellerOrgDetail?.sites.map((item) => {
                          return (
                            <tr key={item.siteId}>
                              <td>{item.businessId}</td>
                              <td>{item.resellerOrgId}</td>
                              <td>{item.latitude}</td>
                              <td>{item.longitude}</td>
                              <td>{item.servicesSold}</td>
                              <td> $ {item.revenue}</td>
                              <td>{item.multipleSites.toString()}</td>
                            </tr>
                          );
                        })}
                      </tbody>
                    </table>
                  </Accordion.Body>
                </Accordion.Header>
              </Accordion.Item>
            </Accordion>
            <div style={{ float: "inline-end" }}>
              <Link to={`/reseller/edit/${resellerOrg.resellerOrgId}`}>
                <button
                  className="btn btn-warning btn-sm"
                  id="edit"
                  style={{ marginRight: "5px" }}
                  onClick={() => {
                    props.handleUpdate(resellerOrg.resellerOrgId);
                  }}
                >
                  Edit
                </button>
                |
              </Link>
              <button
                className="btn btn-danger btn-sm"
                id="delete"
                style={{ marginLeft: "5px" }}
                onClick={() => {
                  handleDelete(resellerOrg.resellerOrgId);
                }}
              >
                Delete
              </button>
            </div>
          </Accordion.Body>
        </Accordion.Item>
      </Accordion>
    );
  });
}
