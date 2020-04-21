package hitesh.asimplegame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class SelectQuestion extends Activity {
    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

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
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }
    public void nonsense(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
    public void algorithm(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
    public void setting(View o) {
        Intent intent = new Intent(this, SoundMusicManager.class);
        startActivity(intent);
    }
    public void voice(View o) {
        Intent intent = new Intent(this, VoiceQuestionActivity.class);
        startActivity(intent);
    }
}
