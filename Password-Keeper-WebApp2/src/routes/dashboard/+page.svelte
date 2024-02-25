<script lang="ts">
	import PasswordList from "../../components/dashboard/password-list/PasswordList.svelte";
	import AddPassword from "../../components/dashboard/AddPassword.svelte";
	import Protected from "../../components/utils/Protected.svelte";
</script>

<Protected>

	<div class="wrapper">

		<h1 class="center text-4xl my-10 font-bold">Welcome to dashboard</h1>


		<PasswordList/>

		<AddPassword/>

	</div>


	<div class="center w-full min-h-[400px]" slot="unauthenticated">
        <span class="text-3xl">
            To access dashboard please register or login
        </span>
	</div>

</Protected>



<style lang="postcss">
    .wrapper {
        width: 100%;
        height: 100%;
        flex-direction: column;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>