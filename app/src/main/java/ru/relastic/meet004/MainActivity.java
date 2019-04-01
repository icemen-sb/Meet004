package ru.relastic.meet004;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_VALUE = "current_text_value";
    private BroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;
    private TextView mTextView;
    private Button mButton;
    private static String curText=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initViews();
        initListeners();
        init();
    }
    private void initViews() {
        mTextView = findViewById(R.id.textView);
        mButton = findViewById(R.id.button);
    }
    private void initListeners() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toChangeStatus();
            }
        });
    }
    private void init( ) {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                hasStatusChange(intent.getStringExtra("DATA"));
            }
        };
        mIntentFilter = new IntentFilter("Meet004");
    }

    private void toChangeStatus(){startService(MyIntentService.newIntent(this));}

    private void hasStatusChange(String newStatus){mTextView.setText(newStatus);}


    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }
    @Override
    protected void onResume() {
        registerReceiver(mReceiver, mIntentFilter, null, null);
        super.onResume();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_VALUE,mTextView.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mTextView.setText(savedInstanceState.getCharSequence(STATE_VALUE).toString());
        super.onRestoreInstanceState(savedInstanceState);
    }
}
