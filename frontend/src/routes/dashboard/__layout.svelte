<script lang="ts">
    import {onMount} from "svelte";
    import {authStore} from "../../stores/AuthStore";
    import {goto} from "$app/navigation";
    import Navbar from "../../components/utils/Navbar.svelte";

    let authenticated = false;
    onMount(() => {
        if(!$authStore.isAuthenticated) {
            goto("/");
        } else {
            authenticated = true;
        }
    });

</script>

{#if authenticated}
    <Navbar/>
    <slot></slot>
{/if}