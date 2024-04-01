import React, {useState} from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {styled} from '@mui/system';
import './EmailVerification.css';
import {FormControl, OutlinedInput} from '@mui/material';
import SignUpStep2 from "./SignUpStep2";
import Link from "@mui/material/Link";

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

export function handleOpenModal(setOpen: React.Dispatch<React.SetStateAction<boolean>>) {
    setOpen(true);
}

export default function EmailVerification() {
    const [open, setOpen] = useState(false);
    const [email, setEmail] = useState('');
    const [showSignUpStep2, setShowSignUpStep2] = useState(false);

    const handleClose = () => setOpen(false);

    const handleContinueClick = () => {
        setShowSignUpStep2(true);
    };

    return (
        <>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style} className="email-verification-modal">
                    <Typography id="modal-modal-title" variant="h6" component="h2" className="email-verifcation-header">
                        <h3 className="heading"><b>VERIFY YOUR EMAIL</b></h3>
                    </Typography>
                    <Typography sx={{mt: 2}}>
                        <h5 className="heading">Verification Code</h5>
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
                    {showSignUpStep2 ? (
                        <SignUpStep2/>
                    ) : (
                        <></>
                    )}
                    <Typography>
                        <StyledButton variant="contained" onClick={handleContinueClick} style={{width: '100%'}}>
                            CONTINUE
                        </StyledButton>
                    </Typography>
                    <Typography id="modal-modal-description" sx={{mt: 2}} className="signInContent">
                        <span style={{ display: 'inline' }}>Already have an account?{' '}</span>
                        <div className="login-link">
                            <Link href="#">Login</Link>
                        </div>
                    </Typography>
                </Box>
            </Modal>
        </>
    );
}


