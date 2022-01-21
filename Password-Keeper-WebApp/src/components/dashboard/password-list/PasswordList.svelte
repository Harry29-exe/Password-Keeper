<!--suppress ALL -->
<script lang="ts">
    import {PasswordInfoDTO} from "../../../logic/password-storage-api/PasswordInfoDTO";
    import Password from "./Password.svelte";
    import Button from "../../utils/atomic/Button.svelte";
    import {ComponentState, State} from "../../utils/ComponentState";
    import {onMount} from "svelte";
    import {authStore} from "../../../stores/AuthStore";
    import {PasswordAPI} from "../../../logic/password-storage-api/PasswordAPI";

    let passwordList: PasswordInfoDTO[] = [];
    let passwordsFetchStatus: ComponentState = new ComponentState(State.IN_PROGRESS);


    const fetchPasswords = () => {
        console.log('fetching passwords');
        if (!$authStore.isAuthenticated) throw new Error("Trying to fetch protected content, without being logged in");

        PasswordAPI.getPasswords($authStore.authToken as string)
            .then(passwords => {
                passwordList = passwords;
                passwordsFetchStatus = new ComponentState(State.FINISHED_SUCCESSFULLY);
            })
            .catch(reason => {
                passwordsFetchStatus = new ComponentState(State.ERROR, "Could not fetch passwords, try latter");
            })
    }

    onMount(
        fetchPasswords
    )
</script>


<div class="component-wrapper">

    <!--Header-->
    <div class="flex justify-between w-full text-2xl font-semibold p-2 px-[5%]">
        <div class="w-4/12">Password name</div>
        <div class="w-6/12">Password url</div>
        <div class="center w-2/12">
            <Button on:click={fetchPasswords} size="md" style="width: 100px">
                refresh
            </Button>
        </div>
    </div>
    <div class="spacer-h border-t-2"/>

    <div class="w-11/12">
        {#each passwordList as passwordInfo}
            <Password passwordInfo={passwordInfo}/>
            <div class="spacer-h"/>
        {/each}
    </div>

</div>


<style>
    .component-wrapper {
        /*dimmensions*/
        @apply w-[95%] sm:w-11/12 md:w-4/5 xl:w-3/4 max-h-[60vh] overflow-auto;
        /*behevior*/
        @apply flex flex-col justify-start items-center;
        /*style*/
        @apply bg-background-700 p-4 rounded-md shadow-md;
    }
</style>