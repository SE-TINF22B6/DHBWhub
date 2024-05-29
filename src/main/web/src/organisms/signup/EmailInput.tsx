import React, {useState} from 'react';
import './EmailInput.css';
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";
import {emailVerification} from "../../services/AuthService";
import {GoogleLogin} from "@react-oauth/google";

const EmailInput = ({onSuccess}: any) => {
    const [message, setMessage] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);

    const initialValues: {
        email: string;
    } = {
        email: "",
    };

    const handleOpenLogin = () => {

    }

    const validationSchema = Yup.object().shape({
        email: Yup.string()
            .email("This is not a valid email.")
            .required("This field is required!"),
    });

    const handleVerify = (formValue: { email: string }) => {
        const {email} = formValue;

        setMessage("");
        setLoading(true);

        localStorage.setItem("userEmailAddress", email);

        emailVerification(email)
            .then(
                () => {
                    onSuccess();
                },
                (error) => {
                    let resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    if (error.message === "Error: Email is already in use!") {
                        resMessage = "The Email is already in use, please try again.";
                    }

                    setLoading(false);
                    setMessage(resMessage);
                }
            );
    };

    return (
        <div className="email-input-modal-content">
          <Formik
              initialValues={initialValues}
              validationSchema={validationSchema}
              onSubmit={handleVerify}
          >
            <Form>
              <div className="modalHeader">
                <h3 className="modalHeadline">CREATE YOUR ACCOUNT</h3>
              </div>
              <p className="info-text">Please note that it is required to verify your email address by entering a verification
                code.</p>
              <div className="form-group">
                <label htmlFor="email" className="heading">Email</label>
                <Field name="email" type="text" className="form-control"/>
                <ErrorMessage
                    name="email"
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
            </Form>
          </Formik>

          <div className="google-oauth-signup">
            <GoogleLogin size={'medium'} logo_alignment={'center'} ux_mode={'popup'} useOneTap={true} text={"signup_with"}
                         onSuccess={credentialResponse => {
                           console.log(credentialResponse);
                         }}
                         onError={() => {
                           console.log('Login Failed');
                         }}
            />
          </div>
        </div>
    );
};

export default EmailInput;