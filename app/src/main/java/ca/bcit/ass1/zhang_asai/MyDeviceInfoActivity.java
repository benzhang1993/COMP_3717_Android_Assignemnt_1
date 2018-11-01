package ca.bcit.ass1.zhang_asai;

import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyDeviceInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_device_info);

        // Set each field datum
        TextView manufacturer = findViewById(R.id.manufacturer);
        manufacturer.setText("Manufacturer:" + Build.MANUFACTURER);

        TextView model = findViewById(R.id.model);
        model.setText("Model:" + Build.MODEL);

        TextView version = findViewById(R.id.version);
        version.setText("Version:" + Build.VERSION.SDK_INT);

        TextView versionRelease = findViewById(R.id.versionRelease);
        versionRelease.setText("Version Release:" + Build.VERSION.RELEASE);

        TextView serialNumber = findViewById(R.id.serialNumber);
        serialNumber.setText("Serial number:"
            + Settings.Secure.getString(
                    getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
    }


}
