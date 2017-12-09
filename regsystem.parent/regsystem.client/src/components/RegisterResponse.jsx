import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

import {connect} from 'react-redux'
import { withRouter } from 'react-router-dom';

import '../style/Register.css'

export class RegisterResponse extends Component {
    render() {
        console.log('RegisterResponse')
        return (
            <div className="register-response-wrapper">
                <div className="container-fluid h-100">
                    <div className="row h-100 justify-content-center align-items-center">
                        <h3 className="text-center">Спасибо за регистрацию. Ожидайте письма на ваш email ^_^</h3>
                    </div>
                </div>
            </div>
        )
    }
}

const mapStateToProps = state => ({});


export default withRouter(connect(mapStateToProps)(RegisterResponse));