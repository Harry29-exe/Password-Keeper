import {ErrorCode} from "./ErrorCode";

export class ErrorBody {
    public errorCode: ErrorCode


    constructor(errorCode: ErrorCode) {
        this.errorCode = errorCode;
    }

    public static async from(request: Response): Promise<ErrorBody> {
        let code = await request.json();
        return new ErrorBody(code.errorCode);
    }


}