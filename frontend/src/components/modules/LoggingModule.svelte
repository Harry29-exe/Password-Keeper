<script lang="ts">
    import Button from "../atomic/Button.svelte";
    import Modal from "../atomic/Modal.svelte";
    import VStack from "../atomic/VStack.svelte";
    import TextInput from "../atomic/TextInput.svelte";

    let modalOpen = false;
    let login = "";
    let password = "";

    const fetchLogin = () => {

    }
</script>


<Button on:click={() => modalOpen = true} size="lg">
    Login
</Button>

{#if modalOpen}
    <Modal bind:isOpen={modalOpen}>
        <VStack style="font-size: 2rem; width: 70%; margin: auto">
            <h1>Login</h1>
            <TextInput bind:value={login} placeholder="Login"
                       style="height: 30px; font-size: 1.4rem"/>
            <TextInput bind:value={password} placeholder="Password"
                       style="height: 30px; font-size: 1.4rem"/>

            <Button on:click={fetchLogin} size="lg" style="margin-top: 20px">
                Login
            </Button>
        </VStack>
    </Modal>
{/if}