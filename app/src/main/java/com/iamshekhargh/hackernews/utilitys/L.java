package com.iamshekhargh.hackernews.utilitys;

import android.util.Log;

/**
 * Created by <<-- iamShekharGH -->>
 * on 28 April 2017
 * at 4:06 PM.
 */

public class L {
    private static boolean showLog = true;

    public static void i(String TAG, String message) {
        if (showLog)
            Log.i(TAG, message);
    }

    public static void i(String m) {
        if (showLog)
            Log.i("NoTitle\t:", m);
    }

    public static void i(int i) {
        if (showLog)
            Log.i("NoTotle\t:", "" + i);
    }

    public static void i(String TAG, int i) {
        if (showLog)
            Log.i(TAG, "" + i);
    }


}
