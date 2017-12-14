package com.paobuqianjin.pbq.step.view.base;

import android.app.Application;
import android.os.AsyncTask;

/**
 * Created by pbq on 2017/12/14.
 */

public class PaoBuApplication extends Application {
    private final static String TAG = PaoBuApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

    }

    //异步初始化三方SDK，开启后台计步服务
    class InitTask extends AsyncTask<int[],int[],boolean[]> {
        @Override
        protected boolean[] doInBackground(int[]... ints) {
            return new boolean[0];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(int[]... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(boolean[] booleans) {
            super.onCancelled(booleans);
        }

        @Override
        protected void onPostExecute(boolean[] booleans) {
            super.onPostExecute(booleans);
        }
    }

    public interface InitResult {

    }
}
