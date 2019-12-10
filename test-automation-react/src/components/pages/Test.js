import React, {Component} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {addTest} from "../../actions/testActions";
import PageSelection from "../PageSelection";
import WebElementSelection from "../WebElementSelection";

const Actions = Object.freeze({
    NAVIGATE_TO: "Navigate To",
    CLICK_ON: "Click On"
});

const PATH_BASE = "http://localhost:8080/api";
const PATH_PAGE = "/pages/name/";
const PATH_WEB_ELEMENT = "/webelements/page/";

class Test extends Component {
    constructor() {
        super();

        this.state = {
            description: "",
            action: "-1",
            pageName: "",
            webelementName: "",
            page: null,
            webelement: null
        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.switchTest = this.switchTest.bind(this);
        this.onChangePage = this.onChangePage.bind(this);
        this.fetchPage = this.fetchPage.bind(this);
        this.onChangeWebElement = this.onChangeWebElement.bind(this);
        this.fetchWebElement = this.fetchWebElement.bind(this);
    }

    onChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    onChangePage(event) {
        this.onChange(event);
        this.fetchPage(event.target.value);
    }

    onChangeWebElement(event) {
        this.onChange(event);
        this.fetchWebElement(event.target.value);
    }

    onSubmit(event) {
        event.preventDefault();
        const newTest = {
            description: this.state.description,
            action: this.state.action,
            page: this.state.page,
            webElement: this.state.webelement
        };
        this.props.addTest(newTest, this.props.history);
    }

    switchTest(action) {
        switch (action) {
            case Actions.NAVIGATE_TO:
                return <PageSelection onChange={this.onChangePage}/>;
            case Actions.CLICK_ON:
                return (
                    <div>
                        <PageSelection onChange={this.onChangePage}/>
                        <WebElementSelection onChange={this.onChangeWebElement} pageName={this.state.pageName}/>
                    </div>
                );
            default:
                break;
        }
    }

    fetchPage(pageName) {
        fetch(`${PATH_BASE}${PATH_PAGE}${pageName}`)
            .then(response => response.json())
            .then(result => this.setState({page: result}))
            .catch(error => error);
    }

    fetchWebElement(webelementName) {
        fetch(`${PATH_BASE}${PATH_WEB_ELEMENT}${this.state.page.id}/${webelementName}`)
            .then(response => response.json())
            .then(result => this.setState({webelement: result}))
            .catch(error => error);
    }

    render() {
        const {action, description} = this.state;

        return (
            <form>
                <div className="form-inline">
                    <span>Test description</span>
                    <input
                        type="input"
                        className="form-control"
                        name="description"
                        value={description}
                        onChange={this.onChange}
                        aria-label="Default"
                        aria-describedby="inputGroup-sizing-default"
                    ></input>
                    <select
                        className="form-control mr-5"
                        name="action"
                        value={action}
                        onChange={this.onChange}
                    >
                        <option value="-1" disabled>
                            Select Action
                        </option>
                        {Object.values(Actions).map(action => (
                            <option>{action}</option>
                        ))}
                    </select>
                    {this.switchTest(action)}
                    <button
                        type="submit"
                        className="btn btn-primary mb-2"
                        onClick={this.onSubmit}
                    >
                        Confirm Test
                    </button>
                </div>
            </form>
        );
    }
}

Test.propTypes = {
    addTest: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps, {addTest})(
    Test
);
