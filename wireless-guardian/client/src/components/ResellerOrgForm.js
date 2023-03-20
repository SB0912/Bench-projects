import React, { useEffect } from "react";

import { useState } from "react";
import { useHistory, useParams, Link } from "react-router-dom";

function ResellerOrgForm(props) {
  const [name, setName] = useState("");
  const [parentOrgId, setParentOrgId] = useState("");
  const [childOrgId, setChildOrgId] = useState("");
  const [errors, setErrors] = useState([]);

  const params = useParams();
  const history = useHistory();

  const checkForPrePopulate = () => {
    if (params.resellerOrgId && props.resellerOrgs.length > 0) {
      const targetResellerOrg = props.resellerOrgs.find((resellerOrg) => {
        return resellerOrg.resellerOrgId.toString() === params.resellerOrgId.toString();
      });

      setName(targetResellerOrg.name);
      setParentOrgId(targetResellerOrg.parentOrgId);
      setParentOrgId(targetResellerOrg.childOrgId);
    }
  };

  useEffect(checkForPrePopulate, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    if (params.resellerOrgId !== undefined) {
      handleResellerUpdate();
    } else {
      handleCreate();
    }
  };

  const handleCreate = () => {
    const newResellerOrg = {
      name,
      parentOrgId,
      childOrgId
    };

    fetch("http://localhost:8080/api/resellerOrg", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(newResellerOrg),
    }).then((result) => {
      if (result.status === 201) {
        setErrors([]);
        clearForm();
        // props.getAll();
        history.push("/ResellerOrgList");
      } else {
        result.json().then((errors) => {
          setErrors(errors);
        });
      }
    });
  };

  const handleResellerUpdate = () => {
    const updatedResellerOrg = {
      name,
      parentOrgId,
      childOrgId,
      resellerOrgId: params.resellerOrgId,
    };
    fetch(`${"http://localhost:8080/api/resellerOrg"}/${params.resellerOrgId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(updatedResellerOrg),
    }).then((response) => {
      if (response.status === 204) {
        clearForm();
        // props.getAll();
        history.push("/ResellerOrglist");
        props.setErrors([]);
      } else {
        response.json().then((errors) => {
          props.setErrors(errors);
        });
      }
    });
  };

  const clearForm = () => {
    setName("");
    setParentOrgId("");
    setChildOrgId("");
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
          <label htmlFor="name-input">Reseller Org Name:</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={name}
            onChange={(event) => {
              setName(event.target.value);
            }}
            type="text"
            id="name-input"
          />
        </div>
        <div>
          <label htmlFor="parent-org-input">Parent Org (if any):</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={parentOrgId}
            onChange={(event) => {
              setParentOrgId(event.target.value);
            }}
            type="text"
            id="parent-org-input"
          />
        </div>
        <div>
          <label htmlFor="child-org-input">Child Org (if any):</label>
          <input
            className="form-control form-control-sm"
            style={{
              width: "200%",
            }}
            value={childOrgId}
            onChange={(event) => {
              setChildOrgId(event.target.value);
            }}
            type="text"
            id="child-org-input"
          />
        </div>
        <input type="submit" value={params.resellerOrgId ? "Update" : "Add"} />
        {params.resellerOrgId !== undefined ? (
          <Link
            to="/ResellerOrgList"
            onClick={() => {
              setErrors([]);
              cancelUpdate();
            }}
          >
            <button className="btn btn-danger">Cancel</button>
          </Link>
        ) : null}
        {params.resellerOrgId === undefined ? (
          <Link
            to="/ResellerOrglist"
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
export default ResellerOrgForm;