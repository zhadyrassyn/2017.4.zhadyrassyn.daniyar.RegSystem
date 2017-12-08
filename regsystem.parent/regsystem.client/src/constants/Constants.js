export const constants = {
    MESSAGE_REQUEST: 'SEND_MESSAGE_REQUEST',
    MESSAGE_SUCCESS: 'SEND_MESSAGE_SUCCESS',
    MESSAGE_FAILURE: 'SEND_MESSAGE_FAILURE',

    LOGIN_REQUEST: 'USERS_LOGIN_REQUEST',
    LOGIN_SUCCESS: 'USERS_LOGIN_SUCCESS',
    LOGIN_FAILURE: 'USERS_LOGIN_FAILURE',

    LOGOUT: 'USERS_LOGOUT',

    ALERT_SUCCESS_SHOW: 'ALERT_SUCCESS_SHOW',
    ALERT_ERROR_SHOW: 'ALERT_ERROR_SHOW',

    //GROUP
    FETCH_GROUPS_REQUEST: 'FETCH_GROUPS_REQUEST',
    FETCH_GROUPS_SUCCESS: 'FETCH_GROUPS_SUCCESS',
    FETCH_GROUPS_FAILURE: 'FETCH_GROUPS_FAILURE',

    //SIGNUP
    SIGN_UP_REQUEST: 'SIGN_UP_REQUEST',
    SIGN_UP_SUCCESS: 'SIGN_UP_SUCCESS',
    SIGN_UP_FAILURE: 'SIGN_UP_FAILURE'

};

export const urls = {
    BASE_API: 'http://localhost:8080/regsystem/api'
}

export const facultyConstants = {
    ENGINEERING_FACULTY: 'Инженерии и естественных наук',
    LAW_FACULTY: 'Закона и социальных наук',
    PHILOSOPHY_FACULTY: 'Философии',
    ECONOMY_FACULTY: 'Экономиста'
}