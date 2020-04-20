package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private Button SIGNUP;
    private Button LOGIN;
    private EditText ID;
    private EditText PW;

    private UserDBOpenHelper database;

//DB값을 String으로 변환해서 담는 임시 변수들
    private String id;
    private String pw;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SIGNUP = (Button)findViewById(R.id.signup);
        LOGIN = (Button)findViewById(R.id.login);
        ID= (EditText)findViewById(R.id.username);
        PW= (EditText)findViewById(R.id.password);
        SIGNUP.setEnabled(true);
        LOGIN.setEnabled(true);

    }
    public void check(){
        if(id.isEmpty()||pw.isEmpty()){
            Toast myToast = Toast.makeText(getApplicationContext(), R.string.NULL_MESSAGE,Toast.LENGTH_SHORT);
            myToast.show();
        }
        else if(database.isUser(id,pw)){
            intent = new Intent(getApplicationContext(), QuestionActivity.class);//다음페이지
            startActivity(intent);
        }
        else{//user가 존재하지 않는 경우
            Toast myToast = Toast.makeText(getApplicationContext(), R.string.NOT_MATCH_MESSAGE,Toast.LENGTH_SHORT);
            myToast.show();
        }
    }
    public void singup(){
        intent = new Intent(getApplicationContext(), SignUpActivity.class);//다음페이지
        startActivity(intent);
    }
    public void login(){
        id = ID.getText().toString();
        pw = PW.getText().toString();
        check();
    }
}
