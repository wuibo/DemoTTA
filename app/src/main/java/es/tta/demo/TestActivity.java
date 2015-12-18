package es.tta.demo;

import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private int correct;
    private String advise;
    private short adviseType;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        /*Fill the question*/
        Data data = new Data();
        Test test = data.getTest();
        TextView textWording = (TextView) findViewById(R.id.test_wording);
        textWording.setText(test.getWording());
        RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);
        int i = 0;
        for(Test.Choice choice : test.getChoices()){
            RadioButton radio = new RadioButton(this);
            radio.setText(choice.getWording());
            radio.setOnClickListener(this);
            group.addView(radio);
            if(choice.isCorrect()){
                correct = i;
            }
            i++;
        }

        advise=test.getAdvice();
        adviseType = test.getAdviseType();
        layout = (LinearLayout) findViewById(R.id.test_layout);
    }

    @Override
    public void onClick(View u){
        findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
    }

    public void send (View view){
        RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);
        int selectedID = group.getCheckedRadioButtonId();
        View radioButton = group.findViewById(selectedID);
        int selected = group.indexOfChild(radioButton);

        int choices = group.getChildCount();
        for (int i=0; i < choices; i++){
            group.getChildAt(i).setEnabled(false);
        }
        View button = findViewById(R.id.button_send_test);
        ((ViewGroup) button.getParent()).removeView(button);

        group.getChildAt(correct).setBackgroundColor(Color.GREEN);
        if(selected != correct){
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(), "¡Has fallado!", Toast.LENGTH_SHORT).show();
            if(advise != null && !advise.isEmpty()){
                findViewById(R.id.button_view_advice).setVisibility(View.VISIBLE);
            }
        } else
            Toast.makeText(getApplicationContext(),"¡Correcto!",Toast.LENGTH_SHORT).show();
    }

    public void help(View view) throws IOException {
        view.setEnabled(false);
        switch(adviseType){
            case Test.ADVISE_AUDIO:
                showAudio();
                break;
            case Test.ADVISE_HTML:
                showHtml();
                break;
            case Test.ADVISE_VIDEO:
                showVideo();
                break;
        }
    }

    private void showHtml(){
        if (advise.substring(0, 10).contains("://")) {
            Uri uri = Uri.parse(advise);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            WebView web = new WebView(this);
            web.loadData(advise, "text/html", null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            layout.addView(web);
        }
    }

    private void showVideo(){
        VideoView video = new VideoView(this);
        video.setVideoURI(Uri.parse(advise));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        video.setLayoutParams(params);

        MediaController controller = new MediaController(this){
            @Override
            public void hide(){}

            @Override
            public boolean dispatchKeyEvent(KeyEvent event){
                if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                    finish();
                return super.dispatchKeyEvent(event);
            }
        };
        controller.setAnchorView(video);
        video.setMediaController(controller);
        layout.addView(video);
        video.start();
    }

    private void showAudio() throws IOException {
        View view = new View(this);
        AudioPlayer audio = new AudioPlayer(view);
        audio.setAudioUri(Uri.parse(advise));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        layout.addView(view);
        audio.start();
    }
}

