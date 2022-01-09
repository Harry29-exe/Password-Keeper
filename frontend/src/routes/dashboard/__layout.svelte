<script lang="ts">
    import {onMount} from "svelte";
    import {authStore} from "../../stores/AuthStore";
    import {goto} from "$app/navigation";

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
    <slot></slot>
{/if}