<script>
    import '../app.css';
    import "./../style/Utils.css";
    import "./../style/StyleComponents.css";
    import {onMount} from 'svelte';
    import Navbar from "../components/navigation/Navbar.svelte";
    import {authStore} from "../stores/AuthStore";

    onMount(() => {
        if (!$authStore.isAuthenticated) {
            authStore.refreshAuth();
        }
    });
</script>

<Navbar/>

<slot/>