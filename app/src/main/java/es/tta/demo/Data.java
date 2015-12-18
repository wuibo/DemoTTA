package es.tta.demo;

import java.util.Random;

/**
 * Created by ainhoa on 15/12/2015.
 */
public class Data {
    Test [] test;
    public Data(){
        test = new Test [3];
        /*para el primer test*/
        String [] choicesWording = new String[4];
        boolean [] choicesCorrect = new boolean[4];
        choicesWording[0] = "Rendimiento";
        choicesCorrect[0] = false;
        choicesWording[1] = "TTA";
        choicesCorrect[1] = true;
        choicesWording[2] = "Despliegue";
        choicesCorrect[2] = false;
        choicesWording[3] = "Proyectos";
        choicesCorrect[3] = false;
        String advise = "<html><body>La asignatura es <b>TTA</b></body></html>";
        test[0] = new Test("¿Qué asignatura es esta?",choicesWording,choicesCorrect,advise,Test.ADVISE_HTML);
        /*para el segundo test*/
        choicesWording = new String[3];
        choicesCorrect = new boolean[3];
        choicesWording[0] = "Sube escaleras";
        choicesCorrect[0] = true;
        choicesWording[1] = "Baja escaleras";
        choicesCorrect[1] = false;
        choicesWording[2] = "Nada";
        choicesCorrect[2] = false;
        advise = "http://51.254.221.215/uploads/subir.mp4";
        test[1] = new Test("¿Qué hace el niño?",choicesWording,choicesCorrect,advise,Test.ADVISE_VIDEO);
        /*para el tercer test*/
        choicesWording = new String[2];
        choicesCorrect = new boolean[2];
        choicesWording[0] = "NO";
        choicesCorrect[0] = true;
        choicesWording[1] = "SI";
        choicesCorrect[1] = false;
        advise = "http://51.254.221.215/uploads/pr.ogg";
        test[2] = new Test("¿Audio?",choicesWording,choicesCorrect,advise,Test.ADVISE_VIDEO);
    }
    protected Test getTest(){
        Random rand = new Random();
        int i = rand.nextInt(3);
        return test[i];
    }
}
