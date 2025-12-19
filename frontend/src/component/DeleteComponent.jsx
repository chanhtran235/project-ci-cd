import {Button, Modal} from "react-bootstrap";
import React from "react";
import {deleteById, getAll} from "../service/studentService.js";
import {boolean} from "yup";
import {toast} from "react-toastify";


const DeleteComponent = ({closeModal,deleteStudent,showModal})=>{
    const handleClose =()=>{
        // chưa code
        closeModal();

    }
   const handleDelete=()=>{
        const fetData =async ()=>{
           let isSuccess = await deleteById(deleteStudent.id);
            if(isSuccess){
                toast.success("Xoá thành công",{
                    position:"top-right",
                    theme:"dark",
                    autoClose: 500
                });
            }else {
                toast.error("Xoá thất bại",{
                    position:"top-right",
                    theme:"dark",
                    autoClose: 500
                });
            }
            closeModal();
        }
        fetData();

        // xoá
    }
    return (
        <>
            {console.log("------modal delete-------------")}
            <Modal show={showModal} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Bạn muốn xoá sinh viên <span className={'text-danger'}>{deleteStudent.name}</span>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="danger" onClick={handleDelete}>
                        Delete
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}
export default DeleteComponent ;