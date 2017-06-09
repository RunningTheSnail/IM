package me.danwi.ry_demo;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/6/7
 * Time: 下午4:45
 */
@ProviderTag(messageContent = CustomMessage.class)
public class CustomMessageProvider extends IContainerItemProvider.MessageProvider<CustomMessage> {
    private Context context;

    @Override
    public void bindView(View view, int i, CustomMessage customMessage, UIMessage uiMessage) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.tv1.setText(customMessage.getContent());
        viewHolder.tv2.setText(customMessage.getTitle());
    }

    @Override
    public Spannable getContentSummary(CustomMessage customMessage) {
        return new SpannableString("last commit");
    }

    @Override
    public void onItemClick(View view, int i, CustomMessage customMessage, UIMessage uiMessage) {
        Toast.makeText(context, "hhah", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongClick(View view, int i, CustomMessage customMessage, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.knrt_custom_message, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.tv1 = (TextView) view.findViewById(R.id.tv_1);
        viewHolder.tv2 = (TextView) view.findViewById(R.id.tv_2);
        view.setTag(viewHolder);
        return view;
    }

    class ViewHolder {
        TextView tv1;
        TextView tv2;
    }
}


