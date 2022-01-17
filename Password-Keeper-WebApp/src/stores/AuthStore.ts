import type {Readable} from "svelte/store";
import {writable} from "svelte/store";
import {ResponseStatus, ResponseStatusU} from "../logic/ResponseStatus";
import {AuthApi} from "../logic/auth-api/AuthApi";

export class AuthHolder {
    protected _isAuthenticated? = false;
    protected _authToken?: string = undefined;
    protected _username?: string = undefined;


    constructor(isAuthenticated?: boolean, authToken?: string , username?: string ) {
        this._isAuthenticated = isAuthenticated;
        this._authToken = authToken;
        this._username = username;
    }

    get isAuthenticated(): boolean | undefined {
        return this._isAuthenticated;
    }

    get authToken(): string | undefined {
        return this._authToken;
    }

    get username(): string | undefined {
        return this._username;
    }
}

interface AuthStore extends Readable<AuthHolder> {
    login: (username: string, password: string, dontLogout?: boolean) => Promise<ResponseStatus>,
    logout: () => Promise<ResponseStatus>,
    refreshAuth: () => Promise<ResponseStatus>,
    refreshRefresh: () => Promise<ResponseStatus>
}

function parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}

function createAuthStore(): AuthStore {
    const {subscribe, set, update} = writable<AuthHolder>();
    set(new AuthHolder(false));

    return {
        subscribe: subscribe,
        login: (username: string, password: string, dontLogout: boolean): Promise<ResponseStatus> => {
            return AuthApi.login(username, password, dontLogout)
                .then(response => {
                    console.log('login response')
                    if (ResponseStatusU.isOk(response.status)) {
                        console.log('auth = true')
                        set(new AuthHolder(true, response.authToken as string, username))
                    }
                    return response.status
                })
        },

        logout: (): Promise<ResponseStatus> => {
            return AuthApi.logout().then(status => {
                if (ResponseStatusU.isOk(status)) {
                    set(new AuthHolder(false, undefined, undefined));
                }

                return status;
            })
        },

        refreshAuth: (): Promise<ResponseStatus> => {
            return AuthApi.refreshAuthToken()
                .then(response => {
                    if (ResponseStatusU.isOk(response.status)) {
                        const token = parseJwt(response.authToken);
                        set(new AuthHolder(true, response.authToken as string, token.sub));
                    }

                    return response.status;
                })
        },

        refreshRefresh: (): Promise<ResponseStatus> => {
            return AuthApi.refreshRefreshToken();
        }

    };
}

export const authStore = createAuthStore();