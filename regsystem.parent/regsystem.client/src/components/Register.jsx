import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

import DatePicker from 'react-date-picker';
import moment from 'moment';
import {connect} from 'react-redux'

import './../style/Register.css'

import {facultyConstants} from "../constants/Constants";
import { withRouter } from 'react-router-dom';

import * as GroupActionCreators from '../actions/GroupActions';
import * as AuthActionCreators from '../actions/AuthActions'
import { bindActionCreators } from 'redux'

import InputMask from 'react-input-mask';

export class Register extends Component {
    constructor(props) {
        super(props)

        const {dispatch} = props

        this.state = {
            studentId: '',
            birthDate: new Date(),
            name: '',
            surname: '',
            patronymic: '',
            email: '',
            password: '',
            phone: '',
            groupId: -1,
            gender: 1,

            showLoader: false,
            course: 1,
            faculty: 'EN',

        }

        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleFetchGroupRequest = this.handleFetchGroupRequest.bind(this)

        this.boundActionCreators = bindActionCreators(GroupActionCreators, dispatch)
        this.boundActionCreators = bindActionCreators(AuthActionCreators, dispatch)
    }

    componentDidMount() {
        this.handleFetchGroupRequest()
    }

    handleFetchGroupRequest() {
        this.setState({showLoader: true}, () => {
            let { dispatch } = this.props

            const request = {
                course: this.state.course,
                faculty: this.state.faculty
            }

            let action = GroupActionCreators.fetchGroups(request,
                () => {
                    this.setState({showLoader: false})
                },
                () => {
                    //setting first option as selected state
                    let e = document.getElementById("studentGroup");
                    let groupId = e.options[e.selectedIndex].value;

                    this.setState({showLoader: false, groupId: groupId})
                }
            )

            dispatch(action)
        })

    }


    handleDateChange(date) {
        this.setState({birthDate: date})
    }

    handleStudentIdChange(event) {
        this.setState({studentId:event.target.value})
    }

    handleNameChange(event) {
        this.setState({name: event.target.value})
    }

    handleSurnameChange(event) {
        this.setState({surname: event.target.value})
    }

    handlePatronymicChange(event) {
        this.setState({patronymic: event.target.value})
    }

    handleEmailChange(event) {
        this.setState({email: event.target.value})
    }

    handlePasswordChange(event) {
        this.setState({password: event.target.value})
    }

    handlePhoneChange(event) {
        this.setState({phone: event.target.value})
    }

    handleCourseChange(event) {
        this.setState({course: event.target.value})
        this.handleFetchGroupRequest()
    }

    handleFacultyChange(event) {
        this.setState({faculty: event.target.value})
        this.handleFetchGroupRequest()
    }

    handleGroupChange(event) {
        this.setState({groupId: event.target.value})
    }

    handleGenderChange(event) {
        const gender = event.target.value === 'male' ? 1 : 0
        this.setState({gender: gender})
    }



    handleSubmit(event) {
        event.preventDefault()
        // let form = document.getElementById('register-form');
        //
        // if (form.checkValidity() === false || studentId.length != 9) {
        //     event.preventDefault();
        //     event.stopPropagation();
        //
        //     form.classList.add('was-validated');
        //     return
        // }

        const student = {
            studentId: this.state.studentId,
            name: this.state.name,
            surname: this.state.surname,
            patronymic: this.state.patronymic,
            email: this.state.email,
            password: this.state.password,
            phone: this.state.phone,
            gender: this.state.gender,
            birthDate: this.state.birthDate,
            groupId: this.state.groupId
        }

        console.log(JSON.stringify(student))

        let { dispatch } = this.props

        // this.setState({showLoader: true})

        let signUpAction = AuthActionCreators.signUp(student,
            () => {
                this.setState({showLoader: false})
            }
        )

        dispatch(signUpAction)
    }


    render() {

        const ENGINEERING_FACULTY = facultyConstants.ENGINEERING_FACULTY
        const LAW_FACULTY = facultyConstants.LAW_FACULTY
        const PHILOSOPHY_FACULTY = facultyConstants.PHILOSOPHY_FACULTY
        const ECONOMY_FACULTY = facultyConstants.ECONOMY_FACULTY

        const showLoader = this.state.showLoader

        const groupItems = this.props.groupItems.map((item) => <GroupOption key={item.id} id={item.id} value={item.name}/>)

        return (
            <div>
                {showLoader &&
                <div>
                    <div className="loader"></div>
                    <div className="background"></div>
                </div>
                }


                <div id="register-wrapper">
                    {/*<AlertContainer ref={a => this.msg = a} {...this.alertOptions} />*/}
                    <div className="container-fluid h-100">
                        <div className="row h-100 justify-content-center align-items-center">
                            <form id="register-form" noValidate>
                                <div className="form-group">
                                    <label htmlFor="studentId">Студенческий ID:</label>
                                    <input type="number" className="form-control" id="studentId" placeholder="Студенческий ID"
                                           onChange={this.handleStudentIdChange.bind(this)} required/>
                                    <div class="invalid-feedback">
                                        Напишите студенческий ID. Он должен содержать 9 чисел
                                    </div>
                                </div>

                                <div className="row">
                                    <div className="form-group col">
                                        <label htmlFor="studentName">Имя:</label>
                                        <input type="text" className="form-control" id="studentName" placeholder="Имя студента" required
                                               onChange={this.handleNameChange.bind(this)}/>
                                        <div class="invalid-feedback">
                                            Напишите ваше имя
                                        </div>
                                    </div>

                                    <div className="form-group col">
                                        <label htmlFor="studentSurname">Фамилия:</label>
                                        <input type="text" className="form-control" id="studentSurname" placeholder="Фамилия студента" required
                                            onChange={this.handleSurnameChange.bind(this)}/>
                                        <div class="invalid-feedback">
                                            Напишите вашу фамилию
                                        </div>
                                    </div>

                                    <div className="form-group col">
                                        <label htmlFor="studentPatronymic">Отчество:</label>
                                        <input type="text" className="form-control" id="studentPatronymic" placeholder="Отчество студента"
                                            onChange={this.handlePatronymicChange.bind(this)}/>
                                    </div>
                                </div>



                                <div className="row">
                                    <div className="col-12">
                                        <div className="form-group">
                                            <label htmlFor="studentEmail">Email:</label>
                                            <input type="email" className="form-control" id="studentEmail" placeholder="Email студента" required
                                                onChange={this.handleEmailChange.bind(this)}/>
                                            <div className="invalid-feedback">
                                                Напишите ваш email
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div className="row">
                                    <div className="col-6">
                                        <label htmlFor="studentPassword">Пароль:</label>
                                        <input type="password" className="form-control" id="studentPassword" placeholder="Фамиилия студента" required
                                            onChange={this.handlePasswordChange.bind(this)}/>
                                        <div className="invalid-feedback">
                                            Напишите пароль
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label htmlFor="studentPhone">Телефон:</label>
                                        <InputMask {...this.props} mask="+7(999) 999-99-99" maskChar="x" className="form-control"
                                                   onChange={this.handlePhoneChange.bind(this)} id="studentPhone"/>
                                        <div className="invalid-feedback">
                                            Напишите телефон
                                        </div>
                                    </div>
                                </div>


                                <div className="row">
                                    <div className="col">
                                        <div className="form-group">
                                            <label htmlFor="studentCourse">Курс:</label>
                                            <select className="form-control" id="studentCourse" onChange={this.handleCourseChange.bind(this)}>
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <div className="form-group">
                                            <label htmlFor="studentFaculty">Факультет:</label>
                                            <select className="form-control" id="studentFaculty" onChange={this.handleFacultyChange.bind(this)}>
                                                <option value="EN">{ENGINEERING_FACULTY}</option>
                                                <option value="LAW">{LAW_FACULTY}</option>
                                                <option value="PHIL">{PHILOSOPHY_FACULTY}</option>
                                                <option value="ECO">{ECONOMY_FACULTY}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div className="col">
                                        <div className="form-group">
                                            <label htmlFor="studentGroup">Группа:</label>
                                            <select className="form-control" id="studentGroup" onChange={this.handleGroupChange.bind(this)}>
                                                {groupItems}
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col">
                                        <label htmlFor="genderMale" id="genderLabel">Пол:</label>

                                        <div className="form-check form-check-inline">
                                            <label className="form-check-label">
                                                <input className="form-check-input" type="radio" name="inlineRadioOptions" id="genderMale" value="male" checked={this.state.gender === 1}
                                                    onChange={this.handleGenderChange.bind(this)}/>   Мужской
                                            </label>
                                        </div>

                                        <div className="form-check form-check-inline">
                                            <label className="form-check-label">
                                                <input className="form-check-input" type="radio" name="inlineRadioOptions" id="genderWoman" value="female" checked={this.state.gender === 0}
                                                       onChange={this.handleGenderChange.bind(this)}/> Женский
                                            </label>
                                        </div>
                                    </div>

                                    <div className="col">
                                        <div className="form-group">
                                            <label htmlFor="studentBirthdate">Дата рождения: </label>
                                            <DatePicker id="studentBirthdate"
                                                        onChange={this.handleDateChange.bind(this)}
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
            </div>

        )
    }
}

class GroupOption extends Component {
    constructor(props){
        super(props)
    }

    render() {
        const id = this.props.id
        const value = this.props.value
        return (
            <option value={id}>{value}</option>
        )
    }
}



const mapStateToProps = state => ({
    groupItems: state.groupStatus.groupItems,
});



export default withRouter(connect(mapStateToProps)(Register));