<script lang="ts">
    import {Page} from "./Page";
    import {goto} from "$app/navigation";
    import {page} from "$app/stores";

    export let pages: Page[];

    let activePage: string = "";

    $: {
        activePage = $page.url.pathname;
    }

    function onLinkClick(newPage: Page) {
        goto(newPage[1]);
    }
</script>


<div class="navbar">

    {#each pages as page, index}
        <div on:click={() => onLinkClick(page)} class:current-page={activePage === page[1]}
             class="link">
            {page[0]}
        </div>
    {/each}


</div>


<style>
    .navbar {
        @apply w-full h-12 p-4 z-0 flex justify-start items-center bg-primary-900;
        @apply shadow-xl;
    }

    .link {
        @apply text-2xl mr-6;
        @apply hover:text-primary-200 hover:underline hover:cursor-pointer;
    }

    .current-page {
        @apply underline decoration-secondary-300 font-bold ;
    }

    .link-spacer {
        @apply border-r-2 border-white w-1 h-full mx-4;
    }


</style>