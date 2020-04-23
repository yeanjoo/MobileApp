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

public class SignUpActivity extends Activity {
    private User CurUser;
    private Button SignUp;
    private EditText NAME;
    private EditText ID;
    private EditText PW;
    private String id;
    private String name;
    private String password;

    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
<<<<<<< Updated upstream
        final UserDBOpenHelper db = new UserDBOpenHelper(this);

        SignUp = (Button) findViewById(R.id.submit);
        NAME = (EditText)findViewById(R.id.name_form);
        ID= (EditText)findViewById(R.id.id_form);
        PW= (EditText)findViewById(R.id.password_form);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = ID.getText().toString(); //string으로 변환하여 저장
                password = PW.getText().toString();
                name = NAME.getText().toString();

                if(id.isEmpty()||password.isEmpty()||name.isEmpty()){ //만약 빈칸이라면
                    Toast myToast = Toast.makeText(getApplicationContext(), R.string.NULL_MESSAGE,Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    CurUser =new User(id,password,name);
                    db.addUser(CurUser);
                   Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);//다음페이지
                    startActivity(intent);
=======
        firebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.ETemail);
        passwd = findViewById(R.id.ETpassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        signIn = findViewById(R.id.TVSignIn);

        sharedPref = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailID = emailId.getText().toString();
                String paswd = passwd.getText().toString();
                if (emailID.isEmpty()) {
                    emailId.setError("Provide your Email first!");
                    emailId.requestFocus();
                } else if (paswd.isEmpty()) {
                    passwd.setError("Set your password");
                    passwd.requestFocus();
                } else if (emailID.isEmpty() && paswd.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                editor.putString("id",emailID);
                                editor.commit();
                                startActivity(new Intent(SignUpActivity.this, SelectQuestion.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
>>>>>>> Stashed changes
                }
            }
        });
    }
}