import config from "../config/config";
import {getJWT, getUserId} from "./AuthService";

interface Picture {
    id: number;
    name: string;
    imageData: string;
}

interface Account {
    id: number;
    username: string;
    email: string;
    password: string;
    picture: Picture;
    active: boolean;
}

interface Course {
    id: number;
    name: string;
    faculty: Faculty;
}

interface Faculty {
    id: number;
    name: string;
}

interface ProfileData {
    age: number;
    description: string;
    course: Course;
    account: Account;
}

const jwt: string | null = getJWT();
const headersWithJwt = {
    ...config.headers,
    'Authorization': jwt ? `Bearer ${jwt}` : ''
};

export async function loadProfileData() {
    const url = config.apiUrl + `/fetch/${getUserId()}`;

    const fetchData = {
        method: "GET",
        headers: headersWithJwt
    };

    try {
        await fetch(url, fetchData)
            .then((response) => response.json())
            .then((data) => console.log(data));

        // const response = await fetch(url, fetchData);
        // const data: ProfileData = await response.json();
        // const age: number = data.age;
        // const description: string = data.description;
        // const course: Course = data.course;
        // const account: Account = data.account;
        // const accountId: number = data.account.id;
        // const username: string = data.account.username;
        // const email: string = data.account.email;
        //
        // console.log( age, description, course, account, accountId, username, email);
    } catch (error) {
        console.error('Fehler beim Abrufen der Daten:', error);
    }
}
