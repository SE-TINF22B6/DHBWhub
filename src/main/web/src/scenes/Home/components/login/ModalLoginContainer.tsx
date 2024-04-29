import React, {useState} from 'react';
import Modal from '@mui/material/Modal';
import './ModalLoginContainer.css';
import Login from "./Login";

export default function ModalLoginContainer() {
    const [open, setOpen] = useState(false);
    const [email, setEmail] = useState('');
    const [openLoginStep2, setOpenLoginStep2] = useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <>
            <button className="login" onClick={handleOpen}>
                <div className="log-in-wrapper">
                    <div className="log-in-text">LOGIN</div>
                </div>
            </button>
            <div className="modal-conatiner">
                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                >
                    <Login />
                </Modal>
            </div>
        </>
    );
}


