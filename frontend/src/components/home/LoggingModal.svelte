<script lang="ts">
    import Button from "../utils/atomic/Button.svelte";
    import Modal from "../utils/atomic/Modal.svelte";
    import VStack from "../utils/atomic/VStack.svelte";
    import TextInput from "../utils/atomic/TextInput.svelte";
    import {authStore} from "../../stores/AuthStore";
    import {ResponseStatusU} from "../../logic/ResponseStatus";
    import CircularProgress from "../utils/atomic/CircularProgress.svelte";
    import Center from "../utils/atomic/Center.svelte";
    import {goto} from "$app/navigation";

    let modalOpen = false;
    let username = "";
    let password = "";
    let loginState: 'inProgress' | 'notInitialized' | 'error' = 'notInitialized';
    let errorMsg = "Bad username or password";
    let dontLogout = false;

    const onLoginClick = () => {
        loginState = 'inProgress';
        authStore.login(username, password, dontLogout)
            .then(status => {
                if(ResponseStatusU.isOk(status)) {
                    modalOpen = false;
                    goto("/dashboard");
                } else {
                    loginState = 'error';
                }
            });
    }
</script>


<Button on:click={() => modalOpen = true} size="lg">
    Login
</Button>

{#if modalOpen}
    <Modal bind:isOpen={modalOpen}>
        <VStack style="font-size: 1.5rem; width: 70%; margin: auto">
            <h1>Login</h1>
            <div>
                Username:
                <TextInput bind:value={username} placeholder="Username"
                           style="font-size: 1.3rem"/>
            </div>
            <div>
                Password:
                <TextInput bind:value={password} placeholder="Password"
                           style="font-size: 1.3rem" type="password"/>
            </div>

            <Center style="position: relative">
                Do not logout me
                <input type="checkbox" bind:checked={dontLogout} style="width: 20px; height: 20px; margin: 10px">
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
                <VStack style="background-color: var(--gray-800)">
                    {errorMsg}
                </VStack>
            {/if}
        </VStack>
    </Modal>
{/if}