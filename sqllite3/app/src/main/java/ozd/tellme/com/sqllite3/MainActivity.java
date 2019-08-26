package ozd.tellme.com.sqllite3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ajt = (Button)findViewById(R.id.buttonAdd);
        ajt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Ajout.class);
                startActivity(intent);
            }
        });

        Button aff = (Button)findViewById(R.id.aff);
        aff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Affichage.class);
                startActivity(intent);
            }
        });
    }
}


