package es.tta.demo;

import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ainhoa on 15/12/2015.
 */
public class Data {
    RestClient rest;
    public Data(String user, String passwd){
        rest = new RestClient("http://u017633.ehu.eus:18080/AlumnoTta/rest/tta");
        rest.setHttpBasicAuth(user, passwd);
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

    public UserStatus getStatus(String dni,String pss) throws IOException, JSONException{
        JSONObject json = rest.getJson(String.format("getStatus?dni=%s",dni));
        UserStatus user = new UserStatus(json.getInt("id"),json.getString("user"),json.getInt("lessonNumber"),
                json.getString("lessonTitle"),json.getInt("nextTest"),json.getInt("nextExercise"),dni,pss);
        return user;
    }

    public int postTest(int user, int choice)throws IOException, JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",user);
        jsonObject.put("choiceId",choice);
        return rest.postJson(jsonObject,"postChoice");
    }

    public int postExercise(Uri uri, int user, int exercise,String name)throws IOException{
        InputStream is = new FileInputStream(uri.getPath());
        String path = "postExercise?user="+user+"&id="+exercise;
        return rest.postFile(path,is,name);
    }

}
