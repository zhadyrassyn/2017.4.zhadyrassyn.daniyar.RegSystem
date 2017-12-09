import {constants} from "../constants/Constants";
import {authService} from "../services/AuthService";
import {history} from "../helpers/history";
import {alertSuccess} from "./AlertActions";
import {alertError} from "./AlertActions";

export const login = (user, onError) => dispatch => {
    dispatch(loginRequest(user))
    authService.login(user).then(
        response => {
            dispatch(loginSuccess(response))
            console.log('SUCCESS: ' + JSON.stringify(response))
            if(response.status === "-1") {
                onError()
            } else {
                console.log(localStorage.getItem('user'))
                history.push('/')
            }
            // history.push('/')
            // location.href = location.href;
        },
        error => {
            onError("ERRRORRR")
            dispatch(loginFailure(error));
            // alert("Ошибка при авторизации. Логин или пароль неверны!")
        }
    )

    function loginRequest(user) { return { type: constants.LOGIN_REQUEST, user } }
    function loginSuccess(user) { return { type: constants.LOGIN_SUCCESS, user } }
    function loginFailure(error) { return { type: constants.LOGIN_FAILURE, error } }
}

export const signUp = (user, onSuccess, onError) => dispatch => {
    dispatch(signUpRequest(user))
    authService.register(user).then(
        success => {
            dispatch(signUpSuccess(success))
            if (success.status === "-1") onError(success.message)
            else history.push('/register-response')
            console.log("SUCCESS: " + JSON.stringify(success))
        },
        error => {
            onError()
            dispatch(signUpError(error))
            dispatch(alertError('Произошла ошибка при регистрации'))
        }
    )

    function signUpRequest(user) {
        return {type: constants.SIGN_UP_REQUEST, user}
    }

    function signUpSuccess(response) {
        return {type: constants.SIGN_UP_SUCCESS, response}
    }

    function signUpError(error) {
        return {type: constants.SIGN_UP_FAILURE, error}
    }
}
