import {authHeader} from "../helpers/auth-header";

export const userService = {
  logout
}

function logout() {
  localStorage.removeItem('user')
}
