package com.example.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnav.helper.MCHelper;
import com.example.bottomnav.helper.MenuHelper;
import com.example.bottomnav.helper.UserHelper;
import com.example.bottomnav.others.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirstActivity extends AppCompatActivity {
    EditText nicknameEdit;
    SQLiteDatabase userDB;
    UserHelper userHelper;

    EditText emailId, password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();
        nicknameEdit = findViewById(R.id.nickname_edit);
        emailId = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        btnSignUp = findViewById(R.id.Sbutton);
        tvSignIn = findViewById(R.id.textView);

        if (user != null && user.isEmailVerified()) {
            startActivity(new Intent(FirstActivity.this, UserActivity.class));
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailId.getText().toString();
                String pass = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("メールアドレスを入力してください");
                    emailId.requestFocus();
                } else if (pass.isEmpty()) {
                    password.setError("パスワードを入力してください");
                    password.requestFocus();
                } else if (email.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(FirstActivity.this, "各項目を正しく入力してください", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pass.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(FirstActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(FirstActivity.this, "サインアップに失敗しました、再入力をしてください", Toast.LENGTH_SHORT).show();
                            } else {
                                mFirebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(FirstActivity.this,
                                                    "仮登録が完了しました。確認メールのURLから本登録を行ってください。", Toast.LENGTH_LONG).show();
                                            emailId.setText("");
                                            password.setText("");
                                            startActivity(new Intent(FirstActivity.this, LoginActivity.class));
                                        } else {
                                            Toast.makeText(FirstActivity.this,
                                                    task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                if (userHelper == null) {
                                    userHelper = new UserHelper(getApplicationContext());
                                }

                                if (userDB == null) {
                                    userDB = userHelper.getWritableDatabase();
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(FirstActivity.this, "エラーが発生しました", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

//    private void insertData(SQLiteDatabase db, String nickname) {
//        ContentValues values = new ContentValues();
//        values.put("name", nickname);
//
//        db.insert("userdb", null, values);
//    }

//    private void readData() {
//        if (userHelper == null) {
//            userHelper = new UserHelper(getApplicationContext());
//        }
//        if (userDB == null) {
//            userDB = userHelper.getReadableDatabase();
//        }
//
//        Cursor c = userDB.query("userdb",
//                new String[]{"name"},
//                null,
//                null,
//                null,
//                null,
//                null);
//
//        b = c.moveToFirst();
//
//        c.close();
//    }
}
