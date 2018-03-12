package org.molaei.progressbarexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.molaei.progressbarwithindicator.ProgressBarWithIndicator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBarWithIndicator progressBarWithIndicator = findViewById(R.id.progressBarWithIndicator);
        progressBarWithIndicator.setProgress(50);
        progressBarWithIndicator.setMax(150);
    }
}
