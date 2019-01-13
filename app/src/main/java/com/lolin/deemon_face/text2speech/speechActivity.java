package com.lolin.deemon_face.text2speech;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class speechActivity extends AppCompatActivity {

    private TextView speechInput;
    private Button clickBtn;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_speech);

        speechInput = findViewById (R.id.speech_TV);
        clickBtn = findViewById (R.id.speech_Btn);

        clickBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra (RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra (RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault ());
                intent.putExtra (RecognizerIntent.EXTRA_PROMPT,"Say Samething");
                try{
                    startActivityForResult (intent,REQ_CODE_SPEECH_INPUT);
                }catch(ActivityNotFoundException a){
                    Toast.makeText (getApplicationContext (),"Sorry your device is not supported",Toast.LENGTH_SHORT).show ();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:{
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra (RecognizerIntent.EXTRA_RESULTS);
                    String speechText = speechInput.getText ().toString () + "\n" + result.get (0);
                    speechInput.setText (speechText);
                }

                break;
            }
        }
    }
}
