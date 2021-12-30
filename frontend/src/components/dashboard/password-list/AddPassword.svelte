<script lang="ts">
    import VStack from "../../utils/atomic/VStack.svelte";
    import Button from "../../utils/atomic/Button.svelte";
    import Modal from "../../utils/atomic/Modal.svelte";
    import TextInput from "../../utils/atomic/TextInput.svelte";
    import Spacer from "../../utils/atomic/Spacer.svelte";
    import HStack from "../../utils/atomic/HStack.svelte";
    import {PasswordAPI} from "../../../logic/password-storage-api/PasswordAPI";
    import {SavePasswordRequestDTO} from "../../../logic/password-storage-api/SavePasswordRequestDTO";
    import {authStore} from "../../../stores/AuthStore";
    import {ResponseStatusU} from "../../../logic/ResponseStatus";
    import {CreateNewPasswordRequestDTO} from "../../../logic/password-storage-api/CreateNewPasswordRequestDTO";

    let isOpen = false;

    let addPasswordMode = true;
    const switchActionType = () => {
        addPasswordMode = !addPasswordMode;
    }

    let passwordName = "";
    let passwordUrl = "";
    let newPassword = "";
    let newPasswordRepeat = "";
    let storagePassword = "";

    const onAddPassword = () => {
        if(newPassword !== newPasswordRepeat) {
            //todo
        } else if(addPasswordMode) {
          PasswordAPI.saveNewPassword(
              new SavePasswordRequestDTO(
                  storagePassword,
                  newPassword,
                  passwordName,
                  passwordUrl
              ), $authStore.authToken as string)
              .then(status => {
                  if(ResponseStatusU.isOk(status)) {
                      console.log('ok')
                  } else {
                      console.log('not ok')
                  }
              })
        } else {
            PasswordAPI.createNewPassword(
                new CreateNewPasswordRequestDTO(
                    storagePassword,
                    passwordName,
                    passwordUrl
                ), $authStore.authToken as string
            ).then(status => {
                if(ResponseStatusU.isOk(status)) {
                    console.log('ok')
                } else {
                    console.log('not ok')
                }
            })
        }
    }
</script>


<Button on:click={() => isOpen = true} size="lg"
        style="width: auto; padding: 10px; margin-top: 30px">
    Add new Password
</Button>


<Modal bind:isOpen>
    <VStack style="margin: 60px 50px 50px 50px; min-width: 300px; width: 80%; font-size: 1.5rem;">
        <TextInput bind:value={passwordName} placeholder="Password name" />
        <TextInput bind:value={passwordUrl} placeholder="Optional password url" />
        <Spacer/>


        <HStack class="hover-pointer" on:click={switchActionType}>
            <input type="checkbox" style="width: 20px; height: 20px" bind:checked={addPasswordMode}/>
            <span style="flex-grow: 5; text-align: center">
                Generate password for me
            </span>
        </HStack>
        {#if addPasswordMode}
            <TextInput bind:value={newPassword} placeholder="New Password" type="password"/>
            <TextInput bind:value={newPasswordRepeat} placeholder="Repeat new Password" type="password"/>
        {/if}

        <Spacer />
        <TextInput bind:value={storagePassword} placeholder="Your storage password" type="password"/>


        <Button style="margin-top: 20px" size="lg" on:click={onAddPassword}>
            Add password
        </Button>
    </VStack>
</Modal>

<style>
    :global(.hover-pointer:hover) {
        cursor: pointer;
    }
</style>