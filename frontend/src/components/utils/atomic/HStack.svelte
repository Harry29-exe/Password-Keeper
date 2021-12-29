<script lang="ts">
    export let style = "";
    export let cssClass = "";
    export {cssClass as class};
</script>

<div style={style} class={`VStack ${cssClass}`} on:click>
    <slot>

    </slot>
</div>

<style>
    .VStack {
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: flex-start;
        align-items: center;
        flex-direction: row;
        margin-left: auto;
        margin-right: auto;
        position: relative;
    }

    .VStack > * {
        margin-right: 8px;
    }
</style>