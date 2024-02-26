<script lang="ts">
    import type {Page} from "./Page";
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
    <div class="navbar-bg"></div>
    {#each pages as page, index}
        <div on:click={() => onLinkClick(page)} class:current-page={activePage === page[1]}
             class="link">
            {page[0]}
        </div>
    {/each}


</div>


<style lang="postcss">
    .navbar {
        @apply relative w-full h-9 p-4 z-0 flex justify-start items-center;
        @apply shadow-xl;
    }

    .navbar-bg {
        @apply w-full h-full absolute top-0 left-0 z-[-1] bg-gradient-to-r opacity-50
        from-primary-600 to-primary-900;
    }

    .link {
        @apply text-xl mr-6;
        @apply hover:text-primary-200 hover:underline hover:cursor-pointer;
    }

    .current-page {
        @apply underline decoration-secondary-300 font-bold ;
    }

    .link-spacer {
        @apply border-r-2 border-white w-1 h-full mx-4;
    }


</style>