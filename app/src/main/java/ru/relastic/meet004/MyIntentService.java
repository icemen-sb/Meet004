package ru.relastic.meet004;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;


public class MyIntentService extends IntentService {
    private static StateManager sm = StateManager.getInstance();
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            StateManager.States newValue = sm.nextState();

            Intent broadcastIntent = new Intent("Meet004");
            broadcastIntent.putExtra("DATA", newValue.getValue());
            broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(broadcastIntent);
        }
    }
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, MyIntentService.class);
        return intent;
    }
}
