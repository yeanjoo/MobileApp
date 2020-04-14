package hitesh.asimplegame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LevelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

    }


    public void easy(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
        Bundle b = new Bundle();
        b.putInt("level", 1);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
    public void medium(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
    public void hard(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}