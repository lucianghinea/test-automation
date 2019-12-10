import React, {Component} from "react";
import "./App.css";
import Home from "./components/pages/Home";
import {BrowserRouter as Router, Route} from "react-router-dom";
import {Provider} from "react-redux";
import store from "./store";
import Test from "./components/pages/Test";
import PageForm from "./components/pages/Page";
import WebElementForm from "./components/pages/WebElement";

class App extends Component {
    render() {
        return (
            <Provider store={store}>
                <Router>
                    <div className="container">
                        <Route exact path="/" component={Home}/>
                        <Route exact path="/test" component={Test}/>
                        <Route exact path="/page" component={PageForm}/>
                        <Route exact path="/webelement" component={WebElementForm}/>
                    </div>
                </Router>
            </Provider>
        );
    }
}

export default App;
