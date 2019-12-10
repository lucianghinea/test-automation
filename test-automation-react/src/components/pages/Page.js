import React, {Component} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {addPage} from "../../actions/pageActions";

class Page extends Component {
    constructor() {
        super();
        this.state = {
            pageName: "",
            pageUrl: ""
        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    onSubmit(event) {
        event.preventDefault();
        const newPage = {
            name: this.state.pageName,
            url: this.state.pageUrl
        };
        this.props.addPage(newPage, this.props.history);
    }

    render() {
        return (
            <form className="form-inline" onSubmit={this.onSubmit}>
                <div className="form-group mx-sm-3 mb-2">
                    <label htmlFor="inputPageName" className="sr-only">
                        Page Name
                    </label>
                    <input
                        type="input"
                        className="form-control"
                        name="pageName"
                        value={this.state.pageName}
                        placeholder="Page Name"
                        onChange={this.onChange}
                    ></input>
                </div>
                <div className="form-group mx-sm-3 mb-2">
                    <label htmlFor="inputPageUrl" className="sr-only">
                        Page URL
                    </label>
                    <input
                        type="input"
                        className="form-control"
                        name="pageUrl"
                        value={this.state.pageUrl}
                        placeholder="Page Url"
                        onChange={this.onChange}
                    ></input>
                </div>
                <button type="submit" className="btn btn-primary mb-2">
                    Confirm Page
                </button>
            </form>
        );
    }
}

Page.propTypes = {
    addPage: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps, {addPage})(Page);
