<script lang="ts">

    import Protected from "../../components/utils/Protected.svelte";
</script>


<Protected>
    <div class="center mt-10 text-2xl">
        Settings mock
    </div>

    <div slot="unauthenticated">
        Login to see your settings
    </div>

    <div slot="authenticating">
        Please wait while we are trying to sign you in
    </div>

</Protected>