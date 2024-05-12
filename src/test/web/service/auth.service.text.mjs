import axios from "axios";
import * as AuthService from "./auth.service"; // Importieren Sie den zu testenden Service

// Mocken Sie axios, um Netzwerkanfragen zu simulieren
jest.mock("axios");

describe("AuthService Tests", () => {
    test("register function should make a POST request to signup endpoint", async () => {
        // Mocken Sie die Antwort von axios.post für die Registrierung
        axios.post.mockResolvedValue({ data: { message: "User registered successfully" } });

        const response = await AuthService.register("testUser", "password");

        expect(response.data.message).toBe("User registered successfully");
        expect(axios.post).toHaveBeenCalledWith("http://localhost:8080/api/auth/signup", {
            username: "testUser",
            password: "password",
        });
    });

    test("login function should make a POST request to login endpoint", async () => {
        // Mocken Sie die Antwort von axios.post für das Login
        axios.post.mockResolvedValue({
            data: { accessToken: "testAccessToken", username: "testUser" },
        });

        const response = await AuthService.login("testUser", "password", true);

        expect(response.accessToken).toBe("testAccessToken");
        expect(localStorage.getItem("user")).not.toBeNull();
        expect(localStorage.getItem("user")).toEqual(
            JSON.stringify({ accessToken: "testAccessToken", username: "testUser" })
        );
    });

    // Weitere Tests für andere Funktionen folgen dem gleichen Muster
});