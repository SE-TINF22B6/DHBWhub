import axios from "axios";

const API_URL = "https://localhost:8080/api/auth/";

class AuthService {
    login(username: any, password: any) {
        return axios
            .post(API_URL + "login", {
                username,
                password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(username: any, email: any, password: any) {
        return axios.post(API_URL + "signup", {
           username,
           email,
           password
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem("user") || "{}");
    }

}

export default new AuthService();