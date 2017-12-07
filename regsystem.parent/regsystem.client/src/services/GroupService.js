export const groupService = {
    fetchGroups
}
function fetchGroups(request) {

    const params = '?course=' + request.course + '&faculty=' + request.faculty;
    return fetch('http://localhost:8080/regsystem/api/groups' + params)
        .then(response => {
            if(!response.ok) {
                return Promise.reject(response.statusText)
            }

            return response.json()
        })
}