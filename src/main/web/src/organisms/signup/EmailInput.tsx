import React, { useState, forwardRef } from "react";
import "./EmailInput.css";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";
import {emailVerification, googleLogin} from "../../services/AuthService";
import ErrorModal from "./ErrorModal";
import {CredentialResponse, GoogleLogin} from "@react-oauth/google";
import {NavigateFunction, useNavigate} from "react-router-dom";

export const EmailInput = forwardRef<HTMLDivElement, { onSuccess: () => void }>(({ onSuccess }, ref) => {
  const [message, setMessage] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const [showError, setShowError] = useState<boolean>(false);
  const [isInputFocused, setIsInputFocused] = useState<boolean>(false);
  let navigate: NavigateFunction = useNavigate();
  const [hasGivenConsent, setHasGivenConsent] = useState<boolean>(false);
  const [consentError, setConsentError] = useState<string>("");
  const [containerHeight, setContainerHeight] = useState<string>("520px");

  const initialValues: {
    email: string;
  } = {
    email: "",
  };

  const handleConsentChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
    setHasGivenConsent(event.target.checked);
    if (event.target.checked) {
      setConsentError("");
      setContainerHeight("520px");
    }
  };

  const handleOpenLogin = (): void => {};

  const validationSchema = Yup.object().shape({
    email: Yup.string()
      .email("This is not a valid email.")
      .required("This field is required!"),
  });

  const handleVerify = (formValue: { email: string }): void => {
    if (!hasGivenConsent) {
      setConsentError("You must accept the Terms of Service and Privacy Policy.");
      setContainerHeight("600px");
      return;
    }

    const { email } = formValue;
    setMessage("");
    setLoading(true);

    localStorage.setItem("userEmailAddress", email);

    emailVerification(email).then(
        (): void => {
          onSuccess();
        },
        (error): void => {
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
        (): void => {
          localStorage.setItem('oathUser', 'true');
          navigate("/profile");
          window.location.reload();
        },
        (): void => {
          const resMessage = "Unable to sign in via Google";
          setLoading(false);
          setMessage(resMessage);
        }
    );
  };

  return (
    <div className="email-input-modal-content" ref={ref} style={{ height: containerHeight }}>
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
            <form style={{color: 'var(--white)', fontSize: '14px'}}>
              <fieldset style={{border: 'none'}}>
                <input
                    type="checkbox"
                    id="mc"
                    name="Agree"
                    value="Agree"
                    className="consent-radio-button"
                    onChange={handleConsentChange}
                />
                <label htmlFor="mc">
                  I accept{" "}
                  <a className="consent-link" href='/terms-of-service' target="_blank" rel="noopener noreferrer">Terms of Service</a>{" "}
                  and{" "}
                  <a className="consent-link" href='/privacy-policy' target="_blank" rel="noopener noreferrer">Privacy Policy</a>
                </label>
              </fieldset>
            </form>
            {consentError && <div className="alert-danger">{consentError}</div>}
            <ErrorMessage name="email" component="div" className="alert-danger"/>
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
          onError={(): void => {
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
});

export default EmailInput;
