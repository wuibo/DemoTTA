package es.tta.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        TextView textLogin = (TextView)findViewById(R.id.menu_login);
        textLogin.setText(intent.getStringExtra(MainActivity.EXTRA_LOGIN));
        TextView textLection = (TextView) findViewById(R.id.menu_lection);
        textLection.setText("Lección 1");   //como meter en código string
    }

    public void test (View view){

    }

    public void exercise (View view){

    }

    public void stadistics (View view){

    }

}
