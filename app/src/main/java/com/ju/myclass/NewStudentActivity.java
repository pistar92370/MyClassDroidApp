package com.ju.myclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ju.myclass.entities.Student;
import com.ju.myclass.view.fragments.DatePickerFragment;

import java.util.Date;

public class NewStudentActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_CLASSROOM_ID = "com.ju.myclass.NewClassroomActivity.REPLY_CLASSROOM_ID";
    public static final String EXTRA_REPLY_FIRST_NAME = "com.ju.myclass.NewClassroomActivity.REPLY_FIRST_NAME";
    public static final String EXTRA_REPLY_LAST_NAME = "com.ju.myclass.NewClassroomActivity.REPLY_LAST_NAME";
    public static final String EXTRA_REPLY_SEX = "com.ju.myclass.NewClassroomActivity.REPLY_SEX";
    public static final String EXTRA_REPLY_BIRTH_DATE = "com.ju.myclass.NewClassroomActivity.REPLY_BIRTH_DATE";
    public static final String EXTRA_REPLY_CONTACT_NBR_1 = "com.ju.myclass.NewClassroomActivity.REPLY_CONTACT_NBR_1";
    public static final String EXTRA_REPLY_CONTACT_NBR_2 = "com.ju.myclass.NewClassroomActivity.REPLY_CONTACT_NBR_2";
    private String[] array_sex = {Student.SEX_FEMALE, Student.SEX_MALE};

    private long classroomId;
    private EditText mEditStudentViewFirstName;
    private EditText mEditStudentViewLastName;
    private Spinner mEditStudentSpinnerSex;
    private String sex = "NONE";
    private EditText mEditStudentViewBirthDate;
    private Date birthDate;
    private EditText mEditStudentViewContactNbr1;
    private EditText mEditStudentViewContactNbr2;
    //    private EditText mEditStudentViewClassroomId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classroomId = getIntent().getLongExtra("CLASS_ID", -1);
        setContentView(R.layout.activity_new_student);

        mEditStudentViewFirstName = findViewById(R.id.edit_student_first_name);
        mEditStudentViewLastName = findViewById(R.id.edit_student_last_name);

        mEditStudentSpinnerSex = findViewById(R.id.edit_student_sex);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_sex);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mEditStudentSpinnerSex.setAdapter(adapter);
        mEditStudentSpinnerSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex = array_sex[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        mEditStudentViewBirthDate = findViewById(R.id.edit_student_birthday);
        mEditStudentViewContactNbr1 = findViewById(R.id.edit_student_contact_nbr_1);
        mEditStudentViewContactNbr2 = findViewById(R.id.edit_student_contact_nbr_2);

        final Button button = findViewById(R.id.button_save_student);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditStudentViewFirstName.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String firstName = mEditStudentViewFirstName.getText().toString();
                String lastName = mEditStudentViewLastName.getText().toString();
                String contactNbr1 = mEditStudentViewContactNbr1.getText().toString();
                String contactNbr2 = mEditStudentViewContactNbr2.getText().toString();

                replyIntent.putExtra(EXTRA_REPLY_FIRST_NAME, firstName);
                replyIntent.putExtra(EXTRA_REPLY_LAST_NAME, lastName);
                replyIntent.putExtra(EXTRA_REPLY_CLASSROOM_ID, classroomId);
                replyIntent.putExtra(EXTRA_REPLY_SEX, sex);
                replyIntent.putExtra(EXTRA_REPLY_BIRTH_DATE, birthDate.getTime());
                replyIntent.putExtra(EXTRA_REPLY_CONTACT_NBR_1, contactNbr1);
                replyIntent.putExtra(EXTRA_REPLY_CONTACT_NBR_2, contactNbr2);

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment datePickerFragment = new DatePickerFragment(this, mEditStudentViewBirthDate);
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}