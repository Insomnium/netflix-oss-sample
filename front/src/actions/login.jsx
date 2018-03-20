import {USER__LOGIN} from "../constants";

export const login = (userData) => ({
    type: USER__LOGIN,
    payload: userData
});