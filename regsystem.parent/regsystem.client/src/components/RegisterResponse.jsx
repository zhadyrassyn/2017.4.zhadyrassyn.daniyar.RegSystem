import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

import DatePicker from 'react-date-picker';
import moment from 'moment';
import {connect} from 'react-redux'
import { withRouter } from 'react-router-dom';

export class RegisterResponse extends Component {
    render() {
        return (
            <h1>Спасибо за регистрацию. Ожидайте письмо на ваш email</h1>
        )
    }
}

const mapStateToProps = state => ({});


export default withRouter(connect(mapStateToProps)(RegisterResponse));