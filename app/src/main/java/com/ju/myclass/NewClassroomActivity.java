package com.ju.myclass;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewClassroomActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_CLASSNAME = "com.ju.myclass.NewClassroomActivity.REPLY_CLASSNAME";

    private EditText mEditClassNameView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_classroom);
        mEditClassNameView = findViewById(R.id.edit_classname);

        final Button button = findViewById(R.id.button_save_classroom);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditClassNameView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String text = mEditClassNameView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY_CLASSNAME, text);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}