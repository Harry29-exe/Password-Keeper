<script lang="ts">
    import {PasswordInfoDTO} from "../../../../logic/password-storage-api/PasswordInfoDTO";
    import Button from "../../../utils/atomic/Button.svelte";
    import "../Password.css";
    import TextInput from "../../../utils/atomic/TextInput.svelte";
    import CircularProgress from "../../../utils/atomic/CircularProgress.svelte";
    import {PasswordAPI} from "../../../../logic/password-storage-api/PasswordAPI";
    import {authStore} from "../../../../stores/AuthStore";
    import {fly} from "svelte/transition";


    export let passwordInfo: PasswordInfoDTO;
    let passwordShowState: 'not-initialized' | 'passwordInput' | 'initialized' | 'finished' = 'not-initialized';
    let storagePassword = "";
    let fetchedPassword = "";

    const onPasswordFetch = () => {
        passwordShowState = 'initialized';
        PasswordAPI.decodeAndGetPassword(
            storagePassword,
            passwordInfo.passwordName,
            $authStore.authToken as string
        ).then(password => {
            console.log('success');
            fetchedPassword = password;
            passwordShowState = 'finished';
        }).catch(reason => {
            console.log('error: ' + reason);
            passwordShowState = 'passwordInput';
        });
    }
</script>

<div class="v-stack w-full">
    <div class="h-stack">
        <div class="label">Password:</div>

        {#if passwordShowState === 'not-initialized'}
            <Button style="position: absolute; right: 0; width: 200px"
                    on:click={() => passwordShowState = 'passwordInput'}>
                Show
            </Button>
        {:else}
            <Button class="absolute right-0 w-[200px]"
                    on:click={() => {
                    passwordShowState = 'not-initialized';
                    storagePassword = "";
                }}>
                Hide
            </Button>
        {/if}

    </div>

    {#if passwordShowState === 'passwordInput'}
        <div class="v-stack w-full" transition:fly={{duration: 250}}>
            <span style="margin-top: 20px">Enter storage password:</span>
            <div class="h-stack">
                <TextInput bind:value={storagePassword} type="password" placeholder="Storage password"
                           style="margin-right: 10px; font-size: 1.2rem"/>
                <Button style="min-width: 200px; height: 2rem" on:click={onPasswordFetch}>Get password</Button>
            </div>
        </div>
    {:else if passwordShowState === 'initialized'}
        <CircularProgress/>
    {:else if passwordShowState === 'finished'}
        <h4>{fetchedPassword}</h4>
    {/if}


</div>