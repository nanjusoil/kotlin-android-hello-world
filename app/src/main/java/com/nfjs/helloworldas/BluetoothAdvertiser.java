import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.os.ParcelUuid;
import java.util.UUID;

public class BluetoothAdvertiser {

    public void startAdvertising() {
        // Get the BluetoothAdapter
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Check if BLE Advertising is supported
        if (!bluetoothAdapter.isMultipleAdvertisementSupported()) {
            System.out.println("BLE Advertising not supported on this device");
            return;
        }

        // Get the BluetoothLeAdvertiser
        BluetoothLeAdvertiser advertiser = bluetoothAdapter.getBluetoothLeAdvertiser();

        // Create Advertise Settings
        AdvertiseSettings advertiseSettings = new AdvertiseSettings.Builder()
            .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_POWER)
            .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM)
            .setConnectable(false)
            .build();

        // Define the byte sequence for your advertisement
        byte[] advertisementData = new byte[]{
            0x23, 0x43, 0x4d, 0x51, 0x46, 0x11, 0x32, 0x42, 0x15, 0x2a, 0x35, 0xab, 0xc6, 0xc1, 0x3a, 0xf3,
            0x1b, 0x32, 0x5f, 0x17, 0x12, 0x91, 0x03
        };

        // Create AdvertiseData with service data or manufacturer data
        AdvertiseData advertiseData = new AdvertiseData.Builder()
            .addServiceData(new ParcelUuid(UUID.fromString("your-service-uuid")), advertisementData)
            //.addManufacturerData(yourManufacturerId, advertisementData) // If using manufacturer-specific data
            .build();

        // Start advertising
        advertiser.startAdvertising(advertiseSettings, advertiseData, new AdvertiseCallback() {
            @Override
            public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                super.onStartSuccess(settingsInEffect);
                System.out.println("Advertising started successfully");
            }

            @Override
            public void onStartFailure(int errorCode) {
                super.onStartFailure(errorCode);
                System.out.println("Advertising failed to start with error code " + errorCode);
            }
        });
    }
}
