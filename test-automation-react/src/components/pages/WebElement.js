import React, {Component} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {addWebElement} from "../../actions/webElementActions";
import PageSelection from "../PageSelection";

const PATH_BASE = "http://localhost:8080/api";
const PATH_PAGE = "/pages/";

class WebElement extends Component {
    constructor() {
        super();
        this.state = {
            pages: null,
            page: null,
            name: "",
            selectBy: "",
            selector: ""
        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.setPages = this.setPages.bind(this);
        this.fetchPages = this.fetchPages.bind(this);
        this.onChangePage = this.onChangePage.bind(this);
    }

    onChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    onChangePage(event) {
        this.setState({page: event.target.value});
    }

    onSubmit(event) {
        event.preventDefault();
        const newWeElement = {
            name: this.state.name,
            selectBy: this.state.selectBy,
            selector: this.state.selector,
            page: this.state.pages.find(page => page.name === this.state.page)
        };
        this.props.addWebElement(newWeElement, this.props.history);
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
        return (
            <form className="form-inline" onSubmit={this.onSubmit}>
                <div className="form-group mx-sm-3 mb-2">
                    <PageSelection onChange={this.onChangePage}/>
                    <select
                        className="form-control"
                        name="selectBy"
                        defaultValue={"-1"}
                        onChange={this.onChange}
                    >
                        <option value="-1" disabled>
                            Select By
                        </option>
                        <option>CSS</option>
                        <option>XPATH</option>
                    </select>
                    <label className="sr-only">WebElement Name</label>
                    <input
                        type="input"
                        className="form-control"
                        name="name"
                        value={this.state.name}
                        placeholder="WebElement Name"
                        onChange={this.onChange}
                    ></input>
                    <label className="sr-only">Selector</label>
                    <input
                        type="input"
                        className="form-control"
                        name="selector"
                        value={this.state.selector}
                        placeholder="Selector"
                        onChange={this.onChange}
                    ></input>
                    <button type="submit" className="btn btn-primary mb-2">
                        Confirm WebElement
                    </button>
                </div>
            </form>
        );
    }
}

WebElement.propTypes = {
    addWebElement: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps, {addWebElement})(WebElement);
