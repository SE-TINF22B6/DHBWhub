import config from "../config/config";


export const getUserIdByAccountId = async (accountId:number) => {
    try {
        const response = await fetch(config.apiUrl +`account/get-user-id/${accountId}`)

        if (response.ok) {
            return (response.json());
        } else {
            console.log(new Error("Failed to fetch User Id"));
        }
    } catch (error) {
        console.error("Error when retrieving the user Id,"+ error)

    }
};

