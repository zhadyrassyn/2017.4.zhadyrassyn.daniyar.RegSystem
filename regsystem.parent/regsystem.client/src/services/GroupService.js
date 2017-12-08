import {urls} from "../constants/Constants";

export const groupService = {
    fetchGroups
}
function fetchGroups(request) {

    const params = '?course=' + request.course + '&faculty=' + request.faculty;
    return fetch(urls.BASE_API + '/groups' + params)
        .then(response => {
            if(!response.ok) {
                return Promise.reject(response.statusText)
            }

            return response.json()
        })
}