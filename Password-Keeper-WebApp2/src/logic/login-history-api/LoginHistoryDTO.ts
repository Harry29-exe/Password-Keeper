import type {LoginEventDTO} from "./LoginEventDTO";

export interface LoginHistoryDTO {
    page: number;
    itemsPerPage: number;
    availablePages: number;
    loginEvents: LoginEventDTO[];

}