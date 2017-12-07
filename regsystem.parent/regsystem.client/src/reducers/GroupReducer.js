import {constants} from "../constants/Constants";

export default (state = {groupItems: []}, action) => {
    switch (action.type) {
        case constants.FETCH_GROUPS_REQUEST:
            return {
                ...state,
                groupItems:[]
            }
        case constants.FETCH_GROUPS_SUCCESS:
            return {
                ...state,
                groupItems: action.groupItems,
            }
        case constants.FETCH_GROUPS_FAILURE:
            return {
                ...state,
            }
        default:
            return state;
    }
}
