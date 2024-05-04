import React, { useState } from 'react';
import Modal from '@mui/material/Modal';

const VerificationCodeInputModal = ({ open, onClose, onVerify }:any) => {
    const [verificationCode, setVerificationCode] = useState('');

    const handleVerify = () => {
        // Hier könntest du Validierungslogik für den Verifizierungscode einfügen
        // Zum Beispiel: Überprüfung, ob der Verifizierungscode korrekt ist

        // Wenn die Validierung erfolgreich ist, rufe die onVerify-Funktion auf
        onVerify(verificationCode);
    };

    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="verification-code-input-modal-title"
            aria-describedby="verification-code-input-modal-description"
        >
            <div>
                <h2 id="verification-code-input-modal-title">Verifizierungscode eingeben</h2>
                <input
                    type="text"
                    placeholder="Verifizierungscode"
                    value={verificationCode}
                    onChange={(e) => setVerificationCode(e.target.value)}
                />
                <button onClick={handleVerify}>Verifizieren</button>
            </div>
        </Modal>
    );
};

export default VerificationCodeInputModal;