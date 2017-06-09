package me.danwi.ry_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Map<String, Boolean> supportedConversations = new HashMap<>();
        supportedConversations.put(Conversation.ConversationType.PRIVATE.getName(), true);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setContent("content");
        customMessage.setTitle("title123ddd");
        Message message = Message.obtain("1234", Conversation.ConversationType.PRIVATE, customMessage);
        message.setMessageId(123);
        RongIM.getInstance().sendMessage(message, "消息", null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {
                Log.d("TAG", "success");
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                Log.d("TAG", errorCode.getMessage());
            }
        });

        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                return false;
            }
        });
        RongIM.getInstance().startConversationList(this, supportedConversations);
    }
}
