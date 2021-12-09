<script lang="ts">
  import "./../../style/ComponentSize.css";
  import "./../../style/Utils.css";
  import {createEventDispatcher} from "svelte";

  export let buttonType: ButtonStyle = "Primary";
  export let size: "sm" | "md" | "lg" = "md";
  export let style = "";

  type ButtonStyle = "Primary" | "Success" | "Danger";

  const dispatch = createEventDispatcher();
  const onClick = (e: MouseEvent) => {
    dispatch('click', e);
  }

</script>


<div on:click={e => onClick(e)} class={`${buttonType} ${size} Button`} style={style}>
    <slot></slot>
</div>

<style>

    .Button {
        display: flex;
        justify-content: center;
        align-items: center;

        border-radius: 2px;
        box-shadow: 2px 2px 4px 2px rgba(0, 0, 0, 0.4);
    }

    .Button:hover {
        cursor: pointer;
    }

    .Primary {
        background: var(--primary-800);
    }

    .Primary:hover {
        background: var(--primary-600);
    }

    .Success {
        background: var(--success-800);
    }

    .Success:hover {
        background: var(--success-600);
    }

    .Warming {
        background: var(--warining-800);
    }

    .Warming:hover {
        background: var(--warining-600);
    }
</style>
