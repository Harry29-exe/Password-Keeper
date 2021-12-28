import type {CreateUserRequest} from "./CreateUserRequest";
import {BACKEND_ADDRESS} from "../BackendAddress";
import {ResponseStatus, ResponseStatusU} from "../ResponseStatus";

export class UserAPI {

    public static async register(requestBody: CreateUserRequest): Promise<ResponseStatus> {
        return fetch(`${BACKEND_ADDRESS}/register`, {
            method: 'put',
            body: JSON.stringify(requestBody)
        }).then(response => ResponseStatusU.getResponseStatus(response.status))
    }


}

