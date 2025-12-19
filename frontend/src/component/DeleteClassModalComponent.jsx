import React, {Component} from "react";
import {Modal,Button} from "react-bootstrap";
import {deleteById, getAll} from "../service/studentService.js";


class DeleteClassModalComponent extends Component{
    constructor(props) {
        super(props);

    }
    handleClose =()=>{
        // chưa code
        this.props.closeModal();

    }
    handleDelete=()=>{

        deleteById(this.props.deleteStudent.id);
        console.log(getAll());
        this.props.closeModal();
        // xoá
    }

    render() {
        console.log("-----delete run-------")
        return(
            <>
                <Modal show={this.props.showModal} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Modal heading</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        Bạn muốn xoá sinh viên <span className={'text-danger'}>{this.props.deleteStudent.name}</span>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.handleClose}>
                            Close
                        </Button>
                        <Button variant="danger" onClick={this.handleDelete}>
                            Delete
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}
export default DeleteClassModalComponent ;