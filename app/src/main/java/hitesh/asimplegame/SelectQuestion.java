package hitesh.asimplegame;


import android.app.Activity;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseAuth;


public class SelectQuestion extends Activity {
    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //효과음
    private SoundPool soundPool;
    private int soundID;
    private int vol=1;//볼륨, 추후 수정

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        soundPool = new SoundPool.Builder().build();
        soundID = soundPool.load(this,R.raw.button,1);
        btnLogOut = (Button) findViewById(R.id.btn_logout);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(SelectQuestion.this, LoginActivity.class);
                startActivity(I);

            }
        });
    }

    public void math(View o) {
        soundPool.play(soundID,1,1,0,0,0);
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }
    public void nonsense(View o) {
        soundPool.play(soundID,1,1,0,0,0);
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
    public void algorithm(View o) {
        soundPool.play(soundID,1,1,0,0,0);
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
    public void setting(View o) {
        soundPool.play(soundID,1,1,0,0,0);
        Intent intent = new Intent(this, SoundMusicManager.class);
        startActivity(intent);
    }
    public void voice(View o) {
        soundPool.play(soundID,1,1,0,0,0);
        Intent intent = new Intent(this, VoiceQuestionActivity.class);
        startActivity(intent);
    }
}
