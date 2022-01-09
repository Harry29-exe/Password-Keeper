<script lang="ts">
    import {PasswordInfoDTO} from "../../../logic/password-storage-api/PasswordInfoDTO";
    import HStack from "../../utils/atomic/HStack.svelte";
    import VStack from "../../utils/atomic/VStack.svelte";
    import Spacer from "../../utils/atomic/Spacer.svelte";
    import Password from "./Password.svelte";
    import Button from "../../utils/atomic/Button.svelte";
    import Center from "../../utils/atomic/Center.svelte";
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


<VStack style="width: 60%; margin-right: auto; margin-left: auto">

    <!--Header-->
    <HStack style="width: 100%; height: auto; font-size: 1.5rem; padding: 5px; box-sizing: border-box">
        <div style="width: 50%">Password name</div>
        <div style="width: 30%">Password url</div>
        <Center style="width: 20%">
            <Button on:click={fetchPasswords} size="md" style="width: 100px">
                refresh
            </Button>
        </Center>
    </HStack>

    {#each passwordList as passwordInfo}
        <Spacer/>
        <Password passwordInfo={passwordInfo}/>
    {/each}
    <Spacer/>


</VStack>