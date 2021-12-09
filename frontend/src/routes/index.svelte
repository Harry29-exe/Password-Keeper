<script lang="ts">
    import "./../style/Utils.css"
    import Button from "../components/atomic/Button.svelte";
    const isTrue = true;
</script>

<svelte:head>
    <title>Welcome to password keeper</title>
</svelte:head>

<div class="wrapper flex-center" >
    <h1>
        Password keeper
    </h1>

    <Button buttonName="Login" style="margin: 8px" size="lg" onClick={e => console.log("click as onClick")}/>

    <Button buttonName="Register" style="margin: 8px" size="lg" onClick={e => console.log("warming")}/>

</div>


<style>
    .wrapper {
        width: 100vw;
        height: 100vh;
        background: #182768;
        flex-direction: column;
    }

</style>
