import React, { useState } from 'react';
import Modal from '@mui/material/Modal';
import * as Yup from "yup";
import {emailVerification, register} from "../../../../services/auth.service.mjs";
import {ErrorMessage, Field, Form, Formik} from "formik";
import {Checkbox, FormControlLabel} from "@mui/material";

const UsernamePasswordInput = ({ open, onClose, onSubmit }:any) => {
    const [message, setMessage] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);

    const initialValues: {
        username: string;
        password: string;
    } = {
        username: "",
        password: ""
    };

    const handleOpenLogin = () => {

    }

    const validationSchema = Yup.object().shape({
        email: Yup.string()
            .required("This field is required!"),
    });

    const handleSignUp = (formValue: { username: string, password: string }) => {
        const {username, password} = formValue;

        setMessage("");
        setLoading(true);

        register(username, password)
            .then(
                () => {

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
                    onSubmit={handleSignUp}
                >
                    <Form>
                        <div className="modal-content">
                            <div className="modalHeader">
                                <h3 className="modalHeadline">CREATE YOUR ACCOUNT</h3>
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
                            <div className="form-group">
                                <label htmlFor="password" className="heading">Re-enter Password</label>
                                <Field name="password" type="password" className="form-control"/>
                                <ErrorMessage
                                    name="password"
                                    component="div"
                                    className="alert-danger"
                                />
                            </div>
                            <div className="accept-privacy-policy">
                                <FormControlLabel control={<Checkbox className="checkbox" checked={rememberMe}
                                                                     onChange={(e) => setRememberMe(e.target.checked)}/>}
                                                  label="Remember me?"
                                                  className="accept-privacy-policy-checkbox"/>
                            </div>
                            <div className="form-group">
                                <button type="submit" className="loading-btn">
                                    {loading && (
                                        <span className="spinner-border spinner-border-sm"></span>
                                    )}
                                    <span className="btn-text">CONTINUE</span>
                                </button>
                            </div>

                            <div className="signup-option">
                                <label className="signup-option-text">Already have an account? </label>
                                <label className="signup-option-text-link" onClick={handleOpenLogin}>LOGIN</label>
                            </div>


                        </div>
                    </Form>
                </Formik>
            </div>
        </div>
    );
};

export default UsernamePasswordInput;