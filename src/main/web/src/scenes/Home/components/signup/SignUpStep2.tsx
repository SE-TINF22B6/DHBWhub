import React, {useState} from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {styled} from '@mui/system';
import DividerWithText from '../login/DividerWithText';
import './SignUpStep2.css';
import {Checkbox, FormControl, FormControlLabel, OutlinedInput} from '@mui/material';
import AlternativeLoginMethods from "../login/AlternativeLoginMethods";
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

export default function SignUpStep2() {
    const [open, setOpen] = useState(false);
    const [email, setEmail] = useState('');
    const [password] = useState('');
    const [reEnterPassword] = useState('');

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const handleContinueClick = () => {

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
                        <h3 className="heading"><b>CREATE YOUR ACCOUNT</b></h3>
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
                                    value={password}
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                                <MyFormHelperText/>
                            </FormControl>
                        </form>
                    </Typography>
                    <Typography sx={{mt: 2}}>
                        <h5 className="heading">Re-enter Password</h5>
                        <form noValidate autoComplete="off">
                            <FormControl sx={{marginBottom: '25px'}} className="textfield">
                                <OutlinedInput
                                    value={reEnterPassword}
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                                <MyFormHelperText/>
                            </FormControl>
                        </form>
                    </Typography>
                    <Typography sx={{mt: 2}}>
                        <FormControlLabel control={<Checkbox defaultChecked/>} label="Remeber me?"/>
                    </Typography>
                    <Typography>
                        <StyledButton variant="contained" onClick={handleContinueClick} style={{width: '100%'}}>
                            CONTINUE
                        </StyledButton>
                    </Typography>
                    <Typography id="modal-modal-description" sx={{mt: 2}} className="signInContent">
                        <span style={{ display: 'inline' }}>Already have an account?{' '}</span>
                        <div className="login-link">
                            <Link>Login</Link>
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


