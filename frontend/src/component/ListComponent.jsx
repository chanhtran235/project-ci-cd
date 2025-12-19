import {useEffect, useRef, useState} from "react";
import {addNew, getAll} from "../service/studentService.js";
import DeleteComponent from "./DeleteComponent.jsx";
import {Link} from "react-router";


const ListComponent = () => {
    // useState
    const [studentList, setStudentList] = useState(null);
    const [deleteStudent, setDeleteStudent] = useState({id: 0, name: "no name"});
    const [showModal, setShowModal] = useState(false);
    const [reloading, setReloading] = useState(false);


    useEffect(() => {
        console.log("----effect---run---")

        // getAll().then(fake-data =>{
        //     setStudentList(fake-data);
        // })
        const fetData = async ()=>{
            const data = await getAll();
            setStudentList(data);
        }
        fetData();

    }, [reloading]);
    const handleShowModal = (student) => {
        setDeleteStudent(student);
        setShowModal(true);
    }

    const closeModal = () => {
        setReloading(pre => !pre);
        setShowModal(false);
    }

    return (
        <>
            {console.log("-------list----------")}
            <Link className="btn btn-sm btn-success" to="/students/add">Thêm mới</Link>
            <h1 style={{color: "red"}}>Danh sách sinh viên</h1>
            <table className="table table-dark">
                <thead>
                <tr>
                    <td>STT</td>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Class name</td>
                    <td>Delete</td>
                </tr>
                </thead>
                <tbody>
                {
                   studentList && studentList.map((student, i) => (
                        <tr key={student.id}>
                            <td>{i + 1}</td>
                            <td>{student.id}</td>
                            <td>{student.name}</td>
                            <td>{student.classCG?.name}</td>
                            <td>
                                <button onClick={() => {
                                    handleShowModal(student)
                                }} className={'btn btn-sm btn-danger'}>
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
            {showModal &&
                <DeleteComponent deleteStudent={deleteStudent}
                                           showModal={showModal}
                                           closeModal={closeModal}
                />
            }

        </>
    );
}
export default ListComponent;