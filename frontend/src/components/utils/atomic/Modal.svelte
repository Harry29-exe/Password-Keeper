<script lang="ts">
  import Button from "./Button.svelte";

  export let isOpen: boolean;
  export let style: string = "";
</script>

{#if isOpen}
<div class="overlay"  on:click={() => isOpen = false}>
    <div class="modal" on:click={e => e.stopPropagation()} style={style}>
        <Button style="position: absolute; right: 10px; top: 10px; width: 40px"
                on:click={() => isOpen = false}>
            X
        </Button>
        <slot></slot>
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

        transform-origin: top;
        animation: modal-in 0.2s ease-in 0s;

    }

    @keyframes modal-in {
        0% {
            transform: scale(0);
        }
        100% {
            transform: scale(1.0);
        }
    }
</style>