package hitesh.asimplegame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RankingActivity extends Activity {
    String message = "";
    //TextView board;
    QuizDBOpenHelper helper = new QuizDBOpenHelper(this);

    private List<Score> scoreList;
    private int size;

    ListView listview;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        adapter = new ListViewAdapter();
        //리스트뷰 참조 및 adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        scoreList = helper.getAllScore();
        //사이즈 조정
        size = scoreList.size();
        if (size > 10) size = 10;

        for (int i = 0; i < size; i++) {
            Score score = scoreList.get(i);
            adapter.addItem(i,score.getUser(), score.getScore());
        }
        //board.setText(message);
    }
}
