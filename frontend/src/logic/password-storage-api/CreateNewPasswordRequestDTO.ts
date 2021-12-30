export class CreateNewPasswordRequestDTO {
    storagePassword: string;
    passwordName: string;
    passwordUrl?: string;


    constructor(storagePassword: string, passwordName: string, passwordUrl: string) {
        this.storagePassword = storagePassword;
        this.passwordName = passwordName;
        this.passwordUrl = passwordUrl;
    }
}