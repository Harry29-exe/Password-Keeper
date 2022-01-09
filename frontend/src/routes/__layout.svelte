<script>
    import '../app.css';
    import "./../style/Utils.css";
    import "./../style/StyleComponents.css";
    import {onMount} from 'svelte';
    import Navbar from "../components/utils/Navbar.svelte";

    onMount(() => {
    });
</script>

<Navbar/>
<slot/>
