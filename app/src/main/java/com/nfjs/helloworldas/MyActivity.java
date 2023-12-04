import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private BluetoothAdvertiser bluetoothAdvertiser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the BluetoothAdvertiser
        bluetoothAdvertiser = new BluetoothAdvertiser();

        // Start BLE advertising when your app's activity is created
        bluetoothAdvertiser.startAdvertising();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Stop BLE advertising when your app's activity is destroyed (if needed)
        // Call bluetoothAdvertiser.stopAdvertising(); if you want to stop advertising explicitly.
    }
}
