<script lang="ts">
    import Modal from "../utils/atomic/Modal.svelte";
    import TextInput from "../utils/atomic/TextInput.svelte";
    import {PasswordAPI} from "../../logic/password-storage-api/PasswordAPI";
    import {SavePasswordRequestDTO} from "../../logic/password-storage-api/SavePasswordRequestDTO";
    import {authStore} from "../../stores/AuthStore";
    import {CreateNewPasswordRequestDTO} from "../../logic/password-storage-api/CreateNewPasswordRequestDTO";
    import {popupStore} from "../../stores/PopupStore";
    import {ErrorCode} from "../../logic/ErrorCode";


    const generalError = "We could not save your password. Try later or refresh a page.";
    const savePasswordDTO = (): SavePasswordRequestDTO =>
        new SavePasswordRequestDTO(
            storagePassword, newPassword,
            passwordName, passwordUrl
        );

    const createPasswordDTO = (): CreateNewPasswordRequestDTO =>
        new CreateNewPasswordRequestDTO(storagePassword, passwordName, passwordUrl);

    const handleReject = (reason: any) => {
        if (!(typeof reason === "number")) {
            popupStore.danger(generalError);
        }

        switch (reason) {
            case ErrorCode.ACCESS_DENIED:
                popupStore.danger("Please check if storage password is correct.");
                break;
            case ErrorCode.BAD_REQUEST:
                popupStore.danger("Some of your input is incorrect.");
                break;
            default:
                popupStore.danger(generalError);
        }
    }

    const onAddPassword = () => {
        if (newPassword !== newPasswordRepeat) {
            popupStore.danger("New password and new password repeat fields does not match ");
        } else if (addPasswordMode) {
            PasswordAPI.saveNewPassword(
                savePasswordDTO(),
                $authStore.authToken as string
            ).then(() => popupStore.success("Password has been saved")
            ).catch(reason => handleReject(reason));
        } else {
            PasswordAPI.createNewPassword(
                createPasswordDTO(),
                $authStore.authToken as string
            ).then(() => popupStore.success("Password has been generated and saved. Click refresh to view your new password")
            ).catch(reason => handleReject(reason));
        }
    }

    let isOpen = false;

    let addPasswordMode: boolean = true;
    const switchActionType = () => addPasswordMode = !addPasswordMode;


    let passwordName = "";
    let passwordUrl = "";
    let newPassword = "";
    let newPasswordRepeat = "";
    let storagePassword = "";

</script>


<button class="btn-primary-lg mt-10" on:click={() => isOpen = true}>
    Add new Password
</button>


<Modal bind:isOpen style="min-height: 70vh; min-width: 50vw">

    <div class="text-3xl font-bold" slot="header">
        Add new password
    </div>

    <div class="v-stack m-10 mt-2.5 min-w-[300px] w-4/5 text-2xl">

        <TextInput bind:value={passwordName} placeholder="Password name"/>
        <TextInput bind:value={passwordUrl} placeholder="Optional password url"/>
        <div class="spacer-h"></div>


        <div class="hover-pointer h-stack" on:click={switchActionType}>
            <input class="w-5 h-5" type="checkbox"
                   checked={!addPasswordMode} on:click={e => {
                       e.stopPropagation();
                       switchActionType();
                   }}/>
            <span class="text-center flex-grow">
                Generate password for me
            </span>
        </div>

        <TextInput autocomplete="new-password" bind:value={newPassword} placeholder="New Password"
                   disabled={!addPasswordMode} name="storage password" type="password"/>
        <TextInput bind:value={newPasswordRepeat} placeholder="Repeat new Password"
                   disabled={!addPasswordMode} name="storage password" type="password"/>


        <div class="spacer-h"></div>
        <TextInput bind:value={storagePassword} placeholder="Your storage password"
                   type="password"/>


        <button class="btn-primary-lg mt-8" on:click={onAddPassword}>
            Add password
        </button>

    </div>
</Modal>