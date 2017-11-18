import {constants} from "../constants/Constants";

export default (state = {alertSuccess: false, alertError: false}, action) => {
  switch (action.type) {
    case constants.ALERT_SUCCESS_SHOW:
      return {
        ...state,
        alertSuccess: true,
        alertError: false,
      }

    case constants.ALERT_ERROR_SHOW:
      return {
        ...state,
        alertSuccess: false,
        alertError: true
      }

    default:
      return state
  }
}