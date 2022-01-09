<script lang="ts">
    import "./../style/Utils.css"
    import Button from "../components/utils/atomic/Button.svelte";
    import LoggingModule from "../components/home/LoggingModal.svelte";
    import RegisterModal from "../components/home/RegisterModal.svelte";

    let loginModal = false;
    let registerModal = false;
</script>

<svelte:head>
    <title>Welcome to password keeper</title>
</svelte:head>

<div class="wrapper flex-center" style="font-size: 24px">

    <span class="text-5xl">
        Password keeper
    </span>

    <br/><br/>

    <Button class="" on:click={() => loginModal = true} size="lg">
        Login
    </Button>
    <LoggingModule bind:modalOpen={loginModal}/>

    <div style="width: 100%; height: 20px"></div>

    <RegisterModal/>

    <div style="width: 1px; height: 200px"></div>
</div>


<style>
    .wrapper {
        width: 100vw;
        height: 100vh;
        flex-direction: column;
    }

</style>
