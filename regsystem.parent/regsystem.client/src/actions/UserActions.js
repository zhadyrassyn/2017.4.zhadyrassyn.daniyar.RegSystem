import {constants} from "../constants/Constants";
import {userService} from "../services/UserService";
import {history} from "../helpers/history";

export const logout = () => dispatch => {
  userService.logout()
  dispatch( { type: constants.LOGOUT } )
  history.push('/login')

}
