package com.example.administrator.clownfish.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactsActivity extends BaseActivity {
    private static final int REQUEST_CODE_CONTACTS_PERMISSIONS = 0x0001;
    private ArrayList<LocalContactModel> contactModels = new ArrayList<>();
    private RecyclerView contactsRecyclerView;
    private ContactsAdapter contactsAdapter;
    private ListSideBar sideBar;
    private TextView popupTextView;
    private ContactsPresent present;
    private AsyncQueryHandler asyncQuery;
    static List<ContentValues> list = new ArrayList<ContentValues>();
    private static final String NAME = "name", NUMBER = "number", SORT_KEY = "sort_key";
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_contacts);
        list.clear();
        asyncQuery = new MyAsyncQueryHandler(getContentResolver());
        bind();
        contactsRecyclerView = (RecyclerView) findViewById(R.id.contactsRecyclerView);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsAdapter = new ContactsAdapter();
        contactsRecyclerView.setAdapter(contactsAdapter);
        sideBar = (ListSideBar) findViewById(R.id.sidrbar);
        popupTextView = (TextView) findViewById(R.id.popupTextView);
        ListSideBar sideBar = new ListSideBar(ContactsActivity.this);
        sideBar.setTextView(popupTextView);
        characterParser = CharacterParser.getInstance();
        // 根据a-z进行排序源数据
        Collections.sort(contactModels, new Comparator<LocalContactModel>() {
            @Override
            public int compare(LocalContactModel lhs, LocalContactModel rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });

        Uri uri = Uri.parse("content://com.android.contacts/data/phones");
        String[] projection = { "_id", "display_name", "data1", "sort_key" };
        asyncQuery.startQuery(0, null, uri, projection, null, null,
                "sort_key COLLATE LOCALIZED asc");
        contactsAdapter.notifyDataSetChanged();

    }

    private boolean move = false;
    public void setAdapter(ArrayList<LocalContactModel> contacts) {
        contactModels.clear();
      /*  contactsAdapter.notifyDataSetChanged();*/
        contactModels.addAll(ContactsUtil.getContactList());
    }


    private class MyAsyncQueryHandler extends AsyncQueryHandler {


        public MyAsyncQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    ContentValues cv = new ContentValues();
                    cursor.moveToPosition(i);
                    String name = cursor.getString(1);
                    String number = cursor.getString(2);
                    String sortKey = cursor.getString(3);
                    if (number.startsWith("+86")) {
                        cv.put(NAME, name);
                        cv.put(NUMBER, number.substring(3));  //ȥ��+86
                        cv.put(SORT_KEY, sortKey);
                    } else {
                        cv.put(NAME, name);
                        cv.put(NUMBER, number);
                        cv.put(SORT_KEY, sortKey);
                    }
                    list.add(cv);
                }

            }

        }

    }


    @Override
    protected void loadData() {
// 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new ListSideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                final int position = contactsAdapter.getPosition(s);
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
        if (PermissionUtil.checkPermission(ContactsActivity.this, Manifest.permission.READ_CONTACTS)) {
            present.bind(this);
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_CONTACTS_PERMISSIONS);
        }
    }

    public void refreshList() {
        contactsAdapter.notifyDataSetChanged();
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

 private  class ContactsAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ContactsActivity.this).inflate(R.layout.item_contacts, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
           /* final LocalContactModel localContactModel = contactModels.get(position);
            holder.contactsListItemNameTextView.setText(localContactModel.getName());
            holder.contactsListItemNumberTextView.setText(localContactModel.getPhone());*/
            holder.contactsListItemNameTextView.setText(list.get(position).get("name").toString());
            holder.contactsListItemNumberTextView.setText(list.get(position).get("number").toString());
        }

        @Override
        public int getItemCount() {
            return list.size();
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contactsListItemNameTextView;
        private TextView contactsListItemNumberTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            contactsListItemNameTextView = (TextView) itemView.findViewById(R.id.contactsListItemNameTextView);
            contactsListItemNumberTextView = (TextView) itemView.findViewById(R.id.contactsListItemNumberTextView);
        }
    }
}
