import React, { useEffect, useState } from 'react';
import { decodeJWT } from '../../../../services/DecodeJWTService';

const ProfilePage = () => {
    const [username, setUsername] = useState('');

    useEffect((): void => {
        const storedUser: string | null = localStorage.getItem('user');
        if (storedUser) {
            const user = JSON.parse(storedUser);
            if (user && user.accessToken) {
                const decodedJWT = decodeJWT(user.accessToken);
                if (decodedJWT && typeof decodedJWT === 'object' && 'sub' in decodedJWT) {
                    const { sub: username } = decodedJWT;
                    setUsername(username);
                }
            }
        }
    }, []);

    return (
        <div>
            <h1>Hallo: {username}</h1>
        </div>
    );
};

export default ProfilePage;