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
        flex-direction: column;
    }

    .VStack > * {
        margin-top: 8px;
    }
</style>