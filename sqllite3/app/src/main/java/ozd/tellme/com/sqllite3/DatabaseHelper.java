package ozd.tellme.com.sqllite3;



import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final  int DATABASE_VERSION =  1;
    public static final  String DATABASE_NAME =  "Client.db";
    public static final  String TABLE_NAME =  "Client";
    public static final  String KEY_ID =  "id";
    public static final  String KEY_NAME =  "name";
    public static final  String KEY_PH_NO =  "phone_number";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CLIENTS_TABLE = "CREATE TABLE " + TABLE_NAME +
                "("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"+ KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CLIENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addClient(String name, String number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PH_NO, number);
        long a = db.insert(TABLE_NAME, null, values);
        db.close();
        if(a == -1)
            return false;
        else
            return true;
    }



    public Cursor getAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " +TABLE_NAME,null);
        return res;
    }

    public void upgradeClient(int id,String name,String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_PH_NO,phone);
        db.update(TABLE_NAME,values,""+TABLE_NAME+"", new String[] {String.valueOf( id)} );
    }
}
