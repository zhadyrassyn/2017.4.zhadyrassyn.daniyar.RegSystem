import { combineReducers } from 'redux'
import authStatus from './AuthReducer';
import userStatus from './UserReducer'
import alertStatus from './AlertReducer'

export default combineReducers({
  authStatus,
  userStatus,
  alertStatus
});