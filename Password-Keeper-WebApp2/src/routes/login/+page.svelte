<script lang="ts">
	import {authStore} from "../../stores/AuthStore";
	import {goto} from "$app/navigation";
	import TextInput from "../../components/utils/atomic/TextInput.svelte";
	import CircularProgress from "../../components/utils/atomic/CircularProgress.svelte";
	import {fade} from "svelte/transition";
	import {ErrorBody} from "../../logic/ErrorBody";
	import {ErrorCode} from "../../logic/ErrorCode";
	import {popupStore} from "../../stores/PopupStore";

	let username = "";
	let password = "";
	let inProgress: boolean = false;
	let errorMsg = "Bad username or password";
	let dontLogout = false;

	const onLoginClick = () => {
		inProgress = true;
		authStore.login(username, password, dontLogout)
			.then(() => {
				goto("/dashboard");
				inProgress = false;
			})
			.catch((reason: ErrorBody) => {
				switch (reason.errorCode) {
					case ErrorCode.BAD_CREDENTIALS:
						popupStore.danger("Bad username or password");
						break;
					case ErrorCode.AUTHENTICATION_FAILED:
						popupStore.danger("We could not log you. This is most likely because your device is not authorized. " +
							"In such case you should receive email with authorization link. (In this preview email is printed to console)");
						break;
					default:
						popupStore.danger("We could not log you in. This is most likely because you account is locked because of too many unsuccessful " +
							"login attempts, please try later or write to our support");
				}
				inProgress = false;
			})
	}

	// loginState = 'error';
	// if (status == AuthApiCode.UNKNOWN_DEVICE) {
	//     errorMsg = "It's look like you are logging first time from this device. " +
	//         "Please check your email for device authorization message";
	// } else {
	//     errorMsg = "Bad username or password";
	// }
</script>


<div class="v-stack w-2/3 text-2xl mt-10">
	<h1 class="text-4xl my-4">Login</h1>
	<div>
		Username:
		<TextInput autocomplete={"username"} bind:value={username} name="username"
							 placeholder="Username"/>
	</div>
	<div>
		Password:
		<TextInput autocomplete={"password"} bind:value={password} name="password"
							 placeholder="Password" type="password"/>
	</div>

	<div class="center relative">
		Do not logout me
		<input bind:checked={dontLogout} class="w-6 h-6 m-5" type="checkbox">
	</div>

	<div>
		<button class="mt-8 btn-primary-lg" on:click={onLoginClick}>
			Login
		</button>

		{#if inProgress}
			<div class="center mt-8" transition:fade>
				<CircularProgress/>
			</div>
		{/if}

	</div>

</div>