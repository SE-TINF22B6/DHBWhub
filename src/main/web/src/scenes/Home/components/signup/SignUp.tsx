import React, {useState} from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {styled} from '@mui/system';
import DividerWithText from '../login/DividerWithText';
import './SignUp.css';
import {FormControl, OutlinedInput} from '@mui/material';
import AlternativeLoginMethods from "../login/AlternativeLoginMethods";
import EmailVerification from "./EmailVerification";

const style = {
    position: 'absolute' as 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    backgroundColor: 'var(--component-grey)',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

const buttonStyles = {
    backgroundColor: 'var(--red)',
    '&:hover': {
        backgroundColor: 'var(--dark-red)',
    },
};

const StyledButton = styled(Button)(buttonStyles);

function MyFormHelperText() {
    return null;
}

export function handleOpenSignUpModal(setOpen: React.Dispatch<React.SetStateAction<boolean>>) {
    setOpen(true);
}

export default function SignUp() {
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
                <Box sx={style} className="signup-modal">
                    <Typography id="modal-modal-title" variant="h6" component="h2" className="signup-modal-header">
                        <h3 className="signup-heading"><b>CREATE YOUR ACCOUNT</b></h3>
                    </Typography>
                    <Typography sx={{mt: 2}}>
                        <p className="paragraph">Please note that it is required to verify your email address by
                            entering a verification code.</p>
                    </Typography>
                    <Typography sx={{mt: 2}}>
                        <h5 className="signup-heading">Email address</h5>
                        <form noValidate autoComplete="off">
                            <FormControl sx={{marginBottom: '25px'}} className="textfield">
                                <OutlinedInput
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                                <MyFormHelperText/>
                            </FormControl>
                        </form>
                    </Typography>
                    {showEmailVerification ? (
                        <EmailVerification/>
                    ) : (
                        <></>
                    )}
                    <Typography>
                        <StyledButton variant="contained" onClick={handleContinueClick} style={{width: '100%'}}>
                            CONTINUE
                        </StyledButton>
                    </Typography>
                    <Typography id="modal-modal-description" sx={{mt: 2}} className="signup-content">
                        <span style={{display: 'inline'}}>Already have an account?{' '}</span>
                        <div className="login-link">
                            <u style={{textDecoration: 'underline', color: '#989494'}}>LOGIN</u>
                        </div>
                    </Typography>
                    <Typography sx={{mt: 2}}>
                        <DividerWithText/>
                    </Typography>
                    <Typography sx={{mt: 2}}>
                        <AlternativeLoginMethods/>
                    </Typography>
                </Box>
            </Modal>
        </>
    );
}


