import {constants} from "../constants/Constants";
import {groupService} from "../services/GroupService";

const fetchGroupsRequest = () => {
    return {
        type: constants.FETCH_GROUPS_REQUEST,
        groupItems: []
    }
}

const fetchGroupsSuccess = (items) => {
    return {
        type: constants.FETCH_GROUPS_SUCCESS,
        groupItems: items
    }
}

const fetchGroupsFailure = (error) => {
    return {
        type: constants.FETCH_GROUPS_FAILURE,
        error
    }
}

export const fetchGroups = (request, onError, onSuccess) => dispatch => {

    dispatch(fetchGroupsRequest())
    groupService.fetchGroups(request).then(
        items => {
            console.log(items)
            dispatch(fetchGroupsSuccess(items))
            onSuccess()
        },
        error => {
            dispatch(fetchGroupsFailure(error));
            alert("Произошла ошибка при получении данных")
            onError()
        }
    )
}
