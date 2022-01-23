<script lang="ts">
    import TextInput from "../components/utils/atomic/TextInput.svelte";
    import {ComponentState, State} from "../components/utils/ComponentState";
    import {UserAPI} from "../logic/user-api/UserAPI";
    import {CreateUserRequest} from "../logic/user-api/CreateUserRequest";
    import {ResponseStatusU} from "../logic/ResponseStatus";
    import Button from "../components/utils/atomic/Button.svelte";
    import {MsgType, popupStore} from "../stores/PopupStore";

    const errorTitle = "We could not register you";
    const tryLatter = "We could not register you, try later or with different username";
    const differentStoragePasswords = "Storage password and repeat storage password field are different";
    const differentPasswords = "Password and repeat password field are different";

    const onRegister = () => {
        if (password !== passwordRepeat) {
            popupStore.setMsg(errorMsg, differentPasswords, MsgType.DANGER);
            return;

        } else if (storagePassword !== storagePasswordRepeat) {
            popupStore.setMsg(errorMsg, differentStoragePasswords, MsgType.DANGER);
            return;
        }

        registerState = new ComponentState(State.IN_PROGRESS);
        UserAPI.register(new CreateUserRequest(username, email, password, storagePassword))
            .then(status => {
                if (ResponseStatusU.isError(status)) {
                    popupStore.setMsg(errorTitle, tryLatter, MsgType.DANGER);

                } else if (ResponseStatusU.isOk(status)) {
                    popupStore.setMsg("Success", "You have been registered. Please precede to login page");
                }
            })
    }

    let username = "";
    let email = "";
    let password = "";
    let passwordRepeat = "";
    let storagePassword = "";
    let storagePasswordRepeat = "";

    let registerState = new ComponentState(State.NOT_INITIALIZED);
    let errorMsg = "";

    const textInputStyle = "font-size: 1.3rem";
</script>


<div class="v-stack w-11/12 sm:w-4/5 lg:w-1/2 mt-10">
    <h1 class="text-3xl">Register</h1>

    <div class="input-module">
        Username:
        <TextInput bind:value={username} placeholder="username" style={textInputStyle}/>
    </div>
    <div class="spacer-h-md"/>

    <div class="input-module">
        Email:
        <TextInput bind:value={email} placeholder="email" style={textInputStyle}/>
    </div>
    <div class="spacer-h-md"/>


    <div class="input-module">
        Password: (it is used for logging to service)
        <TextInput autocomplete="new-password" bind:value={password} placeholder="Enter password"
                   style={textInputStyle} type="password"/>
    </div>
    <div class="input-module">
        Repeat password:
        <TextInput bind:value={passwordRepeat} placeholder="repeat password"
                   style={textInputStyle} type="password"/>
    </div>
    <dvi class="spacer-h-md"/>


    <div class="input-module">
        Storage password (it is used for encrypting and decrypting your passwords):
        <TextInput bind:value={storagePassword} placeholder="storage password"
                   style={textInputStyle} type="password"/>
    </div>
    <div class="input-module">
        Repeat storage password:
        <TextInput bind:value={storagePasswordRepeat} placeholder="repeat storage password"
                   style={textInputStyle} type="password"/>
    </div>
    <div class="spacer-h-md"/>

    <Button on:click={onRegister} size="lg" style="margin-bottom: 30px">
        Register
    </Button>

</div>


<style>
    .input-module {
        width: 100%;
    }
</style>