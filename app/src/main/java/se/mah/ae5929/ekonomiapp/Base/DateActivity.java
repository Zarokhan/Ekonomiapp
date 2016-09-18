package se.mah.ae5929.ekonomiapp.Base;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import se.mah.ae5929.ekonomiapp.R;

public class DateActivity extends AppCompatActivity {

    private DatePicker picker;
    private Button submitBn;

    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        initializeComponents();
    }

    private void initializeComponents() {
        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");

        picker = (DatePicker)findViewById(R.id.datePicker);
        submitBn = (Button)findViewById(R.id.submitBn);

        submitBn.setOnClickListener(new SubmitBnClickListener());
    }

    private class SubmitBnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int day = picker.getDayOfMonth();
            int month = picker.getMonth();
            int year = picker.getYear();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            String date = df.format(cal.getTime());

            Intent intent = new Intent();
            intent.putExtra("mode", mode);
            intent.putExtra("date", date);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
