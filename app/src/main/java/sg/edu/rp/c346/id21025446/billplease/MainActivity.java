package sg.edu.rp.c346.id21025446.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amt;
    EditText noPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView ttlbill;
    TextView eachPay;
    Button split;
    Button reset;
    EditText disc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.enterAmt);
        noPax = findViewById(R.id.enterNo);
        svs = findViewById(R.id.svs);
        gst = findViewById(R.id.gst);
        ttlbill = findViewById(R.id.ttl);
        eachPay = findViewById(R.id.eachPays);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        disc = findViewById(R.id.enterDisc);

        split.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(amt.getText().toString().trim().length() !=0 && noPax.getText().toString().trim().length()!=0){
                    double newAmt = 0.0;
                    if(!svs.isChecked() && !gst.isChecked()){
                        newAmt = Double.parseDouble(amt.getText().toString());
                    }
                    else if (svs.isChecked() && !gst.isChecked()){
                        newAmt = Double.parseDouble(amt.getText().toString()) * 1.1;
                    }
                    else if (!svs.isChecked() && gst.isChecked()){
                        newAmt = Double.parseDouble(amt.getText().toString()) * 1.07;
                    } else{
                        newAmt = Double.parseDouble(amt.getText().toString()) * 1.17;
                    }

                    if (disc.getText().toString().trim().length() !=0){
                        newAmt *= 1 - Double.parseDouble(disc.getText().toString()) /100;
                    }

                    ttlbill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int noPeople = Integer.parseInt(noPax.getText().toString());
                    if (noPeople !=1){
                        eachPay.setText("Each pays: $" + String.format("%.2f", newAmt/noPeople));
                    }else{
                        eachPay.setText("Each pays: $" + newAmt);
                    }
                }


            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                amt.setText("");
                noPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                disc.setText("");
            }
        });
    }
}