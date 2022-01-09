<script lang="ts">
    export let style = "";
    export let cssClass = "";
    export {cssClass as class};
</script>

<div class={`v-stack ${cssClass}`} on:click style={style}>
    <slot>

    </slot>
</div>

<style>
    .v-stack {
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

    .v-stack > * {
        margin-right: 8px;
    }
</style>