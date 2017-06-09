package me.danwi.ry_demo;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/6/6
 * Time: 下午5:15
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new EasyIM() {
            @Override
            public void config() {

            }
        }.init(this, "token", "iMProcess", new CallBack() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String userId) {
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(userId, userId + "easyIm",
                        "123".equals(userId) ?
                                Uri.parse("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=7fcabe8a252eb938f36d7df2e56285fe/a686c9177f3e6709e210f89431c79f3df8dc554c.jpg") : Uri.parse("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3051838861,355376138&fm=117&gp=0.jpg")));
                RongIM.getInstance().setMessageAttachedUserInfo(true);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });

        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                Log.d("TAG", "收到消息");
                return false;
            }
        });

        RongIM.getInstance().addUnReadMessageCountChangedObserver(new IUnReadMessageObserver() {
            @Override
            public void onCountChanged(int i) {
                Log.d("TAG", "Private message");
            }
        }, Conversation.ConversationType.PRIVATE);

        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                return null;
            }
        }, true);

//        RongIM.getInstance().setCurrentUserInfo(new UserInfo("12345", "测试22", Uri.parse("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=7fcabe8a252eb938f36d7df2e56285fe/a686c9177f3e6709e210f89431c79f3df8dc554c.jpg")));

    }
}
