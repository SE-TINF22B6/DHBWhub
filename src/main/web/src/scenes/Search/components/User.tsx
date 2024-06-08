import React from 'react';
import './User.css';
import {Link} from "react-router-dom";
import {UserModel} from "./UserModel";
import {Pictures} from "../../../atoms/Pictures/Pictures";

export const User: React.FC<UserModel> = (props: UserModel) => {
    const {
        accountId,
        username,
        picture,
    } = props;

    const truncatedUsername = username.length > 17 ? username.slice(0, 15) + "..." : username;

    return (
        <div className="user-container">
            <Link to={`/user/?id=${accountId}`} aria-label="To the user" className="user-button">
                {picture && picture.imageData ? (
                    <img className="user-image" alt={username} src={picture.imageData} loading="lazy"/>
                ) : (
                    <Pictures defaultPicture={true} className="custom-image"/>
                )}
                <div className="user-name">{truncatedUsername}</div>
            </Link>
        </div>
    );
}