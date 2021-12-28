<script lang="ts">
    import Button from "../atomic/Button.svelte";
    import Modal from "../atomic/Modal.svelte";
    import VStack from "../atomic/VStack.svelte";
    import TextInput from "../atomic/TextInput.svelte";
    import {AuthStore} from "../../stores/AuthStore";
    import {ResponseStatusU} from "../../logic/ResponseStatus";
    import CircularProgress from "../atomic/CircularProgress.svelte";
    import Center from "../atomic/Center.svelte";

    let modalOpen = false;
    let username = "";
    let password = "";
    let loginState: 'inProgress' | 'notInitialized' | 'error' = 'notInitialized';
    let errorMsg = "Bad username or password";

    const onLoginClick = () => {
        AuthStore.login(username, password)
            .then(status => {
                if(ResponseStatusU.isOk(status)) {
                    modalOpen = false;
                }
            });
    }
</script>


<Button on:click={() => modalOpen = true} size="lg">
    Login
</Button>

{#if modalOpen}
    <Modal bind:isOpen={modalOpen}>
        <VStack style="font-size: 2rem; width: 70%; margin: auto">
            <h1>Login</h1>
            <TextInput bind:value={username} placeholder="Login"
                       style="height: 30px; font-size: 1.4rem"/>
            <TextInput bind:value={password} placeholder="Password"
                       style="height: 30px; font-size: 1.4rem"/>

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
                <VStack style={`background-color: black`}>
                </VStack>
            {/if}
        </VStack>
    </Modal>
{/if}