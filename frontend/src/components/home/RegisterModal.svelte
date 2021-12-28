<script lang="ts">
    import {ComponentState, State} from "../utils/ComponentState";
    import Modal from "../utils/atomic/Modal.svelte";
    import VStack from "../utils/atomic/VStack.svelte";
    import TextInput from "../utils/atomic/TextInput.svelte";

    export let isOpen = false;

    let username = "";
    let password = "";
    let passwordRepeat = "";
    let storagePassword = "";
    let storagePasswordRepeat = "";

    let registerState = new ComponentState(State.NOT_INITIALIZED);
</script>

<Modal bind:isOpen>
    <VStack style="font-size: 2rem; width: 70%; margin: auto">
        <h1>Register</h1>

        <TextInput placeholder="username"/>

    </VStack>
</Modal>


