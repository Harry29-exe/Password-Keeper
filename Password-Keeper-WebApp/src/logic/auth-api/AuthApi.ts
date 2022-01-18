import {BACKEND_ADDRESS} from "../BackendAddress";
import {ResponseStatus, ResponseStatusU} from "../ResponseStatus";


export enum AuthApiCode {
    UNKNOWN_DEVICE = "UNKNOWN_DEVICE",
    INVALID_CREDENTIALS = "INVALID_CREDENTIALS",
    OK = "OK"
}

export class LoginResponse {
    public status: ResponseStatus;
    public authToken: string | null;


    constructor(status: ResponseStatus, authToken: string | null) {
        this.status = status;
        this.authToken = authToken;
    }
}

export class AuthApi {

    public static async login(username: string, password: string, dontLogout?: boolean): Promise<Response> {
        console.log('logging')
        return fetch(`${BACKEND_ADDRESS}/login${dontLogout ? "?dontLogout=true" : ""}`, {
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {"Content-Type": "application/json"},
            credentials: 'include'
        });
    }

    public static async logout(): Promise<ResponseStatus> {
        return fetch(`${BACKEND_ADDRESS}/logout`, {
            method: 'post',
            credentials: 'include'
        }).then(response => response.ok ? ResponseStatus.OK : ResponseStatus.CLIENT_ERROR);
    }

    public static async refreshAuthToken(): Promise<LoginResponse> {
        return fetch(`${BACKEND_ADDRESS}/refresh/auth-token`, {
            method: 'post',
            credentials: 'include'
        }).then(response => {
            if (!response.ok) {
                return new LoginResponse(ResponseStatus.CLIENT_ERROR, null);
            }
            let authHeader = response.headers.get("Authorization");
            return new LoginResponse(ResponseStatus.OK, authHeader);
        }).catch(reason => new LoginResponse(ResponseStatus.CLIENT_ERROR, null));
    }

    public static async refreshRefreshToken(): Promise<ResponseStatus> {
        return fetch(`${BACKEND_ADDRESS}/refresh/refresh-token`, {
            method: 'post',
            credentials: 'include'
        }).then(response => response.ok ? ResponseStatus.OK : ResponseStatus.CLIENT_ERROR);
    }

}