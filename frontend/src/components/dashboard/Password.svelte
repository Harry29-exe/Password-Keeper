<script lang="ts">
    import HStack from "../utils/atomic/HStack.svelte";
    import type {PasswordInfoDTO} from "../../logic/password-storage-api/PasswordInfoDTO.js";
    import Button from "../utils/atomic/Button.svelte";
    import Center from "../utils/atomic/Center.svelte";
    import PasswordModal from "./PasswordModal.svelte";

    export let passwordInfo: PasswordInfoDTO;
    let isOpen = false;
</script>

<HStack style="width: 100%; height: auto; font-size: 1.5rem; padding: 5px; box-sizing: border-box">
    <div style="width: 50%; max-width: 50%;" class="overflow-hidden">
        {passwordInfo.passwordName}
    </div>
    <div style="width: 30%; max-width: 30%;" class="overflow-hidden">
        {passwordInfo.passwordUrl}
    </div>
    <Center style="width: 20%; height: 100%">
        <Button size="md" style="width: 100px" on:click={() => isOpen = true}>
            view
        </Button>
    </Center>

</HStack>

<PasswordModal passwordInfo={passwordInfo} bind:isOpen/>