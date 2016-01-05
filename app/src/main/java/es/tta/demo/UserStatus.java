package es.tta.demo;

import java.io.Serializable;

/**
 * Created by ainhoa on 04/01/2016.
 */
public class UserStatus implements Serializable {
    final private int id;
    final private String user;
    final private int lesson;
    final private String lesson_title;
    final private int nexTest;
    final private int nextExercise;
    final private String user_dni;
    final private String user_pss;

    public UserStatus(int id, String user, int lesson, String lesson_title, int nexTest,
                      int nextExercise,String user_dni,String user_pss){
        this.id = id;
        this.user = user;
        this.lesson = lesson;
        this.lesson_title = lesson_title;
        this.nexTest = nexTest;
        this.nextExercise = nextExercise;
        this.user_dni = user_dni;
        this.user_pss = user_pss;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public int getLesson() {
        return lesson;
    }

    public String getLesson_title() {
        return lesson_title;
    }

    public int getNexTest() {
        return nexTest;
    }

    public int getNextExercise() {
        return nextExercise;
    }

    public String getUser_dni() {
        return user_dni;
    }

    public String getUser_pss() {
        return user_pss;
    }
}
