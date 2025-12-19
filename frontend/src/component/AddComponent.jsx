import {addNew} from "../service/studentService.js";
import {useEffect, useRef, useState} from "react";
import {useNavigate} from "react-router";
import {toast} from "react-toastify";
import {ErrorMessage, Field, Form, Formik} from "formik";
import {Button} from "react-bootstrap";
import * as Yup from "yup";
import {getAllClass} from "../service/classService.js";
import viteLogo from "/vite.svg";
const AddComponent =()=>{
    const [student,setStudent] = useState({
        name:"",
        classCG:""
    })
    const [classList, setClassList] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        const fetData = async ()=>{
            const data = await getAllClass();
            console.log("----------add fetdata---------")
            console.log(data)
            setClassList(data);
        }
        fetData();
    }, []);
    const handleAdd = (values) => {
        console.log(values);       values = {
            ...values,
            classCG: JSON.parse(values.classCG)
        }
        console.log(values);
        const fetData = async ()=>{
            const isSuccess = await addNew(values);
            if(isSuccess){
                toast.success("Thêm mới thành công",{
                    position:"top-right",
                    theme:"dark",
                    autoClose: 500
                });
            }else {
                toast.error("Thêm mới thất bại",{
                    position:"top-right",
                    theme:"dark",
                    autoClose: 500
                });
            }
            navigate("/students");
        }
       fetData();

    }
    const validation = Yup.object({
        classCG: Yup.string().required("Yêu cầu chọn lớp"),
        name:Yup.string().required('Tên không để trống')
            .matches(/^[A-Z][a-z]*(\s[A-Z][a-z]*)+$/,'Tên không đúng định dạng')
    })

    return(
        <>
            <Formik initialValues={student}
                    onSubmit={handleAdd}
                    validationSchema={validation}
            >
            <Form>
                <div>
                    <label>Name</label>
                    <Field type = "text" name={"name"}/>
                    <ErrorMessage className={'text-danger'} name={'name'} component={'small'}/>
                </div>

                <div>
                    <label>Class name</label>
                    <Field as={'select'} name={"classCG"}>
                        <option value="">--- ---Chọn lớp---------- </option>
                        {
                            classList&&classList.map((classCG)=>(
                               <option key={classCG.id} value={JSON.stringify(classCG)}>{classCG.name}</option>)
                            )
                        }
                    </Field>
                    <ErrorMessage className={'text-danger'} name={'classCG'} component={'small'}/>
                </div>

                <Button className={'btn-success btn-sm btn'} type={'submit'}>Thêm mới</Button>
            </Form>
            </Formik>
        </>
    );
}
export default AddComponent;