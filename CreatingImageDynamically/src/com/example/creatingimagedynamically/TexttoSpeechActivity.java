package com.example.creatingimagedynamically;

import java.util.Locale;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TexttoSpeechActivity extends Activity implements OnInitListener {
	

	private TextToSpeech tts;
	private Button btnSpeak;
	private EditText txtText;
	public Animation animAlpha;
	DatabaseHandler dbHelper;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		tts = new TextToSpeech(this, this);
		btnSpeak = (Button) findViewById(R.id.btnSpeak);
		txtText = (EditText) findViewById(R.id.txtText);
		animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
		btnSpeak.startAnimation(animAlpha);
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				speakOut();
				
				
			}

		});
	}

	@Override
	public void onDestroy() 
	{
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	public void onInit(int status) {
		// TODO Auto-generated method stub

		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);
			tts.setPitch(0);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Toast.makeText(this, "Language not supported", Toast.LENGTH_LONG).show();
				Log.e("TTS", "Language is not supported");
			} else {
				btnSpeak.setEnabled(true);

			}

		} else {
			Log.e("TTS", "Initilization Failed");
		}

	}

	private void speakOut() {
		dbHelper=new DatabaseHandler(this);
		String text = txtText.getText().toString();
		dbHelper.addKid(txtText.getText().toString());
		Log.v("EditText", txtText.getText().toString());
		if (text.length() == 0) {
			
			tts.speak("what is your name", TextToSpeech.QUEUE_FLUSH, null);
			
			Toast.makeText(getApplicationContext(), " What is your name! ", Toast.LENGTH_LONG).show();
		} else {
			tts.speak("Lets play"+text, TextToSpeech.QUEUE_FLUSH, null);
			Toast.makeText(getApplicationContext(), "Lets learn some number", Toast.LENGTH_LONG).show();
			Intent RandomActivityCall = new Intent(TexttoSpeechActivity.this,MainActivity.class);
			startActivity(RandomActivityCall);
		}
		
	}
}