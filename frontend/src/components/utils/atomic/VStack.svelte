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
        justify-content: flex-start;
        align-items: center;
        flex-direction: column;
        margin-right: auto;
        margin-left: auto;
    }

    .HStack > * {
        margin-top: 8px;
    }
</style>