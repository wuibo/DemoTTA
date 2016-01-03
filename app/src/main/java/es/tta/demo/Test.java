package es.tta.demo;

import android.view.View;

/**
 * Created by ainhoa on 15/12/2015.
 */
public class Test {

    static public final String ADVISE_HTML = "text/html";
    static public final String ADVISE_VIDEO = "video/mp4";
    static public final String ADVISE_AUDIO = "audio/mpeg";

    private String wording;
    private Choice[] choices;

    public Test(String Wording,int [] choicesId, String [] choicesWording,boolean [] choicesCorrect, String [] choicesAdvise, String [] choicesAdviseType){
        wording = Wording;
        if(choicesWording.length == choicesCorrect.length){
            choices = new Choice[choicesCorrect.length];
            int i = 0;
            for(String choice : choicesWording){
                choices[i] = new Choice(choicesId[i],choicesWording[i],choicesCorrect[i],choicesAdvise[i],choicesAdviseType[i]);
                i++;
            }
        }
    }

    public String getWording(){
        return wording;
    }

    public Choice[] getChoices(){ return choices; }

    public Choice getChoice(int i){
        return choices[i];
    }


    public class Choice{

        private int id;
        private String wording;
        private boolean correct;
        private String advise;
        private String adviseType;

        public Choice(int id, String wording,boolean correct, String advise, String adviseType){
            this.id = id;
            this.wording = wording;
            this.correct = correct;
            this.advise = advise;
            this.adviseType = adviseType;
        }

        public int getId(){ return id; }

        public String getWording(){ return wording; }

        public boolean isCorrect(){
            return correct;
        }

        public String getAdvise() { return advise; }

        public String getAdviseType() { return adviseType; }
    }
}
