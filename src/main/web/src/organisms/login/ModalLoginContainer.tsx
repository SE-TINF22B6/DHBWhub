import React, {useState} from 'react';
import Modal from '@mui/material/Modal';
import './ModalLoginContainer.css';
import Login from "./Login";

export default function ModalLoginContainer() {
    const [open, setOpen] = useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <>
            <button className="login" onClick={handleOpen}>
                <div className="log-in-wrapper">
                    <div className="log-in-text">LOGIN</div>
                </div>
            </button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
                sx={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    width: '100%',
                    height: '100%',
                    borderRadius: '16px',
                    boxShadow: '0 5px 20px rgba(0, 0, 0, 0.2)',
                    padding: '40px',
                    boxSizing: 'border-box',
                }}
            >
              <div>
                <Login/>
              </div>
            </Modal>
        </>
    );
}


