import React, { useState } from "react";
import "./EmailInput.css";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";
import {emailVerification, googleLogin} from "../../services/AuthService";
import ErrorModal from "./ErrorModal";
import {CredentialResponse, GoogleLogin} from "@react-oauth/google";
import {NavigateFunction, useNavigate} from "react-router-dom";

const EmailInput = ({ onSuccess }: any) => {
  const [message, setMessage] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const [showError, setShowError] = useState<boolean>(false);
  const [isInputFocused, setIsInputFocused] = useState<boolean>(false);
  let navigate: NavigateFunction = useNavigate();

  const initialValues: {
    email: string;
  } = {
    email: "",
  };

  const handleOpenLogin = () => {};

  const handleInputFocus = () => {
    setShowError(false);
  };

  const validationSchema = Yup.object().shape({
    email: Yup.string()
      .email("This is not a valid email.")
      .required("This field is required!"),
  });

  const handleVerify = (formValue: { email: string }) => {
    const { email } = formValue;

    setMessage("");
    setLoading(true);

    localStorage.setItem("userEmailAddress", email);

    emailVerification(email).then(
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

        if (error.message === "Request failed with status code 400") {
          setShowError(true);
        }

        setLoading(false);
        setMessage(resMessage);
      }
    );
  };

  const handleGoogleLogin = (credentialResponse: CredentialResponse) => {
    console.log(credentialResponse);
    googleLogin(JSON.stringify({ token: credentialResponse.credential })).then(
        () => {
          navigate("/profile");
          window.location.reload();
        },
        () => {
          const resMessage = "Unable to sign in via Google";
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
          <p className="info-text">
            Please note that it is required to verify your email address by
            entering a verification code.
          </p>
          <div className="form-group">
            <label htmlFor="email" className="heading">
              Email
            </label>
            <Field
              name="email"
              type="text"
              className="form-control"
              onFocus={() => setIsInputFocused(true)}
              onBlur={() => setIsInputFocused(false)}
            />
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
            <label className="signup-option-text">
              Already have an account?{" "}
            </label>
            <label
              className="signup-option-text-link"
              onClick={handleOpenLogin}
            >
              LOGIN
            </label>
          </div>
        </Form>
      </Formik>

      <div className="google-oauth-signup">
        <GoogleLogin
          size={"medium"}
          logo_alignment={"center"}
          ux_mode={"popup"}
          useOneTap={true}
          text={"signup_with"}
          onSuccess={handleGoogleLogin}
          onError={() => {
            console.log("Login Failed");
          }}
        />
      </div>

      <div className="error-message-dialog">
        {showError && !isInputFocused && (
          <ErrorModal message={message} onClose={() => setShowError(false)} />
        )}
      </div>
    </div>
  );
};

export default EmailInput;
