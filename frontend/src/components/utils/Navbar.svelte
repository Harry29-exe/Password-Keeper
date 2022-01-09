<script lang="ts">
    import {authStore} from "../../stores/AuthStore";
    import {goto} from "$app/navigation";

    type Page = [string, string]
    const pages: Page[] = [
        ["Home", "/"],
        ["About", "/about"]
    ]

    const authPages: Page[] = [
        ["Login", "/login"],
        ["Register", "/register"]
    ]

    function onLinkClick(page: Page) {
        goto(page[1])
    }

</script>


<div class="navbar">
    {#each pages as page, index}
        <div class="link" on:click={() => onLinkClick(page)}>
            {page[0]}
        </div>
        {#if index < pages.length - 1}
            <div class="link-spacer"></div>
        {/if}
    {/each}


    <div class="flex-grow"></div>

    {#if !$authStore.isAuthenticated}
        {#each authPages as page, index}
            <div class="link" on:click={() => onLinkClick(page)}>
                {page[0]}
            </div>
            {#if index < pages.length - 1}
                <div class="link-spacer"></div>
            {/if}
        {/each}
    {/if}

</div>



<style>
    .navbar {
        @apply w-full h-16 flex justify-start items-center bg-sky-700 p-4;
    }

    .link {
        @apply text-3xl;
        @apply hover:text-sky-200 hover:underline hover:cursor-pointer;
    }

    .link-spacer {
        @apply border-r-2 border-white w-1 h-full mx-2;
    }


</style>