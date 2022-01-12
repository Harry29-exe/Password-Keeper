<script lang="ts">
    import type {PasswordInfoDTO} from "../../../logic/password-storage-api/PasswordInfoDTO.js";
    import Button from "../../utils/atomic/Button.svelte";
    import PasswordModal from "./modal/PasswordModal.svelte";

    export let passwordInfo: PasswordInfoDTO;
    let isOpen = false;
</script>

<div class="flex items-center md:justify-between flex-col md:flex-row  w-full text-2xl p-2">
    <div class="overflow-hidden w-4/12 row-part">
        {passwordInfo.passwordName}
    </div>
    <div class="overflow-hidden w-6/12 row-part">
        {passwordInfo.passwordUrl}
    </div>
    <div class="center w-2/12 row-part">
        <Button size="md" style="width: 100px" on:click={() => isOpen = true}>
            view
        </Button>
    </div>

</div>

<PasswordModal passwordInfo={passwordInfo} bind:isOpen/>


<style>
    .row-part {
        @apply min-w-full text-center  py-3;
        @apply md:min-w-0 md:text-left md:py-0;
    }
</style>