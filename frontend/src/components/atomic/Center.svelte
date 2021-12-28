<script>
    export let style = "";
</script>


<div style={style}>
    <slot></slot>
</div>

<style>
    div {
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>