export default function authHeader(): { Authorization: string } {
    const userStr: string | null = localStorage.getItem("user");
    let user: { accessToken: string } | null = null;
    if (userStr) user = JSON.parse(userStr);

    if (user && user.accessToken) {
        return { Authorization: 'Bearer ' + user.accessToken };
    } else {
        return { Authorization: '' };
    }
}