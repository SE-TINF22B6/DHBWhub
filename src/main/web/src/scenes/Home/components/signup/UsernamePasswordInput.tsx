import React, { useState } from 'react';
import * as Yup from "yup";
import {emailVerification, register} from "../../../../services/auth.service.mjs";
import {ErrorMessage, Field, Form, Formik} from "formik";
import {Checkbox, FormControlLabel} from "@mui/material";
import {NavigateFunction, useNavigate} from "react-router-dom";
import './UsernamePasswordInput.css';

const UsernamePasswordInput = () => {
    let navigate: NavigateFunction = useNavigate();

    const [message, setMessage] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);
    const [acceptPrivacyPolicy, setAcceptPrivacyPolicy] = useState(false);

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
        username: Yup.string().required("This field is required!"),
        password: Yup.string().required("This field is required!"),
    });

    const handleSignUp = (formValue: { username: string, password: string }) => {
        const {username, password} = formValue;

        setMessage("");
        setLoading(true);

        const email = localStorage.getItem("userEmailAddress");

        if (!email) {
            throw new Error("Value for user email address is not available! Registration failed! ");
        } else {
            register(username, email, password)
                .then(
                    () => {
                        localStorage.removeItem("userEmailAddress");
                        navigate("/profile");
                        window.location.reload();
                    },
                    (error) => {
                        let resMessage =
                            (error.response &&
                                error.response.data &&
                                error.response.data.message) ||
                            error.message ||
                            error.toString();

                        if(error.message === "Error: Username is already taken!") {
                            resMessage = "The username is already in use, please try again.";
                        }

                        setLoading(false);
                        setMessage(resMessage);
                    }
                );
        }
    };

    return (
            <div className="username-password-input">
                <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={handleSignUp}>
                    <Form>
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
                            <label htmlFor="re-password" className="heading">Re-enter Password</label>
                            <Field name="re-password" type="password" className="form-control"/>
                            <ErrorMessage
                                name="re-password"
                                component="div"
                                className="alert-danger"
                            />
                        </div>
                        {/*<div className="accept-privacy-policy">*/}
                        {/*    <FormControlLabel control={<Checkbox className="checkbox" checked={acceptPrivacyPolicy}*/}
                        {/*                                         onChange={(e) => setAcceptPrivacyPolicy(e.target.checked)}/>}*/}
                        {/*                      label="I accept Terms of Service and Privacy Policy"*/}
                        {/*                      className="accept-privacy-policy-checkbox"/>*/}
                        {/*</div>*/}
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
                    </Form>
                </Formik>
            </div>
    );
};

export default UsernamePasswordInput;