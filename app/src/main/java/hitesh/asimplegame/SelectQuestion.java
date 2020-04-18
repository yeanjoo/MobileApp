package hitesh.asimplegame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SelectQuestion extends Activity {
    Button setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        setting = findViewById(R.id.btn_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SettingSound.class);
                startActivity(intent);
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
}