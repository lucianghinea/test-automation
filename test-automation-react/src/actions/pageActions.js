import axios from "axios";

export const addPage = (page, history) => async dispatch => {
    await axios.post("http://localhost:8080/api/page", page).then(response => {
        console.log(response)
    }).catch(error => {
        console.log(error.response)
    });
    // TODO To add history path
    // history.push("/");
};

export const getPages = (history) => async dispatch => {
    await axios.get("http://localhost:8080/api/pages").then(response => {
        console.log(response)
    }).catch(error => {
        console.log(error.response)
    });
    // TODO To add history path
    // history.push("/");
};