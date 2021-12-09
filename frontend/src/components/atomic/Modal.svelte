<script lang="ts">
  export let isOpen: boolean = true;
  export let style: string = "";
</script>


<div class="overlay">
    <div class="modal">
        <div class="close-button" on:click={() => {
            console.log("close");
            isOpen = false;
        }}>Close</div>
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

        border-radius: 10px;
        box-shadow: 2px 2px 4px 2px rgba(0,0,0, 0.4);
    }

    .close-button {
        position: absolute;
        right: 10px;
        top: 10px;
    }
</style>