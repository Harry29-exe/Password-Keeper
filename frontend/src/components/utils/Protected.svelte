<script lang="ts">
    import {authStore} from "../../stores/AuthStore";
</script>


{#if $authStore.isAuthenticated}

    <slot></slot>

{:else if $authStore.isAuthenticated === undefined}

    <slot name="authenticating">
        <span class="text-primary-300">Please wait as we are trying to authenticate you</span>
    </slot>

{:else}

    <slot name="unauthenticated">
        <span class="text-red-500">You must be logged in to view this page</span>
    </slot>

{/if}