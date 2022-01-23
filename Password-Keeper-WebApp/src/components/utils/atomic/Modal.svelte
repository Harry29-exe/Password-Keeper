<script lang="ts">
    import Button from "./Button.svelte";
    import {fly, scale} from "svelte/transition";

    export let isOpen: boolean;
    export let style: string = "";

    let overlayMouseDown = false;

    function close() {
        if (overlayMouseDown) {
            isOpen = false;
        }
        overlayMouseDown = false;
    }
</script>


{#if isOpen}
    <div class="overlay" on:mousedown={() => overlayMouseDown = true} on:mouseup={close} transition:fly>
        <div class="modal" on:mousedown={e => e.stopPropagation()} style={style} transition:scale>
            <Button class="absolute top-4 right-4 w-12 h-8 text-2xl font-bold"
                    on:click={() => isOpen = false}>
                X
            </Button>
            <div class="center my-4 font-semibold w-full min-h-[30px]">
                <slot name="header"/>
            </div>
            <div>
                <slot></slot>
            </div>
        </div>
</div>
{/if}


<style>
    .overlay {
        background: rgba(0, 0, 0, 0.3);
        /*backdrop-filter: blur(2px);*/
        position: fixed;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
        z-index: 100;

        display: flex;
        justify-content: center;
        align-items: center;
    }

    .modal {
        background: var(--gray-700);
        position: fixed;
        z-index: 101;
        min-width: 40%;
        min-height: 50%;
        margin-bottom: 10%;
        margin-top: 10%;

        border-radius: 3px;
        box-shadow: 3px 3px 5px 2px rgba(0,0,0, 0.4);

    }
</style>