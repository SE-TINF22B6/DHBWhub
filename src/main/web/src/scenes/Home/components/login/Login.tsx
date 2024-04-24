import React, {useState} from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {styled} from '@mui/system';
import DividerWithText from './DividerWithText';
import './Login.css';
import LoginStep2 from './LoginStep2';
import {FormControl, OutlinedInput} from '@mui/material';
import AlternativeLoginMethods from "./AlternativeLoginMethods";

const style = {
    position: 'absolute' as 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    height: 560,
    backgroundColor: 'var(--component-grey)',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

const buttonStyles = {
    backgroundColor: 'var(--red)',
    transition: 'var(--transition)',
    '&:hover': {
        backgroundColor: 'var(--dark-red)',
    },
};

const StyledButton = styled(Button)(buttonStyles);

function MyFormHelperText() {
    return null;
}

export function handleOpenModal(setOpen: React.Dispatch<React.SetStateAction<boolean>>) {
    setOpen(true);
}

export default function Login() {
    const [open, setOpen] = useState(false);
    const [email, setEmail] = useState('');
    const [openLoginStep2, setOpenLoginStep2] = useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const sendEmail = async (email: any) => {
        const backendAPIEndpoint = 'http://localhost:8080/api/auth/login';

        try {
            return await fetch(backendAPIEndpoint, {
                method: 'POST',
                mode: 'no-cors',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({email}),
                credentials: 'include',
            });
        } catch (error) {
            console.error('Fetch error:', error);
            throw error;
        }

    }

    const handleContinueClick = () => {
        sendEmail(email)
            .then(response => {
                if (response.status === 200) {
                    console.log('E-Mail gefunden');
                } else if (response.status === 404) {
                    console.log('E-Mail nicht gefunden');
                }
            })
            .catch(error => {
                console.error('Fetch error:', error);
            });
        setOpenLoginStep2(true);
    };

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
            >
                <Box sx={style} className="modal">
                    <Typography id="modal-modal-title" variant="h6" component="h2" className="modalHeader">
                        <h3 className="heading"><b>LOGIN</b></h3>
                    </Typography>
                    <Typography sx={{mt: 2}}>
                        <h5 className="heading">Email address</h5>
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
                    <Typography sx={{mt: 2}}>
                        <h5 className="heading">Password</h5>
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
                    <Typography>
                        <StyledButton variant="contained" onClick={handleContinueClick} style={{width: '100%'}}>
                            CONTINUE
                        </StyledButton>
                        {openLoginStep2 ? <LoginStep2/> : null}
                    </Typography>
                    <Typography id="modal-modal-description" sx={{mt: 2}} className="signInContent">
                        Need an account? <u>SIGN UP</u>
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


