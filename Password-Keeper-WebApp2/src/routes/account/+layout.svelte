<script lang="ts">
    import SecondaryNavbar from "../../components/navigation/SecondaryNavbar.svelte";
    import {Page} from "../../components/navigation/Page";
    import Protected from "../../components/utils/Protected.svelte";

    const pages: Page[] = [
        ["Settings", "/account/settings"],
        ["Authorized devices", "/account/devices"],
        ["Login history", "/account/login-history"]
    ]
</script>

<SecondaryNavbar pages={pages}/>


<Protected>

    <slot/>

</Protected>