//package hitesh.asimplegame;
//
//import android.app.Activity;
//import android.os.Build;
//import android.os.Bundle;
//import android.speech.tts.TextToSpeech;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.RequiresApi;
//
//import java.util.Locale;
//
//public class TTS extends Activity {
//    private final Bundle params = new Bundle();
//    private TextToSpeech tts;
//    private Button button;
//    private TextView answer1;
//    private TextView answer2;
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//LOLLIPOP 이상의 버전은 필요함
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//        button = findViewById(R.id.btn_tts);
//        answer1 = findViewById(R.id.answer1);
//        initTTS();
//
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                String text = textView.getText().toString();
//                speakTTS(text);
//
//                /*버전에 따라 다르게 생성 <--나중에 지워 줄 것
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    ttsGreater21(text);
//                } else {
//                    ttsUnder20(text);
//                }*/
//            }
//        });
//    }
//    private void initTTS() {
//        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, null);
//        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int state) {
//                if (state == TextToSpeech.SUCCESS) {
//                    tts.setLanguage(Locale.KOREAN);
//                } else {
//                    Toast.makeText(getApplicationContext(),"ERROR WHILE GENERATING OBJ",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //LOLLIPOP 이상의 버전은 필요함으로
//    private void speakTTS(final String text){
//        tts.speak(text,TextToSpeech.QUEUE_FLUSH,params,text);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//    @Override
//    protected void onDestroy() {
//        tts.stop();
//        tts.shutdown();
//        super.onDestroy();
//    }
//
//   /* @SuppressWarnings("deprecation")
//    private void ttsUnder20(String text) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
//        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private void ttsGreater21(String text) {
//        String utteranceId=this.hashCode() + "";
//        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
//    }*/ //버전에 따라 다르게 생성하는 코드 나중에 지워줘두 된다.
//}
