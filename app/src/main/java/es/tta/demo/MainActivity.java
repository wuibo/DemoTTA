package es.tta.demo;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_USER = "es.tta.demo.user";

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

    public void login (final View view){
        final Intent intent = new Intent(this,MenuActivity.class);
        EditText editLogin = (EditText) findViewById(R.id.login);
        EditText editPasswd = (EditText) findViewById(R.id.passwd);
        /*verificar que el DNI sigue el formato indicado*/
        final String pss = editPasswd.getText().toString();
        final String dni = editLogin.getText().toString();
        if(dni.matches("[0-9]{8}[A-Z]")){
            /*Obtener los datos de usuario*/
            final Data data = new Data(dni,pss);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserStatus user=null;
                    try{
                        user = data.getStatus(dni,pss);
                    }catch(Exception e){
                        Log.e("demo", e.getMessage(), e);
                    }finally {
                        if(user!=null){
                            intent.putExtra(EXTRA_USER,user);
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(intent);
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(),R.string.server_error,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }).start();
        }else{
            Toast.makeText(getApplicationContext(),R.string.bad_dni,Toast.LENGTH_LONG).show();
        }

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
