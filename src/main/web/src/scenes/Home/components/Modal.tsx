import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { styled } from '@mui/system';
import DividerWithText from './DividerWithText';
import './Modal.css';
import { FormControl, OutlinedInput } from '@mui/material';

const style = {
    position: 'absolute' as 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: '#3B454F',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

const buttonStyles = {
    backgroundColor: 'red',
    '&:hover': {
        backgroundColor: 'darkred',
    },
};

const StyledButton = styled(Button)(buttonStyles);

function MyFormHelperText() {
    return null;
}

export default function ModalComponent() {
    const [open, setOpen] = useState(false);
    const [email, setEmail] = useState('');

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const handleContinueClick = () => {
        console.log('E-Mail:', email);
    };

    return (
        <>
            <Button onClick={handleOpen}>Log In</Button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style} className="modal">
                    <Typography id="modal-modal-title" variant="h6" component="h2" className="modalHeader">
                        <h3 className="heading"><b>LOGIN</b></h3>
                    </Typography>
                    <Typography sx={{ mt: 2 }}>
                        <h5 className="heading">Email address</h5>
                        <form noValidate autoComplete="off">
                            <FormControl sx={{ marginBottom: '25px' }} className="textfield">
                                <OutlinedInput
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                                <MyFormHelperText />
                            </FormControl>
                        </form>
                    </Typography>
                    <Typography>
                        <StyledButton variant="contained" onClick={handleContinueClick} style={{ width: '100%' }}>
                            CONTINUE
                        </StyledButton>
                    </Typography>
                    <Typography id="modal-modal-description" sx={{ mt: 2 }} className="signInContent">
                        Need an account? <u>SIGN UP</u>
                    </Typography>
                    <Typography sx={{ mt: 2 }}>
                        <DividerWithText />
                    </Typography>
                </Box>
            </Modal>
        </>
    );
}
