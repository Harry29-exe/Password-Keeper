<script lang="ts">
    export let cssClass = "";
    export let style = "";
    export let value = "";
    export let placeholder = "";
    export let type: "password" | "text" = "text"

    const onchange = (e: Event) => {
        let target = e.target;
        if(target instanceof HTMLInputElement) {
            value = target.value;
        }
    }
</script>

<input on:change={onchange} style={style} class={cssClass} type={type}
           placeholder={placeholder}/>



<style>
    input {
        background: var(--gray-800);
        border: 2px solid var(--primary-600);
        margin-top: 8px;
        margin-bottom: 8px;
        width: 100%;
        border-radius: 2px;
        box-shadow: 1px 1px 3px 1px rgba(0,0,0,0.4);
        padding: 5px;
        font-size: 1.25rem;
    }
</style>