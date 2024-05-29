import React, {useState} from "react";
import {NavigateFunction, useNavigate} from 'react-router-dom';
import {Formik, Field, Form, ErrorMessage} from "formik";
import * as Yup from "yup";
import './Login.css';
import {login} from "../../services/AuthService";
import {Checkbox, FormControlLabel} from "@mui/material";
import EmailInput from "../signup/EmailInput";
import {GoogleLogin} from "@react-oauth/google";

type Props = {}

const Login: React.FC<Props> = () => {
    let navigate: NavigateFunction = useNavigate();

    const [showSignUp, setShowSignUp] = useState(false);
    const [loading, setLoading] = useState<boolean>(false);
    const [message, setMessage] = useState<string>("");
    const [rememberMe, setRememberMe] = useState(false);

    const initialValues: {
        username: string;
        password: string;
        rememberMe: boolean;
    } = {
        username: "",
        password: "",
        rememberMe: false
    };

    const validationSchema = Yup.object().shape({
        username: Yup.string().required("This field is required!"),
        password: Yup.string().required("This field is required!"),
    });

    const handleOpenSignUp = (): void => {
        setShowSignUp(true);
    };

    const handleLogin = (formValue: { username: string; password: string; rememberMe: boolean }): void => {
        const {username, password, rememberMe} = formValue;

        setMessage("");
        setLoading(true);

        login(username, password, rememberMe).then(
            () => {
                navigate("/profile");
                window.location.reload();
            },
            (error) => {
                const resMessage =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setLoading(false);
                setMessage(resMessage);
            }
        );
    };

    return (
        <div className="col-md-12">
            <div className="card card-container">
                <Formik
                    initialValues={initialValues}
                    validationSchema={validationSchema}
                    onSubmit={handleLogin}
                >
                    <Form>
                        <div className="modal-content">
                            <div className="modalHeader">
                                <h3 className="modalHeadline">LOGIN</h3>
                            </div>
                            <div className="form-group">
                                <label htmlFor="username" className="heading">Username</label>
                                <Field name="username" type="text" className="form-control"/>
                                <ErrorMessage
                                    name="username"
                                    component="div"
                                    className="alert-danger"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="password" className="heading">Password</label>
                                <Field name="password" type="password" className="form-control"/>
                                <ErrorMessage
                                    name="password"
                                    component="div"
                                    className="alert-danger"
                                />
                            </div>

                            <div className="forgot-password-option">
                                <label className="forgot-password-option-text">Forgot Password?</label>
                            </div>

                            <div className="remember-me-option">
                                <FormControlLabel control={<Checkbox className="checkbox" checked={rememberMe}
                                                                     onChange={(e) => setRememberMe(e.target.checked)}/>}
                                                  label="Remember me?"
                                                  className="remember-me-checkbox"/>
                            </div>

                            <div className="form-group">
                                <button type="submit" className="loading-btn" disabled={loading}>
                                    {loading && (
                                        <span className="spinner-border spinner-border-sm"></span>
                                    )}
                                    <span className="btn-text">Login</span>
                                </button>
                            </div>

                            <div className="signup-option">
                                <label className="signup-option-text">Need an account? </label>
                                <label className="signup-option-text-link" onClick={handleOpenSignUp}>SIGN UP</label>
                            </div>

                            <div className="google-oauth-login">
                                <GoogleLogin size={'medium'} logo_alignment={'center'} ux_mode={'popup'} useOneTap={true}
                                             text={"continue_with"}
                                    onSuccess={credentialResponse => {
                                        console.log(credentialResponse);
                                    }}
                                    onError={() => {
                                        console.log('Login Failed');
                                    }}
                                />
                            </div>

                            {showSignUp && <EmailInput/>}

                            {message && (
                                <div className="form-group">
                                    <div className="alert-danger" role="alert">
                                        {message}
                                    </div>
                                </div>
                            )}
                        </div>
                    </Form>
                </Formik>
            </div>
        </div>
    );
};

export default Login;