import React, { useEffect, useState } from 'react';
import { decodeJWT } from '../../../../services/utils'; // Annahme: Du hast eine Funktion zum Decodieren des JWT

const ProfilePage = () => {
    const [username, setUsername] = useState('');

    useEffect(() => {
        // JWT aus dem Local Storage abrufen
        const storedUser = localStorage.getItem('user');

        if (storedUser) {
            const user = JSON.parse(storedUser);

            if (user && user.accessToken) {
                // JWT decodieren
                const decodedJWT = decodeJWT(user.accessToken);

                if (decodedJWT && typeof decodedJWT === 'object' && 'sub' in decodedJWT) {
                    // Benutzernamen extrahieren
                    const { sub: username } = decodedJWT;

                    // Benutzernamen setzen
                    setUsername(username);
                }
            }
        }
    }, []);

    return (
        <div>
            <h1 >Hallo: {username}</h1>
            {/* Weitere Profilinformationen hier */}
        </div>
    );
};

export default ProfilePage;