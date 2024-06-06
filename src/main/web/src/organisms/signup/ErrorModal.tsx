import React from 'react';
import './ErrorModal.css';

const ErrorModal = ({onClose}: any) => {
    return (
        <div className="error-modal">
            <div className="error-description">
                <h4>
                    The email you entered is already in use!
                    Please try again with another email.
                </h4>
            </div>
        </div>
    );
};

export default ErrorModal;
