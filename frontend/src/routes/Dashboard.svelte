<script lang="ts">
    import Modal from "../components/atomic/Modal.svelte";
    import Button from "../components/atomic/Button.svelte";

    let isOpen = false;
</script>

<div class="wrapper">
    Wellcome to dashboard
    {#if isOpen}
        <Modal bind:isOpen={isOpen}/>
    {/if}

    <Button on:click={() => isOpen = true}/>
</div>


<style>
    .wrapper {
        width: 100vw;
        height: 100vh;
        flex-direction: column;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>