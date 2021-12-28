<script lang="ts">
    import {PasswordInfoDTO} from "../../logic/password-storage-api/PasswordInfoDTO";
    import Modal from "../utils/atomic/Modal.svelte";
    import VStack from "../utils/atomic/VStack.svelte";
    import Spacer from "../utils/atomic/Spacer.svelte";
    import HStack from "../utils/atomic/HStack.svelte";
    import Button from "../utils/atomic/Button.svelte";

    export let passwordInfo: PasswordInfoDTO;
    export let isOpen;

    let passwordShowState: 'not-initialized' | 'passwordInput' | 'initialized' | 'finished';
    let storagePassword = "";
    let fetchedPassword = "";
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
        <HStack>
            <div class="label">Password:</div>
            <div class="value">

                {#if passwordShowState === 'not-initialized'}
                    <Button style="position: absolute; right: 0">Show</Button>
                {:else}
                    <Button style="position: absolute; right: 0">Hide</Button>
                {/if}

            </div>
        </HStack>

        <HStack>

        </HStack>
    </VStack>
</Modal>

<style>
    .wrapper {
        width: 100%;
    }

    .label {
        width: 35%;
    }

    .value {
        width: 65%;
        text-align: right;
        position: relative;
    }

    .overflow-hidden {
        overflow: hidden;
        text-overflow: ellipsis
    }
</style>