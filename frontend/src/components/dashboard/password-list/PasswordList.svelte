<script lang="ts">
    import {PasswordInfoDTO} from "../../../logic/password-storage-api/PasswordInfoDTO";
    import HStack from "../../utils/atomic/HStack.svelte";
    import VStack from "../../utils/atomic/VStack.svelte";
    import Spacer from "../../utils/atomic/Spacer.svelte";
    import Password from "./Password.svelte";

    export let passwordList: PasswordInfoDTO[] = [];
</script>

<VStack style="width: 60%; margin-right: auto; margin-left: auto">
    <HStack style="width: 100%; height: auto; font-size: 1.5rem; padding: 5px; box-sizing: border-box">
        <div style="width: 50%">Password name</div>
        <div style="width: 30%">Password url</div>
        <div style="width: 20%"></div>
    </HStack>

    {#each passwordList as passwordInfo}
        <Spacer/>
        <Password passwordInfo={passwordInfo}/>
    {/each}
    <Spacer/>
</VStack>