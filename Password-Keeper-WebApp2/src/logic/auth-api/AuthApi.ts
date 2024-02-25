import {BACKEND_ADDRESS} from "../BackendAddress";
import {ResponseStatus} from "../ResponseStatus";
import {ErrorCode} from "../ErrorCode";
import {ErrorBody} from "../ErrorBody";


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

const quit = () => {
};

const rejectUndefined = () => Promise.reject(new ErrorBody(ErrorCode.UNDEFINED))

const checkStatus = async (response: Response): Promise<Response> => {
    if (!response.ok) {
        let body = await ErrorBody.from(response);
        return Promise.reject(body);
    }

    return response;
}

export class AuthApi {

    public static async login(username: string, password: string, dontLogout?: boolean): Promise<Response> {
        console.log('logging')
        return fetch(`${BACKEND_ADDRESS}/login${dontLogout ? "?dontLogout=true" : ""}`, {
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {"Content-Type": "application/json"},
            credentials: 'include'

        }).then(checkStatus);
        // .then(response => {
        //     let authToken = response.headers.get("Authorization");
        //     if(authToken != null) {
        //         return authToken;
        //     }
        //     console.log("wtf?")
        //     return Promise.reject(ErrorCode.UNDEFINED);
        // });
    }

    public static async logout(): Promise<void> {
        return fetch(`${BACKEND_ADDRESS}/logout`, {
            method: 'post',
            credentials: 'include'

        }).then(checkStatus).then(close);
    }

    public static async refreshAuthToken(): Promise<string> {
        return fetch(`${BACKEND_ADDRESS}/refresh/auth-token`, {
            method: 'post',
            credentials: 'include'
        })
            .then(checkStatus)
            .then(response => response.headers.get("Authorization") as string);
    }

    public static async refreshRefreshToken(): Promise<void> {
        return fetch(`${BACKEND_ADDRESS}/refresh/refresh-token`, {
            method: 'post',
            credentials: 'include'
        }).then(checkStatus).then(quit);
    }

}

