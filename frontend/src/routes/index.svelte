<script lang="ts">
  import "./../style/Utils.css"
  import Button from "../components/atomic/Button.svelte";
  import {goto} from "$app/navigation";

  const isTrue = true;
</script>

<svelte:head>
    <title>Welcome to password keeper</title>
</svelte:head>

<div class="wrapper flex-center" style="font-size: 24px">
    <h1>
        Password keeper
    </h1>

    <Button buttonName="Login" style="margin: 8px" size="lg" on:click={e => goto("/Dashboard")}/>

    <Button buttonName="Register" style="margin: 8px" size="lg" on:click={e => console.log("warming")}/>

    <div style="width: 1px; height: 200px"></div>
</div>


<style>
    .wrapper {
        width: 100vw;
        height: 100vh;
        flex-direction: column;
    }

</style>
