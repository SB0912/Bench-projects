import React from "react";
import { Link } from "react-router-dom";
import { useEffect, useState, event } from "react";

function BusinessList(props) {
  const [businesses, setBusinesses] = useState([]);
  const [errors, setErrors] = useState([]);

  const BACKEND_URL = "http://localhost:8080/api/business";

  const getAll = () => {
    fetch(BACKEND_URL)
      .then((response) => {
        return response.json();
      })
      .then((json) => {
        setBusinesses(json);
      });
  };

  useEffect(getAll, []);

  const handleDelete = (businessId, businessName) => {
    if (window.confirm(`delete business ${businessId}?`)) {
      fetch(`${"http://localhost:8080/api/business"}/${businessId}`, {
        method: "DELETE",
      }).then((response) => {
        if (response.status === 404) {
          setErrors([`cound not find business ${businessId}`]);
        } else {
          getAll();
        }
      });
    }
  };

  return (
    <table className="table">
      <thead className="thead-light">
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Address</th>
          <th scope="col">City</th>
          <th scope="col">State</th>
          <th scope="col">Postal Code</th>
          <th scope="col">Contact Email</th>
          <th scope="col">Contact Phone</th>
        </tr>
      </thead>
      <tbody className="body">
        {businesses.length === 0 ? (
          <tr>
            <td>Loading...</td>
          </tr>
        ) : (
          businesses.map((business) => {
            return (
              <tr key={business.businessId}>
                <td>{business.businessName}</td>
                <td>{business.address}</td>
                <td>{business.city}</td>
                <td>{business.state}</td>
                <td>{business.zipCode}</td>
                <td>{business.contactEmail}</td>
                <td>{business.contactPhone}</td>
                <td>
                  <Link to={`/business/edit/${business.businessId}`}>
                    <button
                      id="edit"
                      onClick={() => {
                        props.handleUpdate(business.businessId);
                      }}
                    >
                      Edit
                    </button>
                  </Link>
                </td>
                <td>
                  <button
                    id="delete"
                    onClick={() => {
                      handleDelete(business.businessId);
                    }}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            );
          })
        )}
      </tbody>
    </table>
  );
}
export default BusinessList;