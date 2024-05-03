import React, { useState } from 'react';
import Modal from '@mui/material/Modal';

const EmailInputModal = ({ open, onClose, onNext }:any) => {
    const [email, setEmail] = useState('');

    const handleNext = () => {
        // Hier könntest du Validierungslogik für die E-Mail-Adresse einfügen
        // Zum Beispiel: Überprüfung, ob die E-Mail-Adresse gültig ist

        // Wenn die Validierung erfolgreich ist, rufe die onNext-Funktion auf
        onNext(email);
    };

    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="email-input-modal-title"
            aria-describedby="email-input-modal-description"
        >
            <div>
                <h2 id="email-input-modal-title">E-Mail-Adresse eingeben</h2>
                <input
                    type="email"
                    placeholder="E-Mail-Adresse"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <button onClick={handleNext}>Weiter</button>
            </div>
        </Modal>
    );
};

export default EmailInputModal;