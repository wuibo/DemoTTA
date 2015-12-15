package es.tta.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_LOGIN = "es.tta.demo.login";
    public final static String EXTRA_PASSWD = "es.tta.demo.passwd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login (View view){
        Intent intent = new Intent(this,MenuActivity.class);
        EditText editLogin = (EditText) findViewById(R.id.login);
        EditText editPasswd = (EditText) findViewById(R.id.passwd);
        intent.putExtra(EXTRA_LOGIN,editLogin.getText().toString());
        intent.putExtra(EXTRA_PASSWD,editPasswd.getText().toString());
        startActivity(intent);
    }
}
