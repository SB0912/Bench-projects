import { useHistory } from "react-router-dom";

function LandingPage() {
  const history = useHistory();

  return (
    <div
      className="d-flex p-2"
      style={{
        backgroundColor: "rgb(244, 171, 163)",
        justifyContent: "center"
      }}
    >
      <div className="jumbotron jumbotron-fluid">
        <div className="container">
          <h1 className="display-4" style={{ textAlign: "center" }}>
            Hello, world!
          </h1>
          <p className="lead">
            This is a modified jumbotron that occupies the entire horizontal
            space of its parent.
          </p>
          <hr className="my-4" />
          <div className="d-flex align-items-center flex-column">
            <button
              className="btn btn-dark"
              onClick={() => {
                history.push("/you-pushed-this-button/i-told-you-not-to");
              }}
            >
              dont click me
            </button>
            <button
              className="btn btn-light"
              style={{ marginTop: "2%" }}
              onClick={() => {
                history.push("/what-did-i-just-tell-you");
              }}
            >
              dont click me either
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default LandingPage;
