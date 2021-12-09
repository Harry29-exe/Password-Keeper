<script lang="ts">
    export let style = "";
    export let value = "";
    export let placeholder = "";
    export let type: "password" | "text" = "text"
</script>

{#if type === "text"}
    <input bind:value={value} style={style} type="text"
           placeholder={placeholder}/>
{/if}

{#if type === "password"}
    <input bind:value={value} style={style} type="password"
           placeholder={placeholder}/>
{/if}


<style>
    input {
        background: var(--gray-800);
        border: 2px solid var(--primary-600);
        margin: 8px 0 8px 0;
        width: 100%;
        border-radius: 2px;
        box-shadow: 1px 1px 3px 1px rgba(0,0,0,0.4);
        padding: 5px;
    }
</style>