<script lang="ts">
    export let style = "";
</script>

<div style={style} class="VStack">
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