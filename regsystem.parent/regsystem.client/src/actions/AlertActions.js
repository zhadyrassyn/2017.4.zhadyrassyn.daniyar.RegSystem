import {constants} from "../constants/Constants";

export const alertSuccess = (message) => {
  return {
    type: constants.ALERT_SUCCESS_SHOW,
    message
  }
}

export const alertError = (message) => {
  return {
    type: constants.ALERT_ERROR_SHOW,
    message
  }
}