import {BACKEND_ADDRESS} from "../BackendAddress";

export class ClientDevicesAPI {

    public static async getAllAuthorizedDevices(authToken: string) {
        return fetch(`${BACKEND_ADDRESS}/device-authorization`, {
            method: 'get',
            headers: {"Authorization": authToken},
        }).then(response => {
            if (!response.ok) {
                return Promise.reject(response.status)
            }

            return response.json();
        })
    }

}
