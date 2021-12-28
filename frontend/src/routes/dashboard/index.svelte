<script lang="ts">
    import Modal from "../../components/utils/atomic/Modal.svelte";
    import Button from "../../components/utils/atomic/Button.svelte";
    import {AuthHolder, authStore} from "../../stores/AuthStore";
    import {onDestroy, onMount} from "svelte";
    import {goto} from "$app/navigation";


</script>

<div class="wrapper">
    Wellcome to dashboard


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