import {Component} from "react";
import {getAll} from "../service/studentService.js";
import DeleteClassModalComponent from "./DeleteClassModalComponent.jsx";



class ListClassComponent extends Component{

    constructor(props) {
        super(props);
        this.state = {
            studentList: [],
            showModal : false,
            deleteStudent :{
                id:"",
                name:""
            }
        }
    }

    componentDidMount() {
        console.log("-------did mount----run----------")
        this.setState({
            studentList:[...getAll()]
        })
    }
    componentDidUpdate(prevProps, prevState, snapshot) {
        if (prevState.showModal!==this.state.showModal){
            this.setState({
                studentList:[...getAll()]
            })
        }
    }

    handleShowModal = (student)=>{
        this.setState({
            showModal: true,
            deleteStudent: {
               ...student
            }
        })
    }
    closeModal =()=>{
        this.setState({
            showModal: false,
        })
    }

    render() {
        console.log("-------list render --------------")
        return (
            <>
                <h1>Danh sách sinh viên</h1>
                <table className="table table-dark">
                    <thead>
                    <tr>
                        <td>STT</td>
                        <td>ID</td>
                        <td>Name</td>
                        <td>Delete</td>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.studentList.map((student, i) => (
                            <tr key={student.id}>
                                <td>{i + 1}</td>
                                <td>{student.id}</td>
                                <td>{student.name}</td>
                                <td>
                                    <button onClick={()=>{
                                        this.handleShowModal(student)
                                    }} className={'btn btn-sm btn-danger'}>Delete</button>
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
                <DeleteClassModalComponent deleteStudent = {this.state.deleteStudent}
                                           showModal = {this.state.showModal}
                                           closeModal = {this.closeModal}
                />
            </>
        );
    }
}
export default ListClassComponent ;