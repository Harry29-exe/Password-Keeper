<script lang="ts">
    import {authStore} from "../../stores/AuthStore";
    import {goto} from "$app/navigation";
    import AccountButton from "./AccountButton.svelte";
    import {onDestroy, onMount} from "svelte";
    import {page} from "$app/stores";

    let activePage: string = "Home";

    type Page = [string, string]
    const pages: Page[] = [
        ["Home", "/"],
        ["About", "/about"],
        ["Dashboard", "/dashboard"]
    ]

    const authPages: Page[] = [
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
    {#each pages as page, index}
        <div on:click={() => onLinkClick(page)} class:current-page={activePage === page[1]}
             class="link">
            {page[0]}
        </div>
        {#if index < pages.length - 1}
            <div class="link-spacer"></div>
        {/if}
    {/each}


    <div class="flex-grow"></div>

    {#if !$authStore.isAuthenticated}
        {#each authPages as page, index}
            <div on:click={() => onLinkClick(page)} class:current-page={activePage === page[1]}
                 class="link">
                {page[0]}
            </div>
            {#if index < authPages.length - 1}
                <div class="link-spacer"></div>
            {/if}
        {/each}
    {:else}
        <AccountButton/>
    {/if}

</div>



<style>
    .navbar {
        @apply w-full h-16 p-4 flex justify-start items-center bg-primary-900;
        @apply shadow-xl;
    }

    .link {
        @apply text-3xl;
        @apply hover:text-primary-200 hover:underline hover:cursor-pointer;
    }

    .current-page {
        @apply underline decoration-secondary-300 font-bold ;
    }

    .link-spacer {
        @apply border-r-2 border-white w-1 h-full mx-4;
    }


</style>