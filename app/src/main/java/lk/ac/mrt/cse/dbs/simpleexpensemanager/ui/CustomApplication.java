package lk.ac.mrt.cse.dbs.simpleexpensemanager.ui;

import android.app.Application;
import android.content.Context;

/**
 * Created by Warunika on 12/5/2015.
 */
public class CustomApplication extends Application {
    private static Context context;
    public void onCreate(){
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getCustomAppContext(){
        return context;
    }
}
