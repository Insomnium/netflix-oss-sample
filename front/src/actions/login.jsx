import fetch from 'isomorphic-fetch';
import {
    BASE_API_URL,
    USER__LOGIN
} from "../constants";

export const login = (userData) => ({
    type: USER__LOGIN,
    payload: userData
});

export const performLogin = () => dispatch => {
    // return fetch(`${BASE_API_URL}/me`)
    //     .then(response => { if (!response.ok) throw Error(response.statusText)}
    //     .catch(console.log)
};