import React, {useState} from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import {styled} from '@mui/system';
import './Login.css';
import {
    Checkbox,
    FormControl,
    FormControlLabel,
    IconButton,
    InputAdornment,
    InputLabel,
    OutlinedInput
} from '@mui/material';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';

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

export default function ModalComponent(props: any) {
    const [open, setOpen] = useState(false);
    const [email, setEmail] = useState('');
    const [showPassword, setShowPassword] = React.useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    if (props === true) {
        handleOpen();
    }

    const handleClickShowPassword = () => setShowPassword((show) => !show);

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    const sendEmail = async (email: any) => {
        const backendAPIEndpoint = 'http://localhost:8080/login/email';
        console.log(email);

        fetch(backendAPIEndpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(email),
            credentials: 'include',
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error('Fetch error:', error);
            });
    }

    const handleContinueClick = () => {
        sendEmail(email);
        console.log('E-Mail:', email);
    };

    return (
        <>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style} className="modal">
                    <Typography id="modal-modal-title" variant="h6" component="h2" className="modalHeader">
                        <h3 className="heading"><b>ENTER YOUR PASSWORD</b></h3>
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
                        <FormControl sx={{ m: 1, width: '25ch' }} variant="outlined">
                            <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                            <OutlinedInput
                                id="outlined-adornment-password"
                                type={showPassword ? 'text' : 'password'}
                                endAdornment={
                                    <InputAdornment position="end">
                                        <IconButton
                                            aria-label="toggle password visibility"
                                            onClick={handleClickShowPassword}
                                            onMouseDown={handleMouseDownPassword}
                                            edge="end"
                                        >
                                            {showPassword ? <VisibilityOff /> : <Visibility />}
                                        </IconButton>
                                    </InputAdornment>
                                }
                                label="Password"
                            />
                        </FormControl>
                    </Typography>
                    <Typography id="modal-modal-description" sx={{mt: 2}} className="signInContent">
                        Forgot Password?
                    </Typography>
                    <Typography sx={{mt: 2}}>
                        <FormControlLabel control={<Checkbox defaultChecked />} label="Remeber me?" />
                    </Typography>
                    <Typography>
                        <StyledButton variant="contained" onClick={handleContinueClick} style={{width: '100%'}}>
                            CONTINUE
                        </StyledButton>
                    </Typography>
                    <Typography id="modal-modal-description" sx={{mt: 2}} className="signInContent">
                        Need an account? <u>SIGN UP</u>
                    </Typography>
                </Box>
            </Modal>
        </>
    );
}