<script lang="ts">
  import Button from "./Button.svelte";

  export let isOpen: boolean = true;
  export let style: string = "";
</script>


<div class="overlay" style={style} on:click={() => isOpen = false}>
    <div class="modal" on:click={e => e.stopPropagation()}>
        <Button style="position: absolute; right: 10px; top: 10px; width: 40px"
                on:click={() => isOpen = false}>
            X
        </Button>
        <slot></slot>
    </div>
</div>


<style>
    .overlay {
        background: rgba(0, 0, 0, 0.3);
        filter: blur(2);
        position: fixed;
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

        border-radius: 3px;
        box-shadow: 3px 3px 5px 2px rgba(0,0,0, 0.4);
    }
</style>