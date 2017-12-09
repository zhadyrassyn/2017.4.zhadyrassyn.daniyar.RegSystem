import React from 'react';
import {connect} from 'react-redux'
import '../style/Login.css';

import 'bootstrap/dist/css/bootstrap.min.css';
import * as AuthActionCreators from '../actions/AuthActions';
import * as UserActionCreators from '../actions/UserActions'
import {bindActionCreators} from 'redux'
import {Link} from 'react-router-dom';

import AlertContainer from 'react-alert'

class Login extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',

            showLoader: false
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

        let logoutAction = UserActionCreators.logout()
        dispatch(logoutAction)

        this.handleSubmit = this.handleSubmit.bind(this)

    }

    handleEmailChange(event) {
        this.setState({email: event.target.value})
    }

    handlePasswordChange(event) {
        this.setState({password: event.target.value})
    }

    handleSubmit(event) {
        event.preventDefault()

        this.setState({showLoader: true}, () => {
            let {dispatch} = this.props;

            const userRequest = {
                email: this.state.email,
                password: this.state.password}

            let action = AuthActionCreators.login(userRequest,
                () => {
                    this.msg.show('Ошибка при входе. Логин или пароль не верны', {
                        type: 'error'
                    })
                    this.setState({showLoader: false})
                })
            dispatch(action);
        })

    }


    render() {

        const showLoader = this.state.showLoader
        return (
            <div>
                {showLoader &&
                <div>
                    <div className="loader"></div>
                    <div className="background"></div>
                </div>
                }
                <div className="wrapper">
                    <AlertContainer ref={a => this.msg = a} {...this.alertOptions} />
                    <div className="container h-100">
                        <div className="row h-100 justify-content-center align-items-center">
                            <form id="form_login">

                                <div className="form-group">
                                    <input type="email" className="form-control" id="userEmail" placeholder="Ваш Email"
                                           onChange={this.handleEmailChange.bind(this)}/>
                                </div>

                                <div className="form-group">
                                    <input type="password" className="form-control" id="userPassword" placeholder="Пароль"
                                           onChange={this.handlePasswordChange.bind(this)}/>
                                </div>

                                <button type="submit" className="btn btn-primary" onClick={this.handleSubmit}>Войти</button>

                                <p>Еще не зарегестрировались?</p>
                                <Link to="/register" className="btn btn-link">Зарегестрироваться</Link>

                            </form>
                        </div>
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