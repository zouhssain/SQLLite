package ozd.ouhssain.zak.com.databasemanager;


import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity
{
    DatabaseHelper db;
    EditText Nom,Phone;
    Button btnAdd,btnShowAll,btnDelete,update;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        final EditText delete_id = (EditText) findViewById(R.id.delete_id);

        Nom=(EditText)findViewById(R.id.Text_Nom);
        Phone=(EditText)findViewById(R.id.Text_Phone);
        btnAdd=(Button)findViewById(R.id.buttonAdd);
        btnShowAll=(Button)findViewById(R.id.buttonShowAll);
        btnDelete=(Button)findViewById(R.id.buttonDelete);
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Client C = db.getClient(Integer.parseInt(delete_id.getText().toString()));
                        db.deleteClient(C);
                            Toast.makeText(MainActivity.this,"Client de id "+Integer.parseInt(delete_id.getText().toString())+" est Supprimer",Toast.LENGTH_LONG).show();
                    }
                }
        );
            Add();
        ShowAll();
        update();
    }

    public void Add()
    {
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = db.addClient(new Client(Nom.getText().toString(),Phone.getText().toString()));
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Client Inséré",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Client non Inséré",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void ShowAll()
    {
        btnShowAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getAllData();
                        if(res.getCount() == 0) {
                            Toast.makeText(MainActivity.this,"Le Tableau est vide!",Toast.LENGTH_LONG).show();
                            return;
                        }
                        StringBuffer buff = new StringBuffer();
                        while(res.moveToNext()){
                            buff.append("ID: "+res.getString(0)+"\n");
                            buff.append("Nom: "+res.getString(1)+"\n");
                            buff.append("Phone Number: "+res.getString(2)+"\n");
                        }
                        Message("Data",buff.toString());
                    }
                }
        );
    }

    public void update()
    {
        final EditText delete_id = (EditText) findViewById(R.id.delete_id);
        final EditText Editname = (EditText) findViewById(R.id.Text_Nom);
        final EditText Editphone = (EditText) findViewById(R.id.Text_Phone);
        update=(Button)findViewById(R.id.buttonUpdate);
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Client C = db.getClient(Integer.parseInt(delete_id.getText().toString()));
                        C.setName(Editname.getText().toString());
                        C.setPhoneNumber(Editphone.getText().toString());
                        db.updateClient(C);
                        Toast.makeText(MainActivity.this,"Client de id "+Integer.parseInt(delete_id.getText().toString())+" est modifier",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void Message(String Title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }
}

