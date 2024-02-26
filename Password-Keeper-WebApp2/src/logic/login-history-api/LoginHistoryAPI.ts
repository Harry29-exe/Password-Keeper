import type {LoginHistoryDTO} from "./LoginHistoryDTO";
import {BACKEND_ADDRESS} from "../BackendAddress";

export class LoginHistoryAPI {

    public static async getAllAuthorizedDevices(page: number, itemsPerPage: number, authToken: string): Promise<LoginHistoryDTO> {
        return fetch(`${BACKEND_ADDRESS}/login-history?page=${page}&itemsPerPage=${itemsPerPage}`, {
            method: 'get',
            headers: {'Authorization': authToken}
        }).then(response => {
            if (!response.ok) return Promise.reject(response.status);

            return response.json();
        })
    }

}