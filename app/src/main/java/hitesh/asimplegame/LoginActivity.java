package hitesh.asimplegame;

<<<<<<< Updated upstream
import android.app.Activity;
=======
import android.content.Context;
>>>>>>> Stashed changes
import android.content.Intent;
import android.content.SharedPreferences;
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

<<<<<<< Updated upstream
//DB값을 String으로 변환해서 담는 임시 변수들
    private String id;
    private String pw;
    private Intent intent;
=======
    //임시 저장 값
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
<<<<<<< Updated upstream
        SIGNUP = (Button)findViewById(R.id.signup);
        LOGIN = (Button)findViewById(R.id.login);
        ID= (EditText)findViewById(R.id.username);
        PW= (EditText)findViewById(R.id.password);
        SIGNUP.setEnabled(true);
        LOGIN.setEnabled(true);
        SIGNUP.setOnClickListener(new View.OnClickListener() {
=======

        firebaseAuth = FirebaseAuth.getInstance();

        sharedPref = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        loginEmailId = findViewById(R.id.loginEmail);
        logInpasswd = findViewById(R.id.loginpaswd);
        btnLogIn = findViewById(R.id.btnLogIn);
        signup = findViewById(R.id.TVSignIn);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(LoginActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(LoginActivity.this, SelectQuestion.class);
                    startActivity(I);
                } else {
                    Toast.makeText(LoginActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };
        signup.setOnClickListener(new View.OnClickListener() {
>>>>>>> Stashed changes
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), SignUpActivity.class);//다음페이지
                startActivity(intent);
            }
        });

        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< Updated upstream
                id = ID.getText().toString();
                pw = PW.getText().toString();
                check();
=======
                final String userEmail = loginEmailId.getText().toString();
                String userPaswd = logInpasswd.getText().toString();
                if (userEmail.isEmpty()) {
                    loginEmailId.setError("Provide your Email first!");
                    loginEmailId.requestFocus();
                } else if (userPaswd.isEmpty()) {
                    logInpasswd.setError("Enter Password!");
                    logInpasswd.requestFocus();
                } else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                            } else {
                                editor.putString("id",userEmail);
                                editor.commit();
                                startActivity(new Intent(LoginActivity.this, SelectQuestion.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
>>>>>>> Stashed changes
            }
        });
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
}
