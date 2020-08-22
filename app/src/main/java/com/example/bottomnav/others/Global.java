package com.example.bottomnav.others;

import android.app.Application;
import android.content.Context;

public class Global extends Application {

    private String menu = null;
    private int selectedPosition = 0;
    private int intervalTime = 0;
    private int numberTimes = 0;
    private static Context context;
    private String conveyMenuName;
    private int deleteCount = 1;
    private String play;

    private String global_date = "";
    private String global_meal;

    @Override
    public void onCreate() {
        super.onCreate();
        Global.context = getApplicationContext();
    }

    public String getMenu() {
        return menu;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public int getIntervalTime() {return  intervalTime;}

    public int getNumberTimes() {return numberTimes;}

    public String getConveyMenuName() {
        return conveyMenuName;
    }

    public int getDeleteCount() {return deleteCount;}

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public void setMenu(String str) {
        menu = str;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public void setIntervalTime(int time) {intervalTime = time;}

    public void setNumberTimes(int times) {numberTimes = times;}

    public void setConveyMenuName(String menuName) {
        conveyMenuName = menuName;
    }

    public void addNumberTimes() {numberTimes++;}

    public void addDeleteCount() {deleteCount++;}

    public void removeNumberTimes() {numberTimes--;}

    public static Context getAppContext() {return context;}














//

    public String getTestString() {
        return global_date;
    }

    public void setTestString(String str) {
        global_date = str;
    }

    public String getMealString() {
        return global_meal;
    }

    public void setMealString(String str) {
        global_meal = str;
    }

}
