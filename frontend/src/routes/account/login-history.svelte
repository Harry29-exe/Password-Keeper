<script lang="ts">
    import {LoginEventDTO} from "../../logic/login-history-api/LoginEventDTO";
    import {onMount} from "svelte";
    import {LoginHistoryAPI} from "../../logic/login-history-api/LoginHistoryAPI";
    import {authStore} from "../../stores/AuthStore";

    let allEvents: LoginEventDTO[];

    onMount(() => {
        LoginHistoryAPI
            .getAllAuthorizedDevices(1, 1000, $authStore.authToken as string)
            .then(history => allEvents = history.loginEvents);
    })
</script>


<div class="table-wrapper">
    <table>
        <thead>
        <tr>
            <th class="w-[120px]">Ip address</th>
            <th>User agent</th>
            <th>Date</th>
        </tr>
        </thead>

        {#if !!allEvents}
            <tbody>
            {#each allEvents as event}
                <tr>
                    <td>{event.ipAddress}</td>
                    <td>{event.userAgent}</td>
                    <td>{event.loginDate}</td>
                </tr>
            {/each}
            </tbody>
        {/if}
    </table>
</div>


<style>
    .table-wrapper {
        @apply w-full mx-auto mt-10 sm:pt-1 md:pt-1 rounded-md md:shadow-lg bg-primary-800 overflow-hidden;
        @apply sm:w-11/12 md:w-4/5 xl:w-3/4;
    }

    table {
        width: 100%;
        table-layout: fixed;
        border-collapse: collapse;
        overflow-wrap: anywhere;
    }

    tbody tr td {
        @apply max-w-[40%] py-4 px-1 text-center;
    }

    tbody tr:nth-child(odd) {
        @apply bg-background-700;
    }

    tbody tr:nth-child(even) {
        @apply bg-background-800;
    }

</style>