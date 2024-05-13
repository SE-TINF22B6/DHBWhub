import axios from "axios";
import * as AuthService from "./auth.service";

jest.mock("axios");

describe("AuthService Tests", () => {
    test("register function should make a POST request to signup endpoint", async () => {

        axios.post.mockResolvedValue({ data: { message: "User registered successfully" } });

        const response = await AuthService.register("testUser", "password");

        expect(response.data.message).toBe("User registered successfully");
        expect(axios.post).toHaveBeenCalledWith("http://localhost:8080/api/auth/signup", {
            username: "testUser",
            password: "password",
        });
    });

    test("login function should make a POST request to login endpoint", async () => {

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

    test();
});