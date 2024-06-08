export interface UserModel {
    username: string;
    accountId: string;
    picture?:  {
        id: number;
        name: string;
        imageData: string;
    }
}