import type {Readable, Subscriber, Unsubscriber, Updater} from "svelte/store";
import {writable} from "svelte/store";
import {AuthApi} from "../logic/auth-api/AuthApi";

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
    login: (username: string, password: string, dontLogout?: boolean) => Promise<void>,
    logout: () => Promise<void>,
    refreshAuth: () => Promise<void>,
    refreshRefresh: () => Promise<void>
}


class AuthStoreImpl implements Readable<AuthHolder> {
    protected sub: (run: Subscriber<AuthHolder>, invalidate?: any) => Unsubscriber;
    protected set: (value: AuthHolder) => void;
    protected update: (update: Updater<AuthHolder>) => void;

    protected state: number;

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

    public login(username: string, password: string, dontLogout: boolean): Promise<void> {
        return AuthApi.login(username, password, dontLogout)
            .then(response => {
                this.set(new AuthHolder(true, response.headers.get("Authorization") as string, username));
                this.state += 1;
                this.setAutoRefresh();
            })
    }

    public logout(): Promise<void> {
        return AuthApi.logout()
            .then(() => {
                this.set(AuthHolder.empty());
                this.state++;
            })
    }

    public refreshAuth(): Promise<void> {
        return AuthApi.refreshAuthToken()
            .then(token => {
                const decodeToken = parseJwt(token)
                this.set(AuthHolder.from(token as string, decodeToken.sub));
            })
    }

    public refreshRefresh(): Promise<void> {
        return AuthApi.refreshRefreshToken();
    }

    private setAutoRefresh() {
        let currentState = this.state;
        setTimeout(() => {
            if (this.state === currentState) {
                this.refreshAuth().then(this.setAutoRefresh)
            }
        }, 600_000);
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