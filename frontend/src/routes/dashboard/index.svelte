<script lang="ts">
    import {authStore} from "../../stores/AuthStore";
    import {onMount} from "svelte";
    import PasswordList from "../../components/dashboard/password-list/PasswordList.svelte";
    import {ComponentState, State} from "../../components/utils/ComponentState";
    import {PasswordAPI} from "../../logic/password-storage-api/PasswordAPI";
    import {PasswordInfoDTO} from "../../logic/password-storage-api/PasswordInfoDTO";

    let passwordList: PasswordInfoDTO[] = [];
    let passwordsFetchStatus: ComponentState = new ComponentState(State.IN_PROGRESS);
    onMount(() => {
        if(!$authStore.isAuthenticated) throw new Error();

        PasswordAPI.getPasswords($authStore.authToken as string)
            .then(passwords => {
                passwordList = passwords;
                passwordsFetchStatus = new ComponentState(State.FINISHED_SUCCESSFULLY);
            })
            .catch(reason => {
                passwordsFetchStatus = new ComponentState(State.ERROR, "Could not fetch passwords, try latter");
            })
    })
</script>

<div class="wrapper">
    <h1>Wellcome to dashboard</h1>

    {#if passwordsFetchStatus.isFinishedSuccessfully()}
        <PasswordList passwordList={passwordList}/>
    {/if}

</div>


<style>
    .wrapper {
        width: 100vw;
        height: 100vh;
        flex-direction: column;
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>