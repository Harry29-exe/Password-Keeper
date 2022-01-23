<script>
    import {authStore} from "../../stores/AuthStore";
    import AccountMenu from "./AccountMenu.svelte";
    import {onDestroy, onMount} from "svelte";

    let isMenuOpen = false;

    const close = () => {
        console.log('window click')
        if (isMenuOpen) {
            isMenuOpen = false;
        }
    }

    onMount(() => window.addEventListener('click', close));
    onDestroy(() => window.removeEventListener('click', close));

</script>

<div class="relative" on:click={e => e.stopPropagation()}>
    <button class="rounded-[100%] min-w-[2rem] min-h-[2rem] bg-secondary-500 text-2xl relative shadow-sm"
            on:click={() => isMenuOpen = !isMenuOpen}
    >
        {$authStore.username.charAt(0)}

    </button>

    <AccountMenu bind:isOpen={isMenuOpen}/>

</div>

