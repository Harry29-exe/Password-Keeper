export class SavePasswordRequestDTO {
    storagePassword: string;
    passwordToSave: string;
    passwordName: string;
    passwordUrl: string;


    constructor(storagePassword: string, passwordToSave: string, passwordName: string, passwordUrl: string) {
        this.storagePassword = storagePassword;
        this.passwordToSave = passwordToSave;
        this.passwordName = passwordName;
        this.passwordUrl = passwordUrl;
    }
}