package hitesh.asimplegame;


import android.app.Activity;
import android.content.Intent;
<<<<<<< Updated upstream
import android.os.Bundle;
import android.view.View;
=======
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
>>>>>>> Stashed changes


public class SelectQuestion extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

    }

    public void math(View o) {
        Intent intent = new Intent(this, LevelActivity.class);
        b.putInt("mode", 1);
        intent.putExtras(b);
        startActivity(intent);
    }
<<<<<<< Updated upstream
    public void nonsense(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
    public void algorithm(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
=======

    public void setting(View o) {
        soundPool.play(soundID, sf.getInt("effect", 1), sf.getInt("effect", 1), 0, 0, 0);
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void ranking(View o){
        soundPool.play(soundID, sf.getInt("effect", 1), sf.getInt("effect", 1), 0, 0, 0);
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);

    }

>>>>>>> Stashed changes
}