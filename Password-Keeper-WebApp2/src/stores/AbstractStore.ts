import { type Readable, type Subscriber, type Unsubscriber, writable } from 'svelte/store';

export abstract class AbstractStore<T> implements Readable<T> {
    protected sub: (run: Subscriber<T>, invalidate?: any) => Unsubscriber;
    // protected update: (update: Updater<T>) => void;
    protected update: () => void;
    protected set: (value: T) => void;
    private readonly _value: T;

    protected constructor(value: T) {
        this._value = value;
        const {subscribe, set, update} = writable<T>(value);
        this.sub = subscribe;
        this.set = set;
        this.update = () => {
            update(value => value);
        };
    }

    get subscribe() {
        return this.sub;
    }

    protected get value() {
        return this._value;
    }

}