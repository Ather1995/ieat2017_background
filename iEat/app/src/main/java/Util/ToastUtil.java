package Util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by fanmiaomiao on 2018/3/7.
 */

public class ToastUtil {
    public static void show(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, int info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }
}
