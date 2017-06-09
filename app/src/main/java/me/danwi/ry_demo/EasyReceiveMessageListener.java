package me.danwi.ry_demo;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/6/6
 * Time: 下午11:29
 */
public class EasyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {
    @Override
    public boolean onReceived(Message message, int i) {
        return false;
    }
}
