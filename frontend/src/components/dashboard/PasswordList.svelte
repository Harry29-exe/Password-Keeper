<script lang="ts">
    import {PasswordInfoDTO} from "../../logic/password-storage-api/PasswordInfoDTO";
    import HStack from "../utils/atomic/HStack.svelte";
    import VStack from "../utils/atomic/VStack.svelte";
    import Spacer from "../utils/atomic/Spacer.svelte";

    export let passwordList: PasswordInfoDTO[] = [];
</script>

<VStack style="width: 60%; margin-right: auto; margin-left: auto">
    <HStack style="width: 100%; height: auto; font-size: 1.5rem; padding: 5px; box-sizing: border-box">
        <div style="width: 60%">Password name</div>
        <div style="width: 40%">Password url</div>

    </HStack>
    {#each passwordList as passwordInfo}
        <Spacer/>

        <HStack style="width: 100%; height: auto; font-size: 1.5rem; padding: 5px; box-sizing: border-box">
            <div style="width: 60%">{passwordInfo.passwordName}</div>
            <div style="width: 40%">{passwordInfo.passwordUrl}</div>

        </HStack>

    {/each}
    <Spacer/>
</VStack>