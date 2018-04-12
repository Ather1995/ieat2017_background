package Util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fanmiaomiao on 2018/3/14.
 */

public class MyBanner extends Banner {
    public MyBanner(Context context) {
        super(context, null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void nextPage() {
        handler.removeCallbacks(task);
        if (count > 1) {
            currentItem = currentItem % (count + 1) + 1;
//                Log.i(tag, "curr:" + currentItem + " count:" + count);
            if (currentItem == 1) {
                viewPager.setCurrentItem(currentItem, false);
            } else {
                viewPager.setCurrentItem(currentItem);
            }
        }
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1500);
        handler.postDelayed(task, delayTime);
    }
    public void lastPage() {
        handler.removeCallbacks(task);
        if (count > 1) {
            Log.i(tag, "1curr:" + currentItem + " count:" + count);
            currentItem = ((currentItem==1?0:currentItem) - 1 + count)  % (count + 1) + 1;
            Log.i(tag, "curr:" + currentItem + " count:" + count);
            if (currentItem == 1) {
                viewPager.setCurrentItem(currentItem, false);
            } else {
                viewPager.setCurrentItem(currentItem);
            }
        }
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1500);
        handler.postDelayed(task, delayTime);
    }

}
