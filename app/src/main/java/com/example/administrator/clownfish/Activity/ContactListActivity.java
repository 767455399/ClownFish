package com.example.administrator.clownfish.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.modle.LocalContactModel;
import com.example.administrator.clownfish.present.ContactsPresent;
import com.example.administrator.clownfish.tool.CharacterParser;
import com.example.administrator.clownfish.tool.ContactsUtil;
import com.example.administrator.clownfish.tool.PermissionUtil;
import com.example.administrator.clownfish.view.ListSideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactListActivity extends BaseActivity {
    private RecyclerView contactsRecyclerView;
    private TextView popupTextView;
    private ListSideBar sideBar;
    private ContactListAdapter contactListAdapter;
    private ArrayList<LocalContactModel> contactModels = new ArrayList<>();
    private boolean move = false;
    private static final int REQUEST_CODE_CONTACTS_PERMISSIONS = 0x0001;
    private ContactsPresent present;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_contact_list2);
        sideBar=(ListSideBar)findViewById(R.id.sidrbar);
        popupTextView=(TextView)findViewById(R.id.popupTextView);
        contactsRecyclerView=(RecyclerView)findViewById(R.id.contactsRecyclerView);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bind();
        sideBar.setTextView(popupTextView);
        contactListAdapter=new ContactListAdapter();
        contactsRecyclerView.setAdapter(contactListAdapter);
        ListSideBar sideBar = new ListSideBar(ContactListActivity.this);
        characterParser = CharacterParser.getInstance();
        // 根据a-z进行排序源数据
        Collections.sort(contactModels, new Comparator<LocalContactModel>() {
            @Override
            public int compare(LocalContactModel lhs, LocalContactModel rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });

    }

    @Override
    protected void loadData() {
// 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new ListSideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                final int position = contactListAdapter.getPosition(s);
                if (position != -1) {
                    final LinearLayoutManager m = (LinearLayoutManager) contactsRecyclerView.getLayoutManager();
                    int firstPosition = m.findFirstVisibleItemPosition();
                    int lastPosition = m.findLastVisibleItemPosition();
                    if (position <= firstPosition) {
                        contactsRecyclerView.scrollToPosition(position);
                    } else if (position <= lastPosition) {
                        contactsRecyclerView.scrollBy(0, contactsRecyclerView.getChildAt(position - firstPosition).getTop());
                    } else {
                        contactsRecyclerView.scrollToPosition(position);
                        move = true;
                    }

                    contactsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if (move) {
                                move = false;
                                int offset = position - m.findFirstVisibleItemPosition();
                                if (offset >= 0 && offset <= recyclerView.getChildCount()) {
                                    contactsRecyclerView.scrollBy(0, contactsRecyclerView.getChildAt(offset).getTop());
                                }
                            }
                        }
                    });
                }
            }
        });
    }


    protected void bind() {
        present = new ContactsPresent();
        checkContactsPermission();
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void checkContactsPermission() {
        if (PermissionUtil.checkPermission(ContactListActivity.this, Manifest.permission.READ_CONTACTS)) {
            present.bind(this);
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_CONTACTS_PERMISSIONS);
        }
    }

    public void refreshList(){
        contactListAdapter.notifyDataSetChanged();
    }

    public void setAdapter(ArrayList<LocalContactModel> contactList){
        contactModels.clear();
        contactModels.addAll(ContactsUtil.getContactList());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CONTACTS_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    PermissionUtil.showPermissionsDescDialog(this, "请在设置-应用-小泰乐活-权限中开启联系人读取权限，以正常使用通讯录功能");
                } else {
                    // Permission GRANTED
                    present = new ContactsPresent();
                    present.bind(this);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private class ContactListAdapter extends RecyclerView.Adapter<ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(ContactListActivity.this).inflate(R.layout.item_contact_list,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final LocalContactModel localContactModel = contactModels.get(position);
            holder.contactsListItemNumberTextView.setText(localContactModel.getPhone());
            holder.contactsListItemNameTextView.setText(localContactModel.getName());
        }

        @Override
        public int getItemCount() {
            return contactModels.size();
        }



        public int getPosition(String s) {
            for (int i = 0; i < contactModels.size(); i++) {
                String sortKey = contactModels.get(i).getSortKey();
                Log.e("sortKey", sortKey);
                if (sortKey.equals(s)) {
                    return i;
                }
            }
            return -1;
        }

    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        private TextView contactsListItemNameTextView;
        private TextView contactsListItemNumberTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            contactsListItemNameTextView=(TextView)itemView.findViewById(R.id.contactsListItemNameTextView);
            contactsListItemNumberTextView=(TextView)itemView.findViewById(R.id.contactsListItemNumberTextView);
        }
    }
}
