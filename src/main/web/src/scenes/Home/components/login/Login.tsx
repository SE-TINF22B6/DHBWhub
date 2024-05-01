import React, {useState} from "react";
import {NavigateFunction, useNavigate} from 'react-router-dom';
import {Formik, Field, Form, ErrorMessage} from "formik";
import * as Yup from "yup";
import './Login.css';

import {login} from "../../../../services/auth.service";
import {Checkbox, checkboxClasses, FormControlLabel} from "@mui/material";
import DividerWithText from "./DividerWithText";
import AlternativeLoginMethods from "./AlternativeLoginMethods";

type Props = {}

const Login: React.FC<Props> = () => {
    let navigate: NavigateFunction = useNavigate();

    const [loading, setLoading] = useState<boolean>(false);
    const [message, setMessage] = useState<string>("");

    const initialValues: {
        username: string;
        password: string;
    } = {
        username: "",
        password: "",
    };

    const validationSchema = Yup.object().shape({
        username: Yup.string().required("This field is required!"),
        password: Yup.string().required("This field is required!"),
    });

    const handleLogin = (formValue: { username: string; password: string }) => {
        const {username, password} = formValue;

        setMessage("");
        setLoading(true);

        login(username, password).then(
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
                        <div className="modal">
                            <div className="modalHeader">
                                <h3 className="modalHeadline">LOGIN</h3>
                            </div>
                            <div className="form-group">
                                <label htmlFor="username" className="heading">Username</label>
                                <Field name="username" type="text" className="form-control"/>
                                <ErrorMessage
                                    name="username"
                                    component="div"
                                    className="alert alert-danger"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="password" className="heading">Password</label>
                                <Field name="password" type="password" className="form-control"/>
                                <ErrorMessage
                                    name="password"
                                    component="div"
                                    className="alert alert-danger"
                                />
                            </div>

                            <div className="forgot-password-option">
                                <label className="forgot-password-option-text">Forgot Password?</label>
                            </div>

                            <div className="remember-me-option">
                                <FormControlLabel control={<Checkbox/>} label="Remember me?"
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
                                <label className="signup-option-text">Need an account?
                                    <label className="signup-option-text-link">SIGN UP</label>
                                </label>
                            </div>

                            {/*<div className="divider-with-text">*/}
                            {/*    <DividerWithText/>*/}
                            {/*</div>*/}

                            {/*<div className="alternative-login-methods">*/}
                            {/*    <AlternativeLoginMethods/>*/}
                            {/*</div>*/}

                            {message && (
                                <div className="form-group">
                                    <div className="alert alert-danger" role="alert">
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