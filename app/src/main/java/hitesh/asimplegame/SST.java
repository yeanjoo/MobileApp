package hitesh.asimplegame;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SST extends Activity {
    Intent intent;
    SpeechRecognizer speech;
    Button button;
    TextView textView;
    String Message=" ";
    final int PERMISSION =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.stt_text);
        button = findViewById(R.id.btn_stt);

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(listener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speech.startListening(intent);
            }
        });

    }
    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
            Toast.makeText(getApplicationContext(),"음성 인식 시작합니다",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int i) {
            String message;

            switch (i) {

                case SpeechRecognizer.ERROR_AUDIO:
                    message = "ERROR_AUDIO";
                    break;

                case SpeechRecognizer.ERROR_CLIENT:
                    message = "ERROR_CLIENT";
                    break;

                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "ERROR_INSUFFICIENT_PERMISSIONS";
                    break;

                case SpeechRecognizer.ERROR_NETWORK:
                    message = "ERROR_NETWORK";
                    break;

                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "ERROR_NETWORK_TIMEOUT";
                    break;

                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "ERROR_NO_MATCH";
                    break;

                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "ERROR_RECOGNIZER_BUSY";
                    break;

                case SpeechRecognizer.ERROR_SERVER:
                    message = "ERROR_SERVER";
                    break;

                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "ERROR_SPEECH_TIMEOUT";
                    break;

                default:
                    message = "UNKNOWN";
                    break;
            }

            Log.e("GoogleActivity", "SPEECH ERROR : " + message);
            Toast.makeText(getApplicationContext(),"ERROR : "+message,Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onResults(Bundle bundle) {
            ArrayList<String> result = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            for (int i=0;i<result.size();i++){
                Message+=result.get(i);
            }
            textView.setText(Message);
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };
}
