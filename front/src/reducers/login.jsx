import {USER__LOGIN} from "../constants";

const userState = {
    authenticated: false,
    name: ''
};

export default function login(state = userState, action) {
    switch (action.type) {
        case USER__LOGIN:
            return Object.assign({}, state, {name: action.payload.fullName, authenticated: true})
        default:
            return state;
    }
}