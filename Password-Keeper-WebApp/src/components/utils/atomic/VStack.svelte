<script lang="ts">
    export let style = "";
</script>

<div class="v-stack" style={style}>
    <slot>

    </slot>
</div>

<style>
    .v-stack {
        width: 100%;
        display: flex;
        justify-content: flex-start;
        align-items: center;
        flex-direction: column;
        margin-right: auto;
        margin-left: auto;
    }

    .v-stack > * {
        margin-top: 8px;
    }
</style>