<script lang="ts">
    import {popupStore} from "../../stores/PopupStore";
    import {fly} from "svelte/transition";

    console.log('dupa 123')

    function close() {
        popupStore.close();
    }
</script>

{#if $popupStore.open}
    <div class={`popup v-stack`} transition:fly>
        <div class={`popup-bg ${$popupStore.msgType}`}></div>
        <div class="text-2xl w-full font-semibold">{$popupStore.msgTitle}</div>
        <div class="w-full py-2">{$popupStore.msg}</div>

        <button class="absolute right-3 top-2 btn-close" on:click={close}>
            X
        </button>
    </div>
{/if}


<style lang="postcss">
    .popup {
        @apply fixed bottom-[6.5%] py-5 px-6 text-left min-w-[300px] z-[100];
        left: 50%;
        transform: translateX(-50%);
    }

    .popup-bg {
        @apply z-[-1] absolute left-0 top-0 w-full h-full rounded-md shadow-md;
        backdrop-filter: blur(2px) brightness(20%);
    }

    .danger {
        @apply bg-red-500/40;
    }

    .warning {
        @apply bg-amber-400/40;
    }

    .info {
        @apply bg-blue-400/40;
    }

    .success {
        @apply bg-green-500/40;
    }
</style>