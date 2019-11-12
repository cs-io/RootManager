package com.creepersan.rootmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creepersan.rootmanager.Conversation;
import com.creepersan.rootmanager.ConversationBuilder;
import com.creepersan.rootmanager.RootManager;

public class MainActivity extends AppCompatActivity {
    private RootManager rootManager = RootManager.getInstance();
    private Conversation conversation = rootManager.createConversation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((EditText) findViewById(R.id.cmdInput)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick_exec(findViewById(R.id.cmdExec));
                    return true;
                }
                return false;
            }
        });
    }

    public void onClick_hasPermission(View view){
        Toast.makeText(this, String.valueOf(rootManager.checkHasRootPermission()), Toast.LENGTH_SHORT).show();
    }

    public void onClick_ls(View view){
        conversation.exec("ls");
    }

    public void onClick_cd_system(View view){
        conversation.exec("cd system");
    }

    public void onClick_cd_go_back(View view){
        conversation.exec("cd ..");
    }

    public void onClick_pwd(View view){
        conversation.exec("pwd");
    }


    public void onClick_exec(View view){
        EditText editText = (EditText) findViewById(R.id.cmdInput);
        conversation.exec(editText.getText().toString().trim());
        editText.setText("");
    }
}
