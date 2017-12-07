package com.example.xuyabo.fourcomponent.activityLaunchModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.xuyabo.fourcomponent.R;

public class SingleInstanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);

    }
    public void toStandardFirstActivity(View view) {
        Intent intent=new Intent(this,StandardFirstActivity.class);
        startActivity(intent);
    }
}
