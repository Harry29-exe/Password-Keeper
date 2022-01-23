import {PasswordInfoDTO} from "./PasswordInfoDTO";
import {BACKEND_ADDRESS} from "../BackendAddress";
import {SavePasswordRequestDTO} from "./SavePasswordRequestDTO";
import {CreateNewPasswordRequestDTO} from "./CreateNewPasswordRequestDTO";
import {ErrorCode} from "../ErrorCode";

export class PasswordAPI {
    private static readonly API_ADDRESS = BACKEND_ADDRESS + "/password-storage"

    public static async getPasswords(authToken: string): Promise<PasswordInfoDTO[]> {
        return fetch(`${this.API_ADDRESS}`, {
            method: 'get',
            headers: {"Authorization": authToken}
        }).then(response => {
            if(!response.ok) {
                return Promise.reject(PasswordAPI.mapRejectStatus(response.status));
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
                return Promise.reject(PasswordAPI.mapRejectStatus(response.status));
            }

            return response.text();
        })
    }

    public static async saveNewPassword(
        requestBody: SavePasswordRequestDTO,
        authToken: string
    ) {

        return fetch(`${this.API_ADDRESS}/save-password`, {
            method: 'put',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': authToken
            },
            body: JSON.stringify(requestBody)
        }).then(response => {
            if (response.ok) {
                return;
            }
            return Promise.reject(PasswordAPI.mapRejectStatus(response.status));
        });

    }

    public static async createNewPassword(
        requestBody: CreateNewPasswordRequestDTO,
        authToken: string
    ) {

        return fetch(`${this.API_ADDRESS}/create-password`, {
            method: 'put',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': authToken
            },
            body: JSON.stringify(requestBody)
        }).then(response => {
            if (response.ok) {
                return;
            }
            return Promise.reject(PasswordAPI.mapRejectStatus(response.status));
        })

    }


    private static mapRejectStatus(status: number): ErrorCode {
        switch (status) {
            case 403:
                return ErrorCode.ACCESS_DENIED;
            case 400:
                return ErrorCode.BAD_REQUEST;
            default:
                return ErrorCode.UNDEFINED;
        }
    }

}