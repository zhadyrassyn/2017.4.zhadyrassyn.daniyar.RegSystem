import React, {Component} from 'react';
import AlertContainer from 'react-alert'
import 'bootstrap/dist/css/bootstrap.min.css';

import DatePicker from 'react-date-picker';
import moment from 'moment';

import './../style/Register.css'



export default class Register extends Component {
    constructor(props) {
        super(props)

        this.state = {
            studentId: '',
            birthDate: new Date()
        }

        this.handleDateChange = this.handleDateChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleStudentIdChange = this.handleStudentIdChange.bind(this)
    }


    handleDateChange(date) {
        this.setState = ({birthDate: date})
    }

    handleStudentIdChange(event) {
        this.setState({studentId:event.target.value})
    }


    handleSubmit(event) {
        event.preventDefault()
        const studentId = this.state.studentId;
        let form = document.getElementById('register-form');

        if (form.checkValidity() === false || studentId.length != 9) {
            event.preventDefault();
            event.stopPropagation();

            form.classList.add('was-validated');
            return
        }

    }


    render() {

        return (
            <div id="register-wrapper">
                <AlertContainer ref={a => this.msg = a} {...this.alertOptions} />
                <div className="container-fluid h-100">
                    <div className="row h-100 justify-content-center align-items-center">
                        <form id="register-form" noValidate>
                            <div className="form-group">
                                <label htmlFor="studentId">Студенческий ID:</label>
                                <input type="number" className="form-control" id="studentId" placeholder="Студенческий ID"
                                       onChange={this.handleStudentIdChange} required/>
                                <div class="invalid-feedback">
                                    Напишите студенческий ID. Он должен содержать 9 чисел
                                </div>
                            </div>

                            <div className="row">
                                <div className="form-group col">
                                    <label htmlFor="studentName">Имя:</label>
                                    <input type="text" className="form-control" id="studentName" placeholder="Имя студента" required/>
                                    <div class="invalid-feedback">
                                        Напишите ваше имя
                                    </div>
                                </div>

                                <div className="form-group col">
                                    <label htmlFor="studentSurname">Фамилия:</label>
                                    <input type="text" className="form-control" id="studentSurname" placeholder="Фамилия студента" required/>
                                    <div class="invalid-feedback">
                                        Напишите вашу фамилию
                                    </div>
                                </div>

                                <div className="form-group col">
                                    <label htmlFor="studentPatronymic">Отчество:</label>
                                    <input type="text" className="form-control" id="studentPatronymic" placeholder="Отчество студента"/>
                                </div>
                            </div>



                            <div className="form-group">
                                <label htmlFor="studentEmail">Email:</label>
                                <input type="email" className="form-control" id="studentEmail" placeholder="Email студента" required/>
                                <div class="invalid-feedback">
                                    Напишите ваш email
                                </div>
                            </div>

                            <div className="row">
                                <div className="col">
                                    <label htmlFor="genderMale" id="genderLabel">Пол:</label>
                                    <div className="form-check form-check">

                                        <label className="form-check-label">
                                            <input className="form-check-input" type="radio" name="inlineRadioOptions" id="genderMale" value="male" checked/>   Мужской
                                        </label>
                                    </div>

                                    <div className="form-check form-check">
                                        <label className="form-check-label">
                                            <input className="form-check-input" type="radio" name="inlineRadioOptions" id="genderWoman" value="female"/> Женский
                                        </label>
                                    </div>
                                </div>
                                <div className="col">
                                    <div class="form-group">
                                        <label for="studentGroup">Группа:</label>
                                        <select class="form-control" id="studentGroup">
                                            <option>ENC01</option>
                                            <option>ENC02</option>
                                            <option>ENC03</option>
                                            <option>ENC04</option>
                                            <option>ENC05</option>
                                        </select>
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="form-group">
                                        <label htmlFor="studentBirthdate">Дата рождения: </label>
                                        <DatePicker id="studentBirthdate"
                                                    onChange={this.handleDateChange}
                                                    value={this.state.birthDate}
                                        />
                                    </div>
                                </div>
                            </div>


                            <button type="submit" className="btn btn-success" onClick={this.handleSubmit}>Зарегестрироваться</button>

                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

