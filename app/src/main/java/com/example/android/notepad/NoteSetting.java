package com.example.android.notepad;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

public class NoteSetting extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notelist_setting);

        loadSwitchState();//加载switch控件当前状态

        Switch reversal_switch = (Switch) findViewById(R.id.orderbytime_switch);
        // 添加监听
        reversal_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Log.d(TAG, "switch be open");
                    setisReversal(true);
                    returnToMain();
                }else {
                    Log.d(TAG, "switch be close");
                    setisReversal(false);
                    returnToMain();
                }
            }
        });
    }

    private void returnToMain(){
        Intent intent = new Intent(this, NotesList.class);
        startActivity(intent);
    }

    private void loadSwitchState(){
        //从SharedPreference中读取并加载switch状态
        SharedPreferences settings
                = this.getSharedPreferences("Setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Switch reversal_switch = (Switch) findViewById(R.id.orderbytime_switch);
        boolean reversal_state = settings.getBoolean("order_reversal", false);
        reversal_switch.setChecked(reversal_state);
    }

    private void setisReversal(boolean flag) {
        //根据flag保存当前switch控件的状态到SharedPreference
        SharedPreferences settings
                = this.getSharedPreferences("Setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("order_reversal", flag);
        editor.commit();
    }

}