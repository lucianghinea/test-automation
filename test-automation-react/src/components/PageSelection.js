import React, {Component} from 'react'
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {getPages} from "../actions/pageActions";

const PATH_BASE = "http://localhost:8080/api";
const PATH_PAGE = "/pages";

class PageSelection extends Component {

    constructor() {
        super();

        this.state = {
            pages: null,
            pageName: ""
        }

        this.setPages = this.setPages.bind(this);
        this.fetchPages = this.fetchPages.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    onChange(event) {
        this.setState({[event.target.name]: event.target.value});
        this.props.onChange(event);
    }

    setPages(pages) {
        this.setState({pages});
    }

    fetchPages() {
        fetch(`${PATH_BASE}${PATH_PAGE}`)
            .then(response => response.json())
            .then(result => this.setPages(result))
            .catch(error => error);
    }

    componentDidMount() {
        this.fetchPages();
    }

    render() {

        const {pages} = this.state;

        return (
            <select
                className="form-control"
                name="pageName"
                defaultValue={"-1"}
                onChange={this.onChange}
            >
                <option value="-1" disabled>
                    Select Page
                </option>
                {pages && pages.map(page => <option>{page.name}</option>)}
            </select>
        )
    }
}

PageSelection.propTypes = {
    getPages: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps, {getPages})(PageSelection);