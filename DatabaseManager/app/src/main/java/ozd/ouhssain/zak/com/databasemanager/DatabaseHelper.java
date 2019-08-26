package ozd.ouhssain.zak.com.databasemanager;


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

    public boolean addClient(Client client)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, client.getName());
        values.put(KEY_PH_NO, client.getPhoneNumber());
        long a = db.insert(TABLE_NAME, null, values);
        db.close();
        if(a == -1)
            return false;
        else
            return true;
    }

    Client getClient(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor= db.query(TABLE_NAME, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO },
                KEY_ID + "=?",new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor!= null)
            cursor.moveToFirst();
        Client client= new Client(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2)
        );
        return client;
    }

    public List<Client> getAllClients()
    {
        List<Client> clientList= new ArrayList<Client>();
        String selectQuery= "SELECT * FROM " + TABLE_NAME;SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do {
                Client client= new Client();
                client.setID(Integer.parseInt(cursor.getString(0)));
                client.setName(cursor.getString(1));
                client.setPhoneNumber(cursor.getString(2));
                clientList.add(client);
            } while(cursor.moveToNext());
        }
        return clientList;
    }

    public int updateClient(Client client)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, client.getName());
        values.put(KEY_PH_NO, client.getPhoneNumber());
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",new String[] { String.valueOf(client.getID()) });
    }

    public void deleteClient(Client client)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",new String[] { String.valueOf(client.getID()) });
        db.close();
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " +TABLE_NAME,null);
        return res;
    }
}
