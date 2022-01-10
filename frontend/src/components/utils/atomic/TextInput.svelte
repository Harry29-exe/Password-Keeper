<script lang="ts">
    let clazz = "";
    export {clazz as class};

    export let style = "";
    export let value = "";
    export let placeholder = "";
    export let name = "";
    export let autocomplete = false;
    export let type: "password" | "text" = "text"
    export let disabled = false;

    const onchange = (e: Event) => {
        let target = e.target;
        if (target instanceof HTMLInputElement) {
            value = target.value;
        }
    }
</script>

<input autocomplete={autocomplete}
       class={clazz}
       name={name}
       on:change={onchange}
       style={`${style} ${disabled? "background: var(--gray-700); color: var(--gray-300)": ""}`} type={type}
       disabled={disabled} placeholder={placeholder}/>



<style>
    input {
        @apply my-2 p-2 text-xl shadow-md bg-gray-800 border-2 border-primary-600;
        @apply transition-[border-color];
        @apply focus-visible:border-primary-300 focus-visible:outline-0 rounded;
        width: 100%;
    }
</style>