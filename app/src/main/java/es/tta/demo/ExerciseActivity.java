package es.tta.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();
        TextView textExercise = (TextView) findViewById(R.id.exercise_wording);
        textExercise.setText(intent.getStringExtra(MenuActivity.EXTRA_EXERCISE));
    }

    public void sendFile(View view){
        Toast.makeText(getApplicationContext(),"Enviar fichero",Toast.LENGTH_SHORT).show();
    }

    public void sendFoto(View view){
        Toast.makeText(getApplicationContext(),"Sacar foto",Toast.LENGTH_SHORT).show();
    }

    public void sendAudio(View view){
        Toast.makeText(getApplicationContext(),"Grabar audio",Toast.LENGTH_SHORT).show();
    }

    public void sendVideo(View view){
        Toast.makeText(getApplicationContext(),"Grabar video",Toast.LENGTH_SHORT).show();
    }

}
