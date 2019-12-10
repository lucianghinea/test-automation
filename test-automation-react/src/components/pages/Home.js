import React, { Component } from "react";
import { Link } from "react-router-dom";
import "../styles/Home.css";

class Home extends Component {
  render() {
    return (
      <div>
        <div className="btn-group">
          <Link
            className="btn btn-primary btn-lg mr-4 text-center"
            to="/testsuite"
          >
            Test Suite
          </Link>
          <Link className="btn btn-primary btn-lg text-center" to="/test">
            Create Test
          </Link>
        </div>
        <div className="btn-group">
          <Link className="btn btn-primary btn-lg mr-4 text-center" to="/page">
            Create Page
          </Link>
          <Link className="btn btn-primary btn-lg text-center" to="/webelement">
            Create WebElement
          </Link>
        </div>
      </div>
    );
  }
}

export default Home;
