<script lang="ts">
    let size = "50px"
</script>

<div style={`width: ${size}; height: ${size}; position: relative`}>
    <div class="outer">

    </div>
    <div class="inner">

    </div>
</div>


<style>
    .outer {
        border: 5px solid var(--gray-800);
        border-radius: 100%;
        position: relative;
        width: 100%;
        height: 100%;
        /*box-sizing: content-box;*/
    }

    .inner {
        border-top: 5px solid var(--primary-400);
        border-left: 5px solid var(--primary-400);

        border-top-left-radius: 100%;
        position: absolute;
        top: 0;
        left: 0;
        width: 50%;
        height: 50%;
        transform-origin: bottom right;
        animation-duration: 1s;
        animation-name: spinner;
        animation-iteration-count: infinite;
    }

    @keyframes spinner {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }
</style>

