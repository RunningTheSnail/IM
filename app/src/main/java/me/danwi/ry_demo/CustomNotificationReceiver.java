package me.danwi.ry_demo;

import android.content.Context;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/6/8
 * Time: 上午1:36
 */
public class CustomNotificationReceiver extends PushMessageReceiver{

    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        return false;
    }
}
