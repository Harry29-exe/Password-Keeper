import {readable, writable} from "svelte/store";
import {ResponseStatus, ResponseStatusU} from "../logic/ResponseStatus";
import {AuthApi} from "../logic/auth-api/AuthApi";

export class AuthHolder {
    protected _isAuthenticated: boolean = false;
    protected _authToken?: string = undefined;
    protected _username?: string = undefined;


    constructor(isAuthenticated: boolean, authToken?: string , username?: string ) {
        this._isAuthenticated = isAuthenticated;
        this._authToken = authToken;
        this._username = username;
    }

    get isAuthenticated(): boolean {
        return this._isAuthenticated;
    }

    get authToken(): string | undefined {
        return this._authToken;
    }

    get username(): string | undefined {
        return this._username;
    }
}

function createAuthStore() {
    const {subscribe, set, update} = writable<AuthHolder>();
    set(new AuthHolder(false));

    return {
        subscribe,
        login: (username: string, password: string): Promise<ResponseStatus> => {
            return AuthApi.login(username, password)
                .then(response => {
                    if (ResponseStatusU.isOk(response.status)) {
                        set(new AuthHolder(true, response.authToken, username))
                    }
                    return response.status
                })
        },

    };
}

export const AuthStore = createAuthStore();