import React, {useState} from 'react';
import Modal from '@mui/material/Modal';
import {ErrorMessage, Field, Form, Formik} from "formik";
import * as Yup from "yup";
import {emailVerification, tokenValidation} from "../../../../services/auth.service.mjs";

const VerificationCodeInput = () => {
    const [verificationCode, setVerificationCode] = useState('');
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState<boolean>(false);

    const initialValues: {
        token: number;
    } = {
        token: 0,
    };

    const handleOpenLogin = () => {

    }

    const validationSchema = Yup.object().shape({
        email: Yup.number()
            .integer("This is not a verification token!")
            .required("This field is required!"),
    });

    const handleValidation = (formValue: { token: number }) => {
        const {token} = formValue;

        setMessage("");
        setLoading(true);

        tokenValidation(token)
            .then(
                () => {
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
                    onSubmit={handleValidation}
                >
                    <Form>
                        <div className="modal-content">
                            <div className="modalHeader">
                                <h3 className="modalHeadline">VERIFY YOUR EMAIL</h3>
                            </div>
                            <div>
                                <div className="form-group">
                                    <label htmlFor="verification-code" className="heading">Verification Code</label>
                                    <Field name="verification-code" type="text" className="form-control"/>
                                    <ErrorMessage
                                        name="verification-code"
                                        component="div"
                                        className="alert-danger"
                                    />
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
                        </div>
                    </Form>
                </Formik>
            </div>
        </div>
    );

};

export default VerificationCodeInput;