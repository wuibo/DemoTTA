package es.tta.demo;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_LOGIN = "es.tta.demo.login";
    public final static String EXTRA_PASSWD = "es.tta.demo.passwd";

    private NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Register BroadcastReceiver to track network conection changes
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        this.registerReceiver(receiver,filter);
    }

    public void login (View view){
        Intent intent = new Intent(this,MenuActivity.class);
        EditText editLogin = (EditText) findViewById(R.id.login);
        EditText editPasswd = (EditText) findViewById(R.id.passwd);
        intent.putExtra(EXTRA_LOGIN,editLogin.getText().toString());
        intent.putExtra(EXTRA_PASSWD,editPasswd.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //unregisters BroadcastReceiver when app is destroyed
        if(receiver != null){
            this.unregisterReceiver(receiver);
        }
    }
}
