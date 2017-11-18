export const authService = {
  login
}
function login(user) {
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({username: user.username, password: user.password})
  }

  // const params = 'username=' + user.username + '&password=' + user.password;
  return fetch('http://localhost:8080/rest/auth', requestOptions)
    .then(response => {
      if(!response.ok) {
        return Promise.reject(response.statusText)
      }

      return response.json()
    })
    .then(user => {
      if(user && user.token) {
        localStorage.setItem('user', JSON.stringify(user))
      }
      return user
    })
}