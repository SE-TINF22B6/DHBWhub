import React from 'react';
import './UserFriend.css';
import {Link} from "react-router-dom";
import {UserFriendModel} from "./models/UserFriendModel";
import {getDefaultOrRandomPicture} from "../../atoms/Pictures/PicturesComponent";

export const UserFriend: React.FC<UserFriendModel> = (props: UserFriendModel) => {
    const {
        friendlistId,
        userId,
        username,
        image,
    } = props;

    const truncatedUsername = username.length > 17 ? username.slice(0, 15) + "..." : username;

    return (
        <div className="user-container">
            <Link to={`/user/?id=${userId}`} aria-label="To the user" className="user-button">
                {image ? (
                    <img className="user-image" alt={username} src={image} loading="lazy"/>
                ) : (
                    <img className="custom-image" alt={"random"} src={getDefaultOrRandomPicture(false)}/>
                )}
                <div className="user-name">{truncatedUsername}</div>
            </Link>
        </div>
    );
}
