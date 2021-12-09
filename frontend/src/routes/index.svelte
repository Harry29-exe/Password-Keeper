<script lang="ts">
  import "./../style/Utils.css"
  import Button from "../components/atomic/Button.svelte";
  import {goto} from "$app/navigation";
  import Modal from "../components/atomic/Modal.svelte";
  import LoggingModule from "../components/modules/LoggingModule.svelte";

  let loginModal = false;
  let registerModal = false;
</script>

<svelte:head>
    <title>Welcome to password keeper</title>
</svelte:head>

<div class="wrapper flex-center" style="font-size: 24px">
    <h1>
        Password keeper
    </h1>

    <LoggingModule/>

    <Button style="margin: 8px" size="lg">
        Register
    </Button>
    {#if registerModal}
        <Modal isOpen={registerModal}>

        </Modal>
    {/if}

    <div style="width: 1px; height: 200px"></div>
</div>


<style>
    .wrapper {
        width: 100vw;
        height: 100vh;
        flex-direction: column;
    }

</style>
