<script lang="ts">
    import Button from "../components/utils/atomic/Button.svelte";
    import {authStore} from "../stores/AuthStore";
    import {goto} from "$app/navigation";
    import TextInput from "../components/utils/atomic/TextInput.svelte";
    import Center from "../components/utils/atomic/Center.svelte";
    import CircularProgress from "../components/utils/atomic/CircularProgress.svelte";
    import {AuthApiCode} from "../logic/auth-api/AuthApi";
    import {fade} from "svelte/transition";

    let username = "";
    let password = "";
    let loginState: 'inProgress' | 'notInitialized' | 'error' = 'notInitialized';
    let errorMsg = "Bad username or password";
    let dontLogout = false;

    const onLoginClick = () => {
        loginState = 'inProgress';
        authStore.login(username, password, dontLogout)
            .then(status => {
                if (status == AuthApiCode.OK) {
                    goto("/dashboard");
                } else {
                    loginState = 'error';
                    if (status == AuthApiCode.UNKNOWN_DEVICE) {
                        errorMsg = "It's look like you are logging first time from this device. " +
                            "Please check your email for device authorization message";
                    } else {
                        errorMsg = "Bad username or password";
                    }
                }
            }).catch(reason => {
            loginState = 'error';
            errorMsg = "We could not authenticate you, please try later.";
        })
    }
</script>


<div class="v-stack w-2/3 text-2xl mt-10">
    <h1 class="text-4xl my-4">Login</h1>
    <div>
        Username:
        <TextInput autocomplete={"username"} bind:value={username} name="username"
                   placeholder="Username"/>
    </div>
    <div>
        Password:
        <TextInput autocomplete={"password"} bind:value={password} name="password"
                   placeholder="Password" type="password"/>
    </div>

    <Center style="position: relative">
        Do not logout me
        <input bind:checked={dontLogout} style="width: 20px; height: 20px; margin: 10px" type="checkbox">
    </Center>

    <div>
        <Button on:click={onLoginClick} size="lg" style="margin-top: 20px">
            Login
        </Button>

        <Center style="margin: 20px">
            {#if loginState === 'inProgress'}
                <CircularProgress/>
            {/if}
        </Center>
    </div>

    {#if loginState === 'error'}
        <div class="bg-primary-800 rounded-sm shadow-sm p-4" transition:fade>
            {errorMsg}
        </div>
    {/if}
</div>