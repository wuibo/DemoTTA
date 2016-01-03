package es.tta.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

/**
 * Created by ainhoa on 15/12/2015.
 */
public class Data {
    RestClient rest;
    public Data(){
        rest = new RestClient("http://u017633.ehu.eus:18080/AlumnoTta/rest/tta");
        rest.setHttpBasicAuth("12345678A","tta");
    }

    public Test getTest(int id) throws IOException, JSONException{
        JSONObject json = rest.getJson(String.format("getTest?id=%d",id));
        String test_wording = json.getString("wording");
        JSONArray jsonArray = json.getJSONArray("choices");
        int length  = jsonArray.length();
        String [] choicesWording = new String[length];
        boolean [] choicesCorrect = new boolean[length];
        String [] choicesAdvise = new String[length];
        String [] choicesAdviseType = new String[length];
        int [] choicesId = new int[length];
        for(int i = 0;i<length;i++){
            JSONObject jsonChoice = jsonArray.getJSONObject(i);
            choicesId[i] = jsonChoice.getInt("id");
            choicesWording[i] = jsonChoice.getString("answer");
            choicesAdvise[i] = jsonChoice.getString("advise");
            choicesCorrect[i] = jsonChoice.getBoolean("correct");
            if(jsonChoice.isNull("resourceType")){
                choicesAdviseType[i] = null;
            }else{
                choicesAdviseType[i] = jsonChoice.getJSONObject("resourceType").getString("mime");;
            }
        }
        Test test = new Test(test_wording,choicesId,choicesWording,choicesCorrect,choicesAdvise,choicesAdviseType);
        return test;
    }

    public Exercise getExercise(int id)throws IOException, JSONException{
        JSONObject json = rest.getJson(String.format("getExercise?id=%d",id));
        Exercise exercise = new Exercise(json.getInt("id"),json.getString("wording"));
        return exercise;
    }

}
