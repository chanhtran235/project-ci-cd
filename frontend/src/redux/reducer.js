import {combineReducers} from "redux";
// giá trị khởi tạo cho state
const initAuth = {
    account: null,
    token: null
}
const authReducer = (state = initAuth, action) => {
    switch (action.type) {
        case "LOGIN_SUCCESS":
            return {
                ...state,
                account: action.payload
            }
        case "LOGOUT":
            return {
                ...state,
                account: null
            }

        default:
            return state;
    }
}
// giá trị khởi tao cho state
const student = {
    studentList: [],
    newStudent: null
};

const studentReducer = (state = student, action) => {
    switch (action.type) {
        case "LIST":
            return {
                ...state,
                studentList: action.payload
            }
        case "ADD":
            return {
                ...state,
                newStudent: action.payload
            }
        default:
            return state;
    }
}

// tạo rootReducer

export const rootReducer = combineReducers({
    auth: authReducer,
    student: studentReducer
})