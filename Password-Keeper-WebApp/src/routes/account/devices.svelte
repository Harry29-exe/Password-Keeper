<script lang="ts">
    import "../../style/component-style/Table.css"
    import {onMount} from "svelte";
    import {ClientDevicesAPI} from "../../logic/client-devices-api/ClientDevicesAPI";
    import {authStore} from "../../stores/AuthStore";
    import {ClientDeviceDTO} from "../../logic/client-devices-api/ClientDeviceDTO";

    let clientDevices: ClientDeviceDTO[] = []
    onMount(() => {
        ClientDevicesAPI.getAllAuthorizedDevices($authStore.authToken as string)
            .then(response => clientDevices = response);
    })
</script>


<div class="table-wrapper">
    <table>
        <thead>
        <tr>
            <th class="w-[120px]">Ip address</th>
            <th>User agent</th>
            <th class="w-[60px]">Is authorized</th>
        </tr>
        </thead>

        <tbody>
        {#each clientDevices as device}
            <tr>
                <td>{device.ipAddress}</td>
                <td>{device.userAgent}</td>
                <td>{device.isAuthorized}</td>
            </tr>
        {/each}
        </tbody>
    </table>
</div>