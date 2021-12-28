<script lang="ts">
    export let style = "";
</script>

<div style={style} class="HStack">
    <slot>

    </slot>
</div>

<style>
    .HStack {
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        flex-direction: row;
    }

    .HStack > * {
        margin-right: 8px;
    }
</style>