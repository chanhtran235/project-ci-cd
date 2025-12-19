import axios from "axios";

const URL_BE = import.meta.env.VITE_API_URL;
// viết phương thức để callAPI
export async function checkUserLogin(username, password){
    try{
        const response = await axios.get(`${URL_BE}/v1/api/users?username=${username}`);
        if (response.data.length===0){
            console.log("username không tồn tại")
            return null;
        }
        if (response.data[0].password===password){
            return response.data[0];
        }else {
            console.log("sai mật khẩu")
            return null;
        }
        // trả về user

    }catch (e) {
        console.log(e.message);
    }
    return null;

}