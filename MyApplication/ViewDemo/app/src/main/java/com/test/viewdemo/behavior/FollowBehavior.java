package com.test.viewdemo.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created  on 2017/9/5.
 *
 * @author xyb
 */

public class FollowBehavior extends CoordinatorLayout.Behavior<TextView> {
    private static final String TAG="FollowBehavior";
    public FollowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 返回true表示child
     * 依赖此时的dependency
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        boolean dependent = dependency instanceof Button;
        Log.i(TAG, "layoutDependsOn"+dependency);
        return dependent;
    }

    /**
     * 当dependency状态发生改变时调用
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        float desX = dependency.getX();
        float desY = dependency.getY() + 100;
        child.setX(desX);
        child.setY(desY);
        return true;
    }
}
