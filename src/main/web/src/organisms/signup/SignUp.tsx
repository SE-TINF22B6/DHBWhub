import React, {useState} from "react";
import Modal from "@mui/material/Modal";
import './SignUp.css';
import EmailInput from "./EmailInput";
import VerificationCodeInput from "./VerificationCodeInput";
import UsernamePasswordInput from "./UsernamePasswordInput";

export default function SignUp() {
    const [open, setOpen] = useState(false);
    const [showEmailVerification, setShowEmailVerification] = useState(false);
    const [showUsernamePasswordInput, setShowUsernamePasswordInput] = useState(false); // Zustand für UsernamePasswordInput hinzufügen

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const handleVerifyEmailSuccess = (): void => {
        setShowEmailVerification(true);
    };

    const handleVerificationSuccess = (): void => {
        setShowEmailVerification(false);
        setShowUsernamePasswordInput(true);
    };

    return (
        <>
            <button className="signup" onClick={handleOpen}>
                <div className="signup-wrapper">
                    <div className="signup-text">SIGN UP</div>
                </div>
            </button>
            <Modal open={open} onClose={handleClose} aria-labelledby="modal-modal-title" aria-describedby="modal-modal-description" tabIndex={0}>
                {showEmailVerification ? (
                    <VerificationCodeInput onSuccess={handleVerificationSuccess}/>
                ) : showUsernamePasswordInput ? (
                    <UsernamePasswordInput/>
                ) : (
                    <EmailInput onSuccess={handleVerifyEmailSuccess}/>
                )}
            </Modal>
        </>
    );
}