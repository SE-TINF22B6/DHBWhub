import React, {useState} from "react";
import Modal from "@mui/material/Modal";
import './SignUp.css';
import SignUp from "./SignUp";

export default function ModalSignUpContainer() {
    const [open, setOpen] = useState(false);
    const [email, setEmail] = useState('');
    const [showEmailVerification, setShowEmailVerification] = useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const handleContinueClick = () => {
        setShowEmailVerification(true);
        handleClose();
    };

    return (
        <>
            <button className="signup" onClick={handleOpen}>
                <div className="signup-wrapper">
                    <div className="signup-text">SIGN UP</div>
                </div>
            </button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <SignUp/>
            </Modal>
        </>
    );
}