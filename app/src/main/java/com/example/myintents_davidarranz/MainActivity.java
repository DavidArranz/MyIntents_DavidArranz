package com.example.myintents_davidarranz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RadioButton maps,url,dial;
    EditText etInPut;
    TextView tvAviso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dial = (RadioButton) findViewById(R.id.radioButtonLlamar);
        url =(RadioButton) findViewById(R.id.radioButtonUrl);
        maps = (RadioButton) findViewById(R.id.radioButtonMaps);
        etInPut=(EditText) findViewById(R.id.editTextInPut);
        tvAviso=(TextView) findViewById(R.id.textViewAviso);

        etInPut.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(dial.isSelected()){
                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+etInPut.getText().toString()));
                        startActivity(i);
                    }else {
                        Intent i = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(etInPut.getText().toString()));
                        startActivity(i);
                    }

                    etInPut.setText("");
                    etInPut.setVisibility(View.INVISIBLE);
                    clickable();
                }
            }
        });
        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etInPut.setVisibility(View.VISIBLE);
                tvAviso.setText("Se va a realizar una llamada");
                unclickable();
            }

        });

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etInPut.setVisibility(View.VISIBLE);
                tvAviso.setText("Se va a buscar el url");
                unclickable();
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:"+etInPut.getText().toString()));
                tvAviso.setText("Se va a abrir Google Maps");
                startActivity(i);
            }
        });

    }

    private void unclickable() {
        dial.setClickable(false);
        url.setClickable(false);
        maps.setClickable(false);
    }
    private void clickable() {
        dial.setClickable(true);
        url.setClickable(true);
        maps.setClickable(true);
    }
}