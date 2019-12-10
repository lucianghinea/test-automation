import axios from "axios";

export const addTest = (test, history) => async dispatch => {
    await axios.post("http://localhost:8080/api/test", test).then(response => {
        console.log(response)
    }).catch(error => {
        console.log(error.response)
    });
    // TODO To add history path
    // history.push("/");
};