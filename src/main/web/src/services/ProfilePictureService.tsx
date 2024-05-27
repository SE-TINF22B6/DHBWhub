import axios from "axios";
import config from "../config/config";

export const getProfilePicture = (id: any) => {
    return axios.get(
        config.apiUrl + "/picture",
        {
            params: {
                account_id: id
            }
        });
};