import {constants} from "../constants/Constants";

// const user = localStorage.getItem('user')
// const isLoggedIn = user ? true : false
export default (state = {isLoggedIn: false, registering: false}, action) => {
    switch (action.type) {
        case constants.LOGIN_REQUEST:
            return {
                isLoggedIn: false
            }
        case constants.LOGIN_SUCCESS:
            return {
                isLoggedIn: true
            }
        case constants.LOGIN_FAILURE:
            return {}
        case constants.LOGOUT:
            return {
                isLoggedIn: false
            }
        case constants.SIGN_UP_REQUEST:
            return {
                ...state,
                registering: true
            }
        case constants.SIGN_UP_SUCCESS:
            return {}
        case constants.SIGN_UP_FAILURE:
            return {}
        default:
            return state;
    }
}