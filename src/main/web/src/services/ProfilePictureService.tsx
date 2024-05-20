import axios from "axios";
import config from "../config/config";

export const getProfilePicture = (username: string) => {
    return axios.get(config.apiUrl + "/picture", { params: { username: username } });
};