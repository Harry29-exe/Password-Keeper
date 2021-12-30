import {PasswordInfoDTO} from "./PasswordInfoDTO";
import {BACKEND_ADDRESS} from "../BackendAddress";
import {SavePasswordRequestDTO} from "./SavePasswordRequestDTO";
import {ResponseStatus, ResponseStatusU} from "../ResponseStatus";

export class PasswordAPI {
    private static readonly API_ADDRESS = BACKEND_ADDRESS + "/password-storage"

    public static async getPasswords(authToken: string): Promise<PasswordInfoDTO[]> {
        return fetch(`${this.API_ADDRESS}`, {
            method: 'get',
            headers: {"Authorization": authToken}
        }).then(response => {
            if(!response.ok) {
                return Promise.reject("Server returned: " + response.status);
            }

            return response.json();
        })
    }

    public static async decodeAndGetPassword(
        storagePassword: string,
        passwordName: string,
        authToken: string
    ): Promise<string> {

        return fetch(`${this.API_ADDRESS}/get`, {
            method: 'post',
            headers: {
                'Authorization': authToken,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                storagePassword: storagePassword,
                passwordName: passwordName
            })
        }).then(response => {
            if(!response.ok) {
                return Promise.reject("Server returned: " + response.status);
            }

            return response.text();
        })
    }

    public static async saveNewPassword(
        requestBody: SavePasswordRequestDTO,
        authToken: string
    ): Promise<ResponseStatus> {

        return fetch(`${this.API_ADDRESS}/save-password`, {
            method: 'put',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': authToken
            },
            body: JSON.stringify(requestBody)
        }).then(response => ResponseStatusU.getResponseStatus(response.status))

    }

}