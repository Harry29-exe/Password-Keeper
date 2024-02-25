export class CreateUserRequest {
    public username: string;
    public email: string;
    public password: string;
    public storagePassword: string;


    constructor(username: string, email: string, password: string, storagePassword: string) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.storagePassword = storagePassword;
    }
}