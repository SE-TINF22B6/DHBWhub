import React, {useState} from 'react';
import {tokenValidation} from "../../services/AuthService";
import AuthCode from "react-auth-code-input";
import './SignUp.css';
import './VerificationCodeInput.css';

const VerificationCodeInput = ({onSuccess}: any) => {
    const [loading, setLoading] = useState<boolean>(false);
    const [result, setResult] = useState('');

    const handleOnChange = (res: string): void => {
        setResult(res);
    };

    const handleOpenLogin = (): void => {

    };

    const handleValidation = (): void => {
        const token: string = result;
        setLoading(true);
        tokenValidation(token)
        .then(
            (): void => {
                onSuccess();
            },
            (error): void => {
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
                setLoading(false);
            }
        );
    };

    const handleKeyDown = (event: React.KeyboardEvent): void => {
        if (event.key === 'Enter') {
            event.preventDefault();
            handleValidation();
        }
    };

    return (
        <div className="verification-modal-content">
            <div className="modalHeader">
                <h3 className="modalHeadline verificationModalHeadline">VERIFY YOUR EMAIL</h3>
            </div>
            <div>
                <div className="form-group" onKeyDown={handleKeyDown}>
                    <label htmlFor="token" className="heading">Please enter your verification code</label>
                    <AuthCode
                        onChange={handleOnChange}
                        length={6}
                        allowedCharacters="numeric"
                        inputClassName="auth-input"
                    />
                </div>
                <div className="form-group">
                    <button type="button" className="loading-btn" onClick={handleValidation}>
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
    );
};

export default VerificationCodeInput;