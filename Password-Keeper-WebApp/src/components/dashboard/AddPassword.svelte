<script lang="ts">
    import Button from "../utils/atomic/Button.svelte";
    import Modal from "../utils/atomic/Modal.svelte";
    import TextInput from "../utils/atomic/TextInput.svelte";
    import {PasswordAPI} from "../../logic/password-storage-api/PasswordAPI";
    import {SavePasswordRequestDTO} from "../../logic/password-storage-api/SavePasswordRequestDTO";
    import {authStore} from "../../stores/AuthStore";
    import {ResponseStatusU} from "../../logic/ResponseStatus";
    import {CreateNewPasswordRequestDTO} from "../../logic/password-storage-api/CreateNewPasswordRequestDTO";
    import {popupStore} from "../../stores/PopupStore";

    let addPasswordMode = true;
    const switchActionType = () => {
        addPasswordMode = !addPasswordMode;
    }

    const savePasswordDTO = (): SavePasswordRequestDTO =>
        new SavePasswordRequestDTO(
            storagePassword, newPassword,
            passwordName, passwordUrl
        );

    const createPasswordDTO = (): CreateNewPasswordRequestDTO =>
        new CreateNewPasswordRequestDTO(storagePassword, passwordName, passwordUrl);

    const onAddPassword = () => {
        if (newPassword !== newPasswordRepeat) {
            popupStore.danger("New password and new password repeat fields does not match ");
        } else if (addPasswordMode) {
            PasswordAPI.saveNewPassword(
                savePasswordDTO(),
                $authStore.authToken as string
            ).then(status => {
                if (ResponseStatusU.isOk(status)) {
                    popupStore.success("Password has been saved");
                } else {
                    popupStore.danger("We could not save your password. Try later or refresh a page.");
                }
            })
        } else {
            PasswordAPI.createNewPassword(
                createPasswordDTO(),
                $authStore.authToken as string
            ).then(status => {
                if (ResponseStatusU.isOk(status)) {
                    console.log('ok')
                } else {
                    console.log('not ok')
                }
            })
        }
    }

    let isOpen = false;

    let passwordName = "";
    let passwordUrl = "";
    let newPassword = "";
    let newPasswordRepeat = "";
    let storagePassword = "";

</script>


<Button on:click={() => isOpen = true} size="lg"
        style="width: auto; padding: 10px; margin-top: 30px">
    Add new Password
</Button>


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