import {AbstractStore} from "./AbstractStore";

export enum MsgType {
    INFO = "info",
    SUCCESS = "success",
    DANGER = "danger",
    WARNING = "warning"
}

export type Msg = {

    msgTitle: string,
    msg: string,
    msgType: MsgType

}

type MsgHolder = {

    open: boolean;

} & Msg

export class MsgPopupStore extends AbstractStore<MsgHolder> {

    constructor() {
        super({
            open: false,
            msg: "",
            msgTitle: "",
            msgType: MsgType.INFO
        });

    }

    public setMsg(msgTitle: string, msg: string, msgType?: MsgType) {
        const v = this.value;
        v.msg = msg;
        v.msgType = msgType ? msgType : MsgType.INFO;
        v.msgTitle = msgTitle;
        v.open = true;
        this.update();
    }

    public success(msg: string, msgTitle?: string) {
        this.setMsg(msgTitle ? msgTitle : "Success", msg, MsgType.SUCCESS);
    }

    public warning(msg: string, msgTitle?: string) {
        this.setMsg(msgTitle ? msgTitle : "Warning", msg, MsgType.WARNING);
    }

    public danger(msg: string, msgTitle?: string) {
        this.setMsg(msgTitle ? msgTitle : "Danger", msg, MsgType.DANGER);
    }

    public info(msg: string, msgTitle?: string) {
        this.setMsg(msgTitle ? msgTitle : "Info", msg, MsgType.INFO);
    }

    public open() {
        this.value.open = true;
        this.update();
        // this.update(value => {
        //     value.open = true;
        //     return value;
        // });
    }

    public close() {
        this.value.open = false;
        this.update();
        // this.update(value => {
        //     value.open = false;
        //     return value;
        // })
    }

}


export const popupStore: MsgPopupStore = new MsgPopupStore();