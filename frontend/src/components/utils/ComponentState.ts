export enum State {
    IN_PROGRESS,
    NOT_INITIALIZED,
    ERROR
}

export class ComponentState {
    public state: State;
    public errorMsg?: string;

    constructor(state: State, errorMsg?: string) {
        this.state = state;
        this.errorMsg = errorMsg;
    }

    public isNotInit() {
        return this.state === State.NOT_INITIALIZED;
    }

    public isInProgress() {
        return this.state === State.IN_PROGRESS;
    }

    public isError() {
        return this.state === State.ERROR;
    }

}