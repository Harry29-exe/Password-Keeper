export class PasswordInfoDTO {
    public readonly passwordName: string;
    public readonly passwordUrl: string;


    constructor(passwordName: string, passwordUrl: string) {
        this.passwordName = passwordName;
        this.passwordUrl = passwordUrl;
    }
}