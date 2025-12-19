import {checkUserLogin} from "../service/userService.js";

export const checkLogin =  (userInfo) => {
    // call API
    return async (dispatch1) => {
        const loginUser = await checkUserLogin(userInfo.username, userInfo.password);
        if (loginUser) {
            dispatch1({
                type: "LOGIN_SUCCESS",
                payload: loginUser
            })
            return true;
        } else {
            dispatch1({
                type: "LOGIN_FAIL",
                payload: null
            })
            return false;
        }
    }


}
export const logout = () => {
    return (dispatch) => {
        dispatch({
            type: "LOGOUT",
            payload: null
        })
    }

}