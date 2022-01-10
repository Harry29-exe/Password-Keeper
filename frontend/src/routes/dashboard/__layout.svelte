<script lang="ts">
    import {authStore} from "../../stores/AuthStore";
</script>

{#if $authStore.isAuthenticated}
    <slot></slot>
{:else}
    <div class="center w-full min-h-[400px]">
        <span class="text-3xl">
            To access dashboard please register or login
        </span>
    </div>
{/if}