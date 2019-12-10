import React, {Component} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {getWebElements} from "../actions/webElementActions";

const PATH_BASE = "http://localhost:8080/api";
const PATH_WEBELEMENT = "/webelements/page/";

class WebElementSelection extends Component {
    constructor() {
        super();

        this.state = {
            webelements: null,
            webelementName: ""
        };

        this.setWEbElements = this.setWEbElements.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    setWEbElements(webelements) {
        this.setState({webelements});
    }

    fetchWebElements() {
        fetch(`${PATH_BASE}${PATH_WEBELEMENT}` + this.props.pageName)
            .then(response => response.json())
            .then(result => this.setWEbElements(result))
            .catch(error => error);
    }

    onChange(event) {
        this.setState({[event.target.name]: event.target.value});
        this.props.onChange(event);
    }

    componentDidUpdate() {
        this.fetchWebElements();
    }

    render() {

        const {webelements} = this.state;

        return (
            <select
                className="form-control"
                name="webelementName"
                defaultValue="-1"
                onChange={this.onChange}
            >
                <option value="-1" disabled>
                    Select WebElement
                </option>
                {webelements && webelements.map(webelement => <option>{webelement.name}</option>)}
            </select>
        );
    }
}

WebElementSelection.propTypes = {
    getWebElements: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps, {getWebElements})(WebElementSelection);
