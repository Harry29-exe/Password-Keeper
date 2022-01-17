<script lang="ts">
    import {PasswordInfoDTO} from "../../../../logic/password-storage-api/PasswordInfoDTO";
    import Modal from "../../../utils/atomic/Modal.svelte";
    import VStack from "../../../utils/atomic/VStack.svelte";
    import Spacer from "../../../utils/atomic/Spacer.svelte";
    import HStack from "../../../utils/atomic/HStack.svelte";
    import PasswordFetcher from "./PasswordFetcher.svelte";
    import "../Password.css";

    export let passwordInfo: PasswordInfoDTO;
    export let isOpen;


</script>

<Modal bind:isOpen style="width: 80vw">
    <VStack style="font-size: 1.5rem; width: 85%">
        <div style="width: 100%; height: 50px;"></div>

        <Spacer style="margin: 10px 0 10px 0"/>
        <HStack>
            <div class="label">Password name: </div>
            <div class="value">{passwordInfo.passwordName}</div>
        </HStack>

        <Spacer style="margin: 10px 0 10px 0"/>
        <HStack>
            <div class="label">Password url: </div>
            <div class="value">{passwordInfo.passwordUrl}</div>
        </HStack>

        <Spacer style="margin: 10px 0 10px 0"/>


        <PasswordFetcher passwordInfo={passwordInfo}/>

        <HStack>

        </HStack>
    </VStack>
</Modal>

