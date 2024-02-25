<script lang="ts">
    import {authStore} from "../../stores/AuthStore";
    import {goto} from "$app/navigation";
    import AccountButton from "./AccountButton.svelte";
    import {page} from "$app/stores";
    import type {Page} from "./Page";


    let activePage: string;

    const leftPages: Page[] = [
        ["Home", "/"],
        ["About", "/about"],
        ["Dashboard", "/dashboard"]
    ]

    const rightPages: Page[] = [
        ["Login", "/login"],
        ["Register", "/register"]
    ]

    function onLinkClick(newPage: Page) {
        goto(newPage[1]);
    }

    $: {
        activePage = $page.url.pathname;
    }

</script>


<div class="navbar">
    <div class="navbar-bg"></div>

    {#each leftPages as page, index}
        <div on:click={() => onLinkClick(page)} class:current-page={activePage === page[1]}
             class="link">
            {page[0]}
        </div>
        {#if index < leftPages.length - 1}
            <div class="link-spacer"></div>
        {/if}
    {/each}


    <div class="flex-grow"></div>


    {#if !$authStore.isAuthenticated}
        {#each rightPages as page, index}
            <div on:click={() => onLinkClick(page)} class:current-page={activePage === page[1]}
                 class="link">
                {page[0]}
            </div>
            {#if index < rightPages.length - 1}
                <div class="link-spacer"></div>
            {/if}
        {/each}
    {:else}
        <AccountButton/>
    {/if}

</div>



<style lang="postcss">

    .navbar {
        @apply w-full h-14 px-4 py-1 z-10 flex justify-start items-center ;
        @apply relative shadow-md;
    }

    .navbar-bg {
        @apply w-full h-full absolute top-0 left-0 z-[-1] bg-gradient-to-r opacity-50
        from-primary-600 to-primary-900;
    }

    .link {
        @apply text-3xl;
        @apply hover:text-primary-200 hover:underline hover:cursor-pointer dark:text-pink-500;
    }

    .current-page {
        @apply underline decoration-secondary-300 font-bold ;
    }

    .link-spacer {
        /*@apply border-r-2 border-white w-1 h-full mx-4;*/
        @apply mx-4 h-1 w-0;
    }


</style>