package Util;

import android.content.Context;
import android.util.Log;

/**
 * Created by fanmiaomiao on 2018/3/7.
 */

public class LogUtil {
    public static void Log(Context context,String tag,String msg){
        Log.d(context.getClass().getSimpleName()+":"+tag,msg);
    }
}
