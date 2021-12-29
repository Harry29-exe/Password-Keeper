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
        display: flex;
        justify-content: flex-start;
        align-items: center;
        flex-direction: column;
        margin-right: auto;
        margin-left: auto;
    }

    .VStack > * {
        margin-top: 8px;
    }
</style>