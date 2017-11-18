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
