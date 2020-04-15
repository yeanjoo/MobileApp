package hitesh.asimplegame;


import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class LevelActivity extends Activity {

//    int level=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

    }

    public void easy(View o) {
//        setLevel(0);
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("level",0);
        startActivity(intent);
    }
    public void medium(View o) {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("level",1);
        startActivity(intent);
    }
    public void hard(View o) {
//        setLevel(2);
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("level",2);
        startActivity(intent);
    }

//    public void setLevel(int lv){
//        level = lv;
//    }
//
//    public int getLevel(){
//        return level;
//    }

}