package com.example.administrator.clownfish.tool;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.example.administrator.clownfish.modle.LocalContactModel;

import java.util.ArrayList;

/**
 * Created by hzwq on 2016/7/19.
 */
public class ContactsUtil {


    public static ArrayList<LocalContactModel> getContactList() {
        ArrayList<LocalContactModel> arrayList = new ArrayList<>();
        fetchContacts(arrayList);
        return arrayList;
    }


    private static void fetchContacts(ArrayList<LocalContactModel> arrayList) {

        String phoneNumber;
        String email = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String SORT_KEY = ContactsContract.Contacts.SORT_KEY_PRIMARY;
        String PHONE_BOOK_LABEL = "phonebook_label";
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, SORT_KEY);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    LocalContactModel localContactModel = new LocalContactModel();
                    String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                    String sortKey = cursor.getString(cursor.getColumnIndex(PHONE_BOOK_LABEL));

                    int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                    boolean hasName = !TextUtils.isEmpty(StringUtil.removeSpace(name));
                    if (hasPhoneNumber > 0 && hasName) {
                        localContactModel.setName(name);
                        localContactModel.setSortKey(sortKey);
                        Cursor phoneCursor = contentResolver.query(PHONE_CONTENT_URI, null, PHONE_CONTACT_ID + " = ?", new String[]{contact_id}, null);
                        if (phoneCursor != null) {
                            while (phoneCursor.moveToNext()) {
                                phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                                phoneNumber = StringUtil.removeSpace(phoneNumber);
                                if (phoneNumber.startsWith("+86")) {
                                    phoneNumber = phoneNumber.replace("+86", "");
                                }
                                localContactModel.setPhone(phoneNumber);
                            }
                            phoneCursor.close();
                        }
                        arrayList.add(localContactModel);
                    }
                }

            }
            cursor.close();
        }
    }

}
