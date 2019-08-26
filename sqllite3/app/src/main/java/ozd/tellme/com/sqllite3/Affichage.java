package ozd.tellme.com.sqllite3;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Affichage extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        Button back = (Button)findViewById(R.id.back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ShowAll();
    }

    public void ShowAll()
    {
        Cursor res = db.getAllData();
        if(res.getCount() == 0) {
            Toast.makeText(Affichage.this,"Le Tableau est vide!",Toast.LENGTH_LONG).show();
            return;
        }
        String x="";
        TextView txt = (TextView)findViewById(R.id.result);
        StringBuffer buff = new StringBuffer();
        while(res.moveToNext()){

            x += "ID: "+res.getString(0)+"\n";
           x+= "Nom: "+res.getString(1)+"\n";
           x+= "Phone Number: "+res.getString(2)+"\n";
        }

        txt.setText(x);
    }

}
