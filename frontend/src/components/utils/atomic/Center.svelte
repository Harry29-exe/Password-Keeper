<script>
    export let style = "";
    let clazz = "";
    export {clazz as class};
</script>


<div class={clazz} style={style}>
    <slot></slot>
</div>

<style>
    div {
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>