import React, { useState } from 'react';
import Modal from '@mui/material/Modal';

const UsernamePasswordInputModal = ({ open, onClose, onSubmit }:any) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const handleSubmit = () => {
        // Hier kannst du Validierungslogik für den Benutzernamen und das Passwort einfügen
        // Zum Beispiel: Überprüfung, ob das Passwort und die Bestätigung übereinstimmen

        // Wenn die Validierung erfolgreich ist, rufe die onSubmit-Funktion auf
        onSubmit({ username, password });
    };

    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="username-password-input-modal-title"
            aria-describedby="username-password-input-modal-description"
        >
            <div>
                <h2 id="username-password-input-modal-title">Benutzername und Passwort eingeben</h2>
                <input
                    type="text"
                    placeholder="Benutzername"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Passwort"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Passwort bestätigen"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                />
                <button onClick={handleSubmit}>Registrieren</button>
            </div>
        </Modal>
    );
};

export default UsernamePasswordInputModal;