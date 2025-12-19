import axios from "axios";

const URL_BE = import.meta.env.VITE_API_URL;
// viết phương thức để callAPI
export async function getAllClass(){
    try{
       
        const response = await axios.get(`${URL_BE}/v1/api/classes`);
        return response.data;
    }catch (e) {
        console.log(e.message);
    }
    return [];

}