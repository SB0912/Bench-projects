import React, { useEffect } from "react";

import { useState } from "react";
import { useHistory, useParams, Link } from "react-router-dom";

function BusinessForm(props) {
  const [businessName, setBusinessName] = useState("");
  const [address, setAddress] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [zipCode, setZipCode] = useState("");
  const [contactEmail, SetContactEmail] = useState("");
  const [contactPhone, SetContactPhone] = useState("");
  const [sites, setSites] = useState([]);
  const [errors, setErrors] = useState([]);

  const params = useParams();
  const history = useHistory();

  const checkForPrePopulate = () => {
    if (params.businessId && props.businesses.length > 0) {
      const targetBusiness = props.businesses.find((business) => {
        return business.businessId.toString() === params.businessId.toString();
      });

      setBusinessName(targetBusiness.businessName);
      setAddress(targetBusiness.address);
      setCity(targetBusiness.city);
      setState(targetBusiness.state);
      setZipCode(targetBusiness.zipCode);
      SetContactEmail(targetBusiness.contactEmail);
      SetContactPhone(targetBusiness.contactPhone);
      setSites(targetBusiness.sites);
    }
  };

  useEffect(checkForPrePopulate, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    if (params.businessId !== undefined) {
      handleUpdate();
    } else {
      handleCreate();
    }
  };

  const handleCreate = () => {
    const newBusiness = {
      businessName,
      address,
      city,
      state,
      zipCode,
      contactEmail,
      contactPhone,
    };

    fetch("http://localhost:8080/api/business", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(newBusiness),
    }).then((result) => {
      if (result.status === 201) {
        setErrors([]);
        clearForm();
        // props.getAll();
        history.push("/business/list");
      } else {
        result.json().then((errors) => {
          setErrors(errors);
        });
      }
    });
  };

  const handleUpdate = () => {
    const updatedBusiness = {
      businessName,
      address,
      city,
      state,
      zipCode,
      contactEmail,
      contactPhone,
      sites,
      businessId: params.businessId,
    };
    fetch(`${"http://localhost:8080/api/business"}/${params.businessId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(updatedBusiness),
    }).then((response) => {
      if (response.status === 204) {
        clearForm();
        // props.getAll();
        history.push("/Businesslist");
        setErrors([]);
      } else {
        response.json().then((errors) => {
          setErrors(errors);
        });
      }
    });
  };

  const clearForm = () => {
    setBusinessName("");
    setAddress("");
    setCity("");
    setState("");
    setZipCode("");
    SetContactEmail("");
    SetContactPhone("");
    setSites([]);
  };

  const cancelUpdate = () => {
    // setEditingId(0);
    clearForm();
  };

  const cancelAdd = () => {
    // setEditingId(0);
    clearForm();
  };

  return (
    <div
      className="d-flex p-3"
      style={{
        backgroundColor: "rgb(244, 171, 163)",
        justifyContent: "center",
        float: "left",
        marginLeft: "37%"
      }}
    >
      <form
        id="form"
        onSubmit={(event) => {
          handleSubmit(event);
        }}
      >
        <div>
          <label htmlFor="name-input">Business Name:</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={businessName}
            onChange={(event) => {
              setBusinessName(event.target.value);
            }}
            type="text"
            id="name-input"
          />
        </div>
        <div>
          <label htmlFor="address-input">Address:</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={address}
            onChange={(event) => {
              setAddress(event.target.value);
            }}
            type="text"
            id="address-input"
          />
        </div>
        <div>
          <label htmlFor="city-input">City:</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={city}
            onChange={(event) => {
              setCity(event.target.value);
            }}
            type="text"
            id="city-input"
          />
        </div>
        <div>
          <label htmlFor="state-input">State:</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={state}
            onChange={(event) => {
              setState(event.target.value);
            }}
            type="text"
            id="state-input"
          />
        </div>
        <div>
          <label htmlFor="zipcode-input">Postal Code:</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={zipCode}
            onChange={(event) => {
              setZipCode(event.target.value);
            }}
            type="number"
            id="zipcode-input"
          />
        </div>
        <div>
          <label htmlFor="contact-email-input">Contact Email:</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={contactEmail}
            onChange={(event) => {
              SetContactEmail(event.target.value);
            }}
            type="email"
            id="contact-email-input"
          />
        </div>
        <div>
          <label htmlFor="contact-phone-input">Contact Phone:</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={contactPhone}
            onChange={(event) => {
              SetContactPhone(event.target.value);
            }}
            type="tel"
            id="contact-phone-input"
          />
        </div>
        <button type="submit" className="btn btn-primary">{params.businessId ? "Update" : "Add"}</button>
        {params.businessId !== undefined ? (
          <Link
            to="/BusinessList"
            onClick={() => {
              setErrors([]);
              cancelUpdate();
            }}
          >
            <button className="btn btn-danger">Cancel</button>
          </Link>
        ) : null}
        {params.businessId === undefined ? (
          <Link
            to="/BusinessList"
            onClick={() => {
              setErrors([]);
              cancelAdd();
            }}
          >
            <button className="btn btn-danger">Cancel</button>
          </Link>
        ) : null}
      </form>
    </div>
  );
}
export default BusinessForm;