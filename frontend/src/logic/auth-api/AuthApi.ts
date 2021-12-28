import {BACKEND_ADDRESS} from "../BackendAddress";
import type {ResponseStatus} from "../ResponseStatus";
import {ResponseStatusU} from "../ResponseStatus";

export class LoginResponse {
    public status: ResponseStatus;
    public authToken: string | undefined;


    constructor(status: ResponseStatus, authToken: string | undefined) {
        this.status = status;
        this.authToken = authToken;
    }
}

export class AuthApi {

    public static async login(username: string, password: string): Promise<LoginResponse> {
        console.log('logging')
        return fetch(`${BACKEND_ADDRESS}/login`, {
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {"Content-Type": "application/json"}
        }).then(response => {
            return new LoginResponse(
                ResponseStatusU.getResponseStatus(response.status),
                response.headers.get("Authorization") as string | undefined)
        });
    }

    public static async refreshAuthToken() {

    }

}