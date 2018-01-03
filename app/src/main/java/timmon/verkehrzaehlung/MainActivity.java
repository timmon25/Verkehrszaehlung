package timmon.verkehrzaehlung;

import android.content.Context;
import android.graphics.Path;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;


public class MainActivity extends AppCompatActivity {
    int PKW = 0;
    int LKW =0;
    String pkwliste="";
    String lkwliste="";
    Context cxt = this;
    Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonPKW = (Button) findViewById(R.id.bPKW);
        final TextView txtPKW = (TextView) findViewById(R.id.txtPKW) ;
        final Button buttonLKW = (Button) findViewById(R.id.bLKW);
        final Button buttonSave= (Button) findViewById(R.id.bSave);
        final Spinner spinner= (Spinner) findViewById(R.id.spinner);
        final TextView txtLKW = (TextView) findViewById(R.id.txtLKW) ;
        final TextView txtSave = (TextView) findViewById(R.id.tSaveOrt) ;


        buttonPKW.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PKW+= +1;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",Locale.GERMANY);
                String datum_Zeit = dateFormat.format(new java.util.Date());
                pkwliste +=datum_Zeit+";";
                txtPKW.setText(PKW+"");
                // Perform action on click
            }
        });
        buttonLKW.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LKW+= +1;

                txtLKW.setText(LKW+"");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",Locale.GERMAN);
                String datum_Zeit = dateFormat.format(new java.util.Date());
                lkwliste +=datum_Zeit.toString()+";";
                // Perform action on click
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",Locale.GERMAN);
                String datum_Zeit = dateFormat.format(new java.util.Date());

                String dateiname =spinner.getSelectedItem().toString()+ datum_Zeit;

                String ausgabe = (dateiname+",\nAnzahl an PKWs: "+ txtPKW.getText()+"\nAnzahl an LKWs: "+txtLKW.getText()+";\nPKWliste : "+pkwliste+" |\nLKWliste : "+lkwliste);

               saveToFile(dateiname,ausgabe);
                Toast tes =Toast.makeText(getApplicationContext(),"Theroetisch alles gespeichert", Toast.LENGTH_LONG );
            tes.show();

            }
        });





    }
    private boolean saveToFile(String dateiname, String text){

        final TextView txtSave = (TextView) findViewById(R.id.tSaveOrt) ;
        try {

            File root = new File(getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath(), "VerkehrDaten");
            // if external memory exists and folder with name Notes
            txtSave.setText(root.toString());
            if (!root.exists()) {

                root.mkdirs(); // this will create folder.

            }

            File filepath = new File(root,   dateiname+".txt");  // file path to save

            FileWriter writer = new FileWriter(filepath);

            writer.append(text);

            writer.flush();
            writer.close();


        } catch (IOException e) {

            e.printStackTrace();


        }

return true;
    }
}
