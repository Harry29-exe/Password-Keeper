<script lang="ts">
    import {PasswordInfoDTO} from "../../../logic/password-storage-api/PasswordInfoDTO";
    import Modal from "../../utils/atomic/Modal.svelte";
    import PasswordFetcher from "./PasswordFetcher.svelte";
    import "./Password.css";

    export let passwordInfo: PasswordInfoDTO;
    export let isOpen;


</script>

<Modal bind:isOpen style="width: 80vw">
    <div class="v-stack text-2xl w-4/5">
        <div style="width: 100%; height: 50px;"></div>


        <div class="spacer-h my-4"/>
        <div class="h-stack">
            <div class="label">Password name:</div>
            <div class="value">{passwordInfo.passwordName}</div>
        </div>

        <div class="spacer-h my-4"/>
        <div class="h-stack">
            <div class="label">Password url:</div>
            <div class="value">{passwordInfo.passwordUrl}</div>
        </div>

        <div class="spacer-h my-4"/>


        <PasswordFetcher passwordInfo={passwordInfo}/>

    </div>
</Modal>

