import React from 'react';
import {connect} from 'react-redux'
import '../style/Login.css';

import 'bootstrap/dist/css/bootstrap.min.css';
import * as AuthActionCreators from '../actions/AuthActions';
import * as UserActionCreators from '../actions/UserActions'
import { bindActionCreators } from 'redux'

import AlertContainer from 'react-alert'

class Login extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      login: '',
      password: ''
    }

    this.alertOptions = {
      offset: 40,
      position: 'top right',
      theme: 'light',
      time: 5000,
      transition: 'scale'
    }

    const {dispatch} = props;

    this.boundActionCreators = bindActionCreators(AuthActionCreators, dispatch)
    this.boundActionCreators = bindActionCreators(UserActionCreators, dispatch)

    let logoutAction = UserActionCreators.logout()
    dispatch(logoutAction)

    this.handleLoginChange = this.handleLoginChange.bind(this)
    this.handlePasswordChange = this.handlePasswordChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)

  }

  handleLoginChange(event) {
    this.setState({login: event.target.value})
  }

  handlePasswordChange(event) {
    this.setState({password: event.target.value})
  }

  handleSubmit(event) {
    event.preventDefault()

    let { dispatch } = this.props;

    let action = AuthActionCreators.login(this.state.login, this.state.password, () => this.msg.show('Ошибка при входе. Логин или пароль не верны', {
      type: 'error'
    }));
    dispatch(action);
  }


  render() {

    return (
      <div className="wrapper">
        <AlertContainer ref={a => this.msg = a} {...this.alertOptions} />
        <div className="container h-100">
          <div className="row h-100 justify-content-center align-items-center">
            <form id="form_login">

              <div className="form-group">
                <input type="text" className="form-control" id="username" placeholder="Ваш логин"
                  onChange={this.handleLoginChange}/>
              </div>

              <div className="form-group">
                <input type="password" className="form-control" id="password" placeholder="Пароль"
                  onChange={this.handlePasswordChange}/>
              </div>

              <button type="submit" className="btn btn-primary" onClick={this.handleSubmit}>Войти</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}


const mapStateToProps = state => ({
  isLoggedIn: state.authStatus.isLoggedIn,
  alertSuccess: state.alertStatus.alertSuccess,
  alertError: state.alertStatus.alertError
});

export default connect(mapStateToProps)(Login);