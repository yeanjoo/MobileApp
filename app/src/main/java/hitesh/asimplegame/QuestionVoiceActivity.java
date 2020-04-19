package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Locale;

public class QuestionVoiceActivity extends Activity implements View.OnClickListener {

    final private String SUCCESS  ="축하합니다! 정답입니다";
    final private String FAILED ="틀렸습니다 다시 시도해보세요~";

    private EditText answer1;
    private TextView question1;
    private TextView result;
    private Button btn_ans1;
    private Button btn_que1;
    private Button btn_ans2;
    private String que1;
    private String Message=null;
    //STT
    Intent intent;
    SpeechRecognizer speech;
   // final int PERMISSION =1;
    //TTS
    private final Bundle params = new Bundle();
    private TextToSpeech tts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //builder
        result = findViewById(R.id.result);
        answer1 = findViewById(R.id.text_question1);
        question1= findViewById(R.id.text1);
        btn_ans1= findViewById(R.id.btn_ans1);
        btn_que1= findViewById(R.id.btn_que1);
        btn_ans2= findViewById(R.id.btn_ans2);
        que1=question1.getText().toString();
//<=============
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(listener);
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"ko-KR");
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        initTTS();

        //받아쓰기
        btn_que1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                speakTTS(que1);
            }
        });
        btn_ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(que1.equals(answer1.getText().toString())){
                    result.setText(SUCCESS);
                }else{
                    result.setText(FAILED);
                }
            }
        });
        //말해서 답변
        btn_ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                speech.startListening(intent);
                if(Message=="이"||Message=="2"||Message=="이 입니다"){
                    result.setText(SUCCESS);
                }else{
                    result.setText(FAILED);
                }
            }
        });

    }

    //<============STT result : message, voice -> text
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
            //textView.setText(Message);
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };
    //======>

    //<=====TTS text -> voice
    private void initTTS() {
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, null);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int state) {
                if (state == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.KOREAN);
                } else {
                    Toast.makeText(getApplicationContext(),"ERROR WHILE GENERATING OBJ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //LOLLIPOP 이상의 버전은 필요함으로
    private void speakTTS(final String text){
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,params,text);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        tts.stop();
        tts.shutdown();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
    }
    //=====>
}
