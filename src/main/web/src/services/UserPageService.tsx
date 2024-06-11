import config from "../config/config";


export const getUserIdByAccountId = async (accountId:number) => {

    const response = await fetch(config.apiUrl +`account/get-user-id/${accountId}`);
    return (response.json());
};

