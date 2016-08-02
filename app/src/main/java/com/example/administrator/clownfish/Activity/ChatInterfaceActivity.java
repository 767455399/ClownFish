package com.example.administrator.clownfish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.modle.ChatMsgEntity;
import com.example.administrator.clownfish.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChatInterfaceActivity extends BaseActivity {
    private RecyclerView chatInterfaceRecyclerView;
    private ChatInterfaceAdapter chatInterfaceAdapter;
    private List<ChatMsgEntity> chatMsgEntityList = new ArrayList<>();
    private String[] msgArray = new String[]{"有大吗", "有！你呢？", "我也有", "那上吧",
            "打啊！你放大啊！", "你TM咋不放大呢？留大抢人头啊？CAO！你个菜B", "2B不解释", "尼滚...",
            "今晚去网吧包夜吧？", "有毛片吗？", "种子一大堆啊~还怕没片？", "OK,搞起！！"};

    private String[] dataArray = new String[]{"2012-09-22 18:00:02",
            "2012-09-22 18:10:22", "2012-09-22 18:11:24",
            "2012-09-22 18:20:23", "2012-09-22 18:30:31",
            "2012-09-22 18:35:37", "2012-09-22 18:40:13",
            "2012-09-22 18:50:26", "2012-09-22 18:52:57",
            "2012-09-22 18:55:11", "2012-09-22 18:56:45",
            "2012-09-22 18:57:33",};
    private String isMe = "http://www.yingkounews.com/wenti/ttkx/tyzx/lq/201409/W020140915540308090003.jpg";
    private String isNotMe = "http://photocdn.sohu.com/20160117/Img434782859.jpg";
  //  http://gb.cri.cn/mmsource/images/2006/08/11/ie060811016.jpg
    @Override
    protected void initView() {
        setContentView(R.layout.activity_chat_interface);
        chatInterfaceRecyclerView = (RecyclerView) findViewById(R.id.chatInterfaceRecyclerView);
        chatInterfaceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatInterfaceAdapter = new ChatInterfaceAdapter();
        chatInterfaceRecyclerView.setAdapter(chatInterfaceAdapter);
    }

    @Override
    protected void loadData() {
        for (int i = 0; i < 12; i++) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setTime(dataArray[i]);
            if (i % 2 == 0) {
                entity.setName("肖B");
                entity.setIsComMeg(true);// 收到的消息
            } else {
                entity.setName("必败");
                entity.setIsComMeg(false);// 自己发送的消息
            }
            entity.setMessage(msgArray[i]);
            chatMsgEntityList.add(entity);
        }

    }

    class ChatInterfaceAdapter extends RecyclerView.Adapter {
        public static final int ITEM_IS_ME = 0;
        public static final int ITEM_IS_NOT_ME = 1;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            switch (viewType) {
                case ITEM_IS_ME:
                    view = LayoutInflater.from(ChatInterfaceActivity.this).inflate(R.layout.item_chat_interface_left, parent, false);
                    return new ViewHolder(view);
                case ITEM_IS_NOT_ME:
                    view = LayoutInflater.from(ChatInterfaceActivity.this).inflate(R.layout.item_chat_interface_right, parent, false);
                    return new ViewHolder(view);

            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ViewHolder) holder).chatInterfaceTextView.setText(chatMsgEntityList.get(position).getMessage());
            ((ViewHolder) holder).timeTextView.setText(chatMsgEntityList.get(position).getTime());
            if(chatMsgEntityList.get(position).isIsComMeg()){
                Picasso.with(ChatInterfaceActivity.this).load(isMe).error(R.mipmap.ic_launcher).into(((ViewHolder)holder).iconImageView);
            }else{
                Picasso.with(ChatInterfaceActivity.this).load(isNotMe).error(R.mipmap.ic_launcher).into(((ViewHolder)holder).iconImageView);
            }

        }

        @Override
        public int getItemCount() {
            return chatMsgEntityList.size();
        }


        @Override
        public int getItemViewType(int position) {
            Boolean isMe = chatMsgEntityList.get(position).isIsComMeg();
            if (isMe) {
                return ITEM_IS_ME;
            } else {
                return ITEM_IS_NOT_ME;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView iconImageView;
        private TextView chatInterfaceTextView;
        private TextView timeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImageView = (CircleImageView) itemView.findViewById(R.id.iconImageView);
            chatInterfaceTextView = (TextView) itemView.findViewById(R.id.chatInterfaceTextView);
            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
        }
    }


}
