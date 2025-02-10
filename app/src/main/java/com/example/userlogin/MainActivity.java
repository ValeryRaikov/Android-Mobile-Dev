package com.example.userlogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    User Rec1 = new User("admin", "1234");
    final Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final TextView userName = (TextView) findViewById(R.id.userNameBOX);
        final TextView password = (TextView) findViewById(R.id.PasswordBOX);
        final Button login = (Button) findViewById(R.id.LoginBtn);

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(R.mipmap.ic_launcher);

        alertDialogBuilder = new AlertDialog.Builder(this);

        db.addUser(Rec1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = db.getAllUsers();
                boolean log = true;

                for(User u : users) {
                    if (userName.getText().toString().equals(u.getUserName()) &&
                        password.getText().toString().equals(u.getPassword())) {
                        log = false;
                        open("Login Successful!", log);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    }

                    if (log) {
                        open("Unsuccessful!", log);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }
            }
        });
    }

    public void open(String ss, final boolean log) {
        alertDialogBuilder.setMessage(ss);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Create New Activity -> Work Activity
                if (log == false)
                    startActivity(new Intent(MainActivity.this, WorkActivity.class));
            }
        });
    }
}