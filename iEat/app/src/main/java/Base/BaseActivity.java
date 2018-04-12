package Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by fanmiaomiao on 2018/3/7.
 */

public class BaseActivity extends AppCompatActivity {
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Log.d("ActivityName", getClass().getSimpleName());
        AppCompatActivityCollector.addActivity(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        AppCompatActivityCollector.removeActivity(this);
    }
}