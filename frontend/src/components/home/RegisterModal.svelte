<script lang="ts">
    import {ComponentState, State} from "../utils/ComponentState";
    import Modal from "../utils/atomic/Modal.svelte";
    import VStack from "../utils/atomic/VStack.svelte";
    import TextInput from "../utils/atomic/TextInput.svelte";
    import Button from "../utils/atomic/Button.svelte";
    import Spacer from "../utils/atomic/Spacer.svelte";
    import Center from "../utils/atomic/Center.svelte";
    import {UserAPI} from "../../logic/user-api/UserAPI";
    import {CreateUserRequest} from "../../logic/user-api/CreateUserRequest";
    import {ResponseStatusU} from "../../logic/ResponseStatus";

    export let isOpen = false;

    let username = "";
    let password = "";
    let passwordRepeat = "";
    let storagePassword = "";
    let storagePasswordRepeat = "";

    let registerState = new ComponentState(State.NOT_INITIALIZED);
    let errorMsg = "";

    const onRegister = () => {
        if(password !== passwordRepeat) {
            registerState = new ComponentState(State.ERROR);
            errorMsg = "Password and repeat password field are different";
            return;
        } else if(storagePassword !== storagePasswordRepeat) {
            registerState = new ComponentState(State.ERROR);
            errorMsg = "Storage password and repeat storage password field are different";
            return;
        }

        registerState = new ComponentState(State.IN_PROGRESS);
        UserAPI.register(new CreateUserRequest(username, password, storagePassword))
            .then(status => {
                if(ResponseStatusU.isError(status)) {
                    registerState = new ComponentState(State.ERROR);
                    errorMsg = "We could not register you, try later or with different username";
                }
            })
    }

    const textInputStyle = "font-size: 1.3rem";
    const spacerStyle = "margin-top: 30px; margin-bottom: 30px";
</script>

<Button on:click={() => isOpen = true} size="lg">
    Register
</Button>

<Modal bind:isOpen>
    <VStack style="font-size: 1.5rem; width: 70%; margin: auto">
        <h1>Register</h1>

        <div class="input-module">
            Username:
            <TextInput placeholder="username" bind:value={username} style={textInputStyle}/>
        </div>
        <Spacer style={spacerStyle}/>


        <div class="input-module">
            Password: (it is used for logging to service)
            <TextInput placeholder="password" bind:value={password}
                       style={textInputStyle} type="password"/>
        </div>
        <div class="input-module">
            Repeat password:
            <TextInput placeholder="repeat password" bind:value={passwordRepeat}
                       style={textInputStyle} type="password"/>
        </div>
        <Spacer style={spacerStyle}/>


        <div class="input-module">
            Storage password (it is used for encrypting and decrypting your passwords):
            <TextInput placeholder="storage password" bind:value={storagePassword}
                       style={textInputStyle} type="password"/>
        </div>
        <div class="input-module">
            Repeat storage password:
            <TextInput placeholder="repeat storage password" bind:value={storagePasswordRepeat}
                       style={textInputStyle} type="password"/>
        </div>
        <Spacer style={spacerStyle}/>

        <Button on:click={onRegister} size="lg" style="margin-bottom: 30px">
            Register
        </Button>

        {#if registerState.isError()}
            <Center style="color: var(--warning-400)">
                {errorMsg}
            </Center>
        {:else if registerState.isFinishedSuccessfully()}
            <Center style="color: var(--success-400)">
                You have successfully register, please close this dialog and login
            </Center>
        {/if}


    </VStack>
</Modal>



<style>
    .input-module {
        width: 100%;
    }
</style>