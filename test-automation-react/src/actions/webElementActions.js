import axios from "axios";

export const addWebElement = (element, history) => async dispatch => {
    await axios.post("http://localhost:8080/api/webelement", element).then(response => {
        console.log(response)
    }).catch(error => {
        console.log(error.response)
    });
    // TODO To add history path
    // history.push("/");
};

export const getWebElements = (history) => async dispatch => {
    await axios.get("http://localhost:8080/api/webelements").then(response => {
        console.log(response)
    }).catch(error => {
        console.log(error.response)
    });
    // TODO To add history path
    // history.push("/");
};