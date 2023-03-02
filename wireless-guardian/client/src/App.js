import "./App.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import NavBar from "./components/NavBar";
import LandingPage from "./components/LandingPage";
import BusinessForm from "./components/BusinessForm";
import BusinessList from "./components/BusinessList";
import { useEffect, useState } from "react";

function App(props) {

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
          <Route
          path="/create"
          render={() => {
            return (
              <BusinessForm
                backendUrl={"http://localhost:8080/api/business"}
                setErrors={setErrors}
                getAll={props.getAll}
              />
            );
          }}
        />
        <Route
          path="/list"
          render={() => {
            return <BusinessList businesses={businesses} handleDelete={props.handleDelete} />;
          }}
        />
        <Route
          path="/edit/:agentId"
          render={() => {
            return (
              <BusinessForm
                backendUrl={"http://localhost:8080/api/business"}
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
