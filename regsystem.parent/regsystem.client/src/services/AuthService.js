import {urls} from "../constants/Constants";

export const authService = {
    login,
    register
}

function login(user) {
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(user)
    }

    // const params = 'username=' + user.username + '&password=' + user.password;
    return fetch(urls.BASE_API + '/sign/in', requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response.statusText)
            }

            return response.json()
        })
        .then(user => {
            if (user && user.token) {
                localStorage.setItem('user', JSON.stringify(user))
            }
            return user
        })
}

function register(user) {
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(user)
    }

    // const params = 'username=' + user.username + '&password=' + user.password;
    return fetch(urls.BASE_API + '/sign/up', requestOptions)
        .then(response => {
            if (!response.ok) {
                return Promise.reject(response.statusText)
            }

            return response.json()
        })
}