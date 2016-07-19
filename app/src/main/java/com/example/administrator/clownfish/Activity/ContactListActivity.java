package com.example.administrator.clownfish.Activity;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.clownfish.R;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {
    private AsyncQueryHandler asyncQuery;
    static List<ContentValues> list = new ArrayList<ContentValues>();
    private static final String NAME = "name", NUMBER = "number", SORT_KEY = "sort_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        asyncQuery = new MyAsyncQueryHandler(getContentResolver());
    }


    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = Uri.parse("content://com.android.contacts/data/phones");
        String[] projection = { "_id", "display_name", "data1", "sort_key" };
        asyncQuery.startQuery(0, null, uri, projection, null, null,
                "sort_key COLLATE LOCALIZED asc");
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
                if (list.size() > 0) {

                }
            }

        }

    }

    public static List getContactlist(){
        return list;
    }

}
