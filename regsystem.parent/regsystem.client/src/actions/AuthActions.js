import {constants} from "../constants/Constants";
import {authService} from "../services/AuthService";
import {history} from "../helpers/history";
import {alertSuccess} from "./AlertActions";
import {alertError} from "./AlertActions";


const loginRequest = (user) => {
  return {
    type: constants.LOGIN_REQUEST,
    user
  }
}

const loginSuccess = (user) => {
  return {
    type: constants.LOGIN_SUCCESS,
    user
  }
}

const loginFailure = (error) => {
  return {
    type: constants.LOGIN_FAILURE,
    error
  }
}

export const login = (username, password, onError) => dispatch => {
  dispatch(loginRequest({username, password}))
  authService.login({username, password}).then(
    user => {
      dispatch(loginSuccess(user));
      history.push('/')
        // location.href = location.href;
    },
    error => {
      onError("ERRRORRR")
      dispatch(loginFailure(error));
      dispatch(alertError('Ошибка при авторизации. Логин или пароль неверны!'))
      // alert("Ошибка при авторизации. Логин или пароль неверны!")
    }
  )
}

export const signUp = (user, onSuccess, onError) => dispatch => {
    dispatch(signUpRequest(user))
    authService.register(user).then(
        success => {
            dispatch(signUpSuccess(success))
            if(success.status === "-1") onError(success.message)
            else history.push('/register-response')
            console.log("SUCCESS: " + JSON.stringify(success))
        },
        error => {
            onError()
            dispatch(signUpError(error))
            dispatch(alertError('Произошла ошибка при регистрации'))
        }
    )
    function signUpRequest(user) { return { type: constants.SIGN_UP_REQUEST, user} }
    function signUpSuccess(response) { return { type: constants.SIGN_UP_SUCCESS, response } }
    function signUpError(error) { return { type: constants.SIGN_UP_FAILURE, error } }
}
