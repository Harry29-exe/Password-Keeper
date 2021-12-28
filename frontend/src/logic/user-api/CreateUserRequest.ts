export class CreateUserRequest {
    public username: string;
    public password: string;
    public storagePassword: string;


    constructor(username: string, password: string, storagePassword: string) {
        this.username = username;
        this.password = password;
        this.storagePassword = storagePassword;
    }
}