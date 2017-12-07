package com.example.xuyabo.fourcomponent.activityLaunchModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.xuyabo.fourcomponent.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void toStandardFirstActivity(View view) {
        Intent intent=new Intent(this,StandardFirstActivity.class);
        startActivity(intent);
    }

    public void toStandarSecondActivity(View view) {
        Intent intent=new Intent(this,StandardSecondActivity.class);
        startActivity(intent);
    }

    public void toNewTackActivity(View view) {
        Intent intent=new Intent(this,NewTaskActivity.class);
        startActivity(intent);
    }

    public void toSingleInstanceActivity(View view) {
        Intent intent=new Intent(this,SingleInstanceActivity.class);
        startActivity(intent);
    }
}
