export interface UserModel {
    username: string;
    userId: string;
    picture?: {
        id: number;
        name: string;
        imageData: string;
    };
}
