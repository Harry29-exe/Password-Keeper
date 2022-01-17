export enum ResponseStatus {
    OK,
    CLIENT_ERROR,
    SERVER_ERROR
}

export class ResponseStatusU {

    public static getResponseStatus(status: number): ResponseStatus {
        if(status >= 200 && status < 300 ) return ResponseStatus.OK
        if(status >= 400 && status < 500) return ResponseStatus.CLIENT_ERROR
        else return ResponseStatus.SERVER_ERROR
    }

    public static isOk(responseStatus: ResponseStatus): boolean {
        return responseStatus === ResponseStatus.OK;
    }

    public static isError(responseStatus: ResponseStatus): boolean {
        return responseStatus !== ResponseStatus.OK;
    }

}