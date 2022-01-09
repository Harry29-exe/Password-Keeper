<script lang="ts">
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



    <div style="width: 100%; height: 20px"></div>

    <RegisterModal/>

    <div style="width: 1px; height: 200px"></div>
</div>


<style>
    .wrapper {
        width: 100%;
        height: 100%;
        flex-direction: column;
    }

</style>
