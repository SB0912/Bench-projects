import "./App.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import NavBar from "./components/NavBar";
import LandingPage from "./components/LandingPage";
import BusinessForm from "./components/BusinessForm";
import BusinessList from "./components/BusinessList";
import ResellerOrgForm from "./components/ResellerOrgForm";
import ResellerOrgList from "./components/ResellerOrgList";
import { useEffect, useState } from "react";

function App(props) {

  const businessBackendUrl = "http://localhost:8080/api/business"
  const resellerOrgBackendUrl = "http://localhost:8080/api/resellerOrg"

  const [errors, setErrors] = useState([]);
  const [businesses, setBusinesses] = useState([]);

  return (
    <div className="App">
      <Router>
        <NavBar />
        <Switch>
          <Route exact path="/BusinessForm">
            <BusinessForm />
          </Route>
          <Route exact path="/BusinessList">
            <BusinessList />
          </Route>
          <Route exact path="/ResellerOrgForm">
            <ResellerOrgForm />
          </Route>
          <Route exact path="/ResellerOrgList">
            <ResellerOrgList />
          </Route>
          {/* <Route
          path="/create"
          render={() => {
            return (
              <BusinessForm
                backendUrl={businessBackendUrl}
                setErrors={setErrors}
                getAll={props.getAll}
              />
            );
          }}
        /> */}
        <Route
          path="/business/list"
          render={() => {
            return (
            <BusinessList businesses={businesses} handleDelete={props.handleDelete} />
          );
          }}
        />
        <Route
          path="/business/edit/:businessId"
          render={() => {
            return (
              <BusinessForm
                backendUrl={businessBackendUrl}
                setErrors={setErrors}
                getAll={props.getAll}
                businesses={businesses}
              />
            );
          }}
        />
        </Switch>
        <Switch>
          <Route exact path="/">
            <LandingPage />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
