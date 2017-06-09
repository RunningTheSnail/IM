package me.danwi.ry_demo;

import android.content.Context;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/6/6
 * Time: 下午6:31
 */
public abstract class EasyIM {
    private static final String TAG = "EasyIM";

    //融云sdk初始化
    public void init(Context context, String token, String iMProcessName, final CallBack callBack) {

        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (context.getApplicationInfo().packageName.equals(getCurProcessName(context.getApplicationContext())) ||
                iMProcessName.equals(getCurProcessName(context.getApplicationContext()))) {
            RongIM.init(context.getApplicationContext());
            config();
        }

        if (context.getApplicationInfo().packageName.equals(getCurProcessName(context.getApplicationContext()))) {
            //设置消息监听器，所有接收到的消息、通知、状态都经由此处设置的监听器处理。包括私聊消息、讨论组消息、群组消息、聊天室消息以及各种状态。
            RongIM.setOnReceiveMessageListener(new EasyReceiveMessageListener());
            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d(TAG, "--onTokenIncorrect");
                    callBack.onTokenIncorrect();
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d(TAG, "--onSuccess---" + userid);
                    callBack.onSuccess(userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d(TAG, "--onError" + errorCode);
                    callBack.onError(errorCode);
                }
            });
        }
    }

    //配置自定义消息
    //消息模板
    public abstract void config();

    public static void startPrivateChat(Context context, String targetId, String title) {
        RongIM.getInstance().startPrivateChat(context, "9527", "标题");
    }

}

interface CallBack {
    void onTokenIncorrect();

    void onSuccess(String userId);

    void onError(RongIMClient.ErrorCode errorCode);
}
