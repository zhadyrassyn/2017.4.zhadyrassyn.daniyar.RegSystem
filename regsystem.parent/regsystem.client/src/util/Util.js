export const utils = {
    getKeyByValue
}
function getKeyByValue(value, object) {
    for(var property in object) {
        if(object[property] === value) {
            return property
        }
    }
}