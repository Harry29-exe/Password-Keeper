<script lang="ts">
    import PasswordList from "../../components/dashboard/password-list/PasswordList.svelte";
    import AddPassword from "../../components/dashboard/AddPassword.svelte";
</script>

<div class="wrapper">
    <h1>Wellcome to dashboard</h1>


    <PasswordList/>

    <AddPassword/>

</div>


<style>
    .wrapper {
        width: 100%;
        height: 100%;
        flex-direction: column;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>