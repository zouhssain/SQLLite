package ozd.tellme.com.sqllite3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ajout extends AppCompatActivity {

    DatabaseHelper db;
    String Nom="test",Phone="067854";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button ajt = (Button)findViewById(R.id.ajt);
        ajt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });

    }

    public void Add()
    {
        EditText textphone = (EditText)findViewById(R.id.Text_Phone);
        EditText Text_Nom = (EditText)findViewById(R.id.Text_Nom);
        Nom = Text_Nom.getText().toString();
        Phone = textphone.getText().toString();
        boolean isInserted = db.addClient( Nom,Phone);
        if(isInserted == true)
            Toast.makeText(Ajout.this,"Client Inséré",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Ajout.this,"Client non Inséré",Toast.LENGTH_LONG).show();
    }
}
