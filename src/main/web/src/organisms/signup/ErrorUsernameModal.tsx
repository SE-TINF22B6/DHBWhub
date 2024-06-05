import React from "react";
import './ErrorModal.css';

const ErrorUsernameModal = ({onClose}: any) => {
    return (
        <div className="error-modal">
            <div className="error-description">
                <h4>
                    The username you entered is already in use!
                    Please try again with another username.
                </h4>
            </div>
        </div>
    );
};

export default ErrorUsernameModal;
