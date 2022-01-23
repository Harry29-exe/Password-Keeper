import type {Readable, Subscriber, Unsubscriber, Updater} from "svelte/store";
import {writable} from "svelte/store";
import {ResponseStatus, ResponseStatusU} from "../logic/ResponseStatus";
import {AuthApi, AuthApiCode} from "../logic/auth-api/AuthApi";
import {ErrorBody} from "../logic/ErrorBody";

export class AuthHolder {
    public readonly isAuthenticated?: boolean;
    public readonly authToken?: string;
    public readonly username?: string;


    constructor(isAuthenticated?: boolean, authToken?: string , username?: string ) {
        this.isAuthenticated = isAuthenticated;
        this.authToken = authToken;
        this.username = username;
    }

    public static from(authToken: string, username: string): AuthHolder {
        return new AuthHolder(true, authToken, username);
    }

    public static empty(): AuthHolder {
        return new AuthHolder(false);
    }

}

interface AuthStore extends Readable<AuthHolder> {
    login: (username: string, password: string, dontLogout?: boolean) => Promise<AuthApiCode>,
    logout: () => Promise<ResponseStatus>,
    refreshAuth: () => Promise<ResponseStatus>,
    refreshRefresh: () => Promise<ResponseStatus>
}

class AuthStoreImpl implements AuthStore {
    protected sub: (run: Subscriber<AuthHolder>, invalidate?: any) => Unsubscriber;
    protected set: (value: AuthHolder) => void;
    protected update: (update: Updater<AuthHolder>) => void;

    constructor() {
        const {subscribe, set, update} = writable<AuthHolder>();
        this.sub = subscribe;
        this.set = set;
        this.update = update;

        set(AuthHolder.empty());
    }

    get subscribe() {
        return this.sub;
    }

    public login(username: string, password: string, dontLogout: boolean): Promise<AuthApiCode> {
    return AuthApi.login(username, password, dontLogout)
        .then(response => {
            if (response.ok) {
                this.set(new AuthHolder(true, response.headers.get("Authorization") as string, username));
                return AuthApiCode.OK;
            }

            return response.json().then(errorBody => {
                return (errorBody as ErrorBody).errorCode as AuthApiCode;
            });
        })
    }

    public logout(): Promise<ResponseStatus> {
        return AuthApi.logout().then(status => {
            if (ResponseStatusU.isOk(status)) {
                this.set(AuthHolder.empty());
            }

            return status;
        })
    }

    public refreshAuth(): Promise<ResponseStatus> {
        return AuthApi.refreshAuthToken()
            .then(response => {
                if (ResponseStatusU.isOk(response.status)) {
                    const token = parseJwt(response.authToken);
                    this.set(AuthHolder.from(response.authToken as string, token.sub));
                }

                return response.status;
            })
    }

    public refreshRefresh(): Promise<ResponseStatus> {
        return AuthApi.refreshRefreshToken();
    }

}

function parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}


export const authStore: AuthStore = new AuthStoreImpl();