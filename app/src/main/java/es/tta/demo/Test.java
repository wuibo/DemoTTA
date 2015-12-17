package es.tta.demo;

import android.view.View;

/**
 * Created by ainhoa on 15/12/2015.
 */
public class Test {

    protected String getWording(){
        return "¿Qué asignatura es esta?";
    }

    protected Choice[] getChoices(){
        Choice [] choices = new Choice[4];
        choices[0] = new Choice("Rendimiento",false);
        choices[1] = new Choice("Desplieguie",false);
        choices[2] = new Choice("TTA",true);
        choices[3] = new Choice("Proyectos",false);

        return choices;
    }

    protected String getAdvice(){
        return "ayuda";
    }

    protected class Choice{

        private String wording;
        private boolean correct;

        public Choice(String Swording, boolean Correct){
            wording=Swording;
            correct=Correct;
        }

        protected String getWording(){ return wording;  }

        protected boolean isCorrect(){
            return correct;
        }
    }
}
