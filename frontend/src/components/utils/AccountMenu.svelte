<script lang="ts">
    import {authStore} from "../../stores/AuthStore";
    import {goto} from "$app/navigation";

    export let isOpen;


    const logout = () => {
        authStore.logout();
    }

    const goToDashboard = () => {
        goto("/dashboard");
    }

    const goToSettings = () => goto("/settings");
</script>


{#if isOpen}
    <div class="menu-wrapper v-stack" on:click={e => e.stopPropagation()}>

        <div class="menu-option" on:click={goToDashboard}>
            Dashboard
        </div>
        <div class="menu-option" on:click={goToSettings}>
            Settings
        </div>
        <div class="menu-option" on:click={logout}>
            Logout
        </div>


    </div>
{/if}


<style>
    .menu-wrapper {
        @apply z-20 absolute right-0 top-[100%] mt-2.5 py-4 min-w-[200px];
        @apply bg-background-700 rounded-sm shadow-xl text-xl;
    }

    .menu-wrapper::before {
        @apply bg-background-700;
        content: ' ';
        position: absolute;
        border-radius: 3px;
        z-index: -1;
        top: -4px;
        right: 9px;
        width: 15px;
        height: 15px;
        transform: rotate(45deg);
    }

    .menu-option {
        @apply text-center w-[90%] mx-2 py-2 relative;
    }

    .menu-option:hover {
        @apply bg-background-800 cursor-pointer text-primary-200 rounded-sm;
    }

    .menu-option::before {
        content: ' ';
        @apply w-full border-t border-primary-400
        opacity-70 h-[1px] left-0 top-0 absolute;
    }


</style>
