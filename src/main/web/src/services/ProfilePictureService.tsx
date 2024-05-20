import axios from "axios";
import config from "../config/config";

export const profilePicture = (username: string) => {
    return axios.get(config.apiUrl + "/picture");
};