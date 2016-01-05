package es.tta.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MenuActivity extends AppCompatActivity {

    private UserStatus user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        TextView textLogin = (TextView)findViewById(R.id.menu_login);
        user = (UserStatus) intent.getSerializableExtra(MainActivity.EXTRA_USER);
        textLogin.setText("Bienvenido "+user.getUser());
        TextView textLection = (TextView) findViewById(R.id.menu_lection);
        textLection.setText("Lección "+user.getLesson());
    }

    public void test (View view){
        Intent intent = new Intent(this,TestActivity.class);
        intent.putExtra(MainActivity.EXTRA_USER,user);
        startActivity(intent);
    }

    public void exercise (View view){
        Intent intent = new Intent(this,ExerciseActivity.class);
        intent.putExtra(MainActivity.EXTRA_USER,user);
                startActivity(intent);
    }

    public void stadistics (View view){

    }

}
