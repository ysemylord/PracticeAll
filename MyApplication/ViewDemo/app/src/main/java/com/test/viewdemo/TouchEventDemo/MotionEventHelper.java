package com.test.viewdemo.TouchEventDemo;

import android.view.MotionEvent;

/**
 * Created by xuyabo on 2018/5/13.
 */

public class MotionEventHelper {
    public static String getActionString(MotionEvent motionEvent){
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP";
             case MotionEvent.ACTION_CANCEL:
                 return "ACTION_CANCEL";
        }
        return motionEvent.getAction()+"";
    }
}
