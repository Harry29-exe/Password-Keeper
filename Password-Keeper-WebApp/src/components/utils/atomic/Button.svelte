<script lang="ts">
    import "../../../style/ComponentSize.css";
    import "../../../style/Utils.css";
    import {createEventDispatcher} from "svelte";

    export let buttonType: ButtonStyle = "Primary";
    export let size: "sm" | "md" | "lg" = "md";
    export let style = "";
    let clazz = "";
    export {clazz as class};

    type ButtonStyle = "Primary" | "Success" | "Danger";

    const dispatch = createEventDispatcher();
    const onClick = (e: MouseEvent) => {
        dispatch('click', e);
    }

</script>


<div class={` ${buttonType} Button ${clazz}`}
     on:click={e => onClick(e)} style={style}>
    <slot></slot>
</div>

<style>

    .Button {
        @apply rounded-sm shadow-md hover:shadow-xl bg-primary-800 px-4 py-2;
        @apply hover:cursor-pointer hover:bg-primary-600;

        @apply font-semibold text-xl;
        display: flex;
        justify-content: center;
        align-items: center;

        /*box-shadow: 2px 2px 4px 2px rgba(0, 0, 0, 0.4);*/
    }

    .Success {
        background: var(--success-800);
    }

    .Success:hover {
        background: var(--success-600);
    }

    /*.Warming {*/
    /*    background: var(--warining-800);*/
    /*}*/

    /*.Warming:hover {*/
    /*    background: var(--warining-600);*/
    /*}*/
</style>
