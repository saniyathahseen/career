package com.example.user.career;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class institution1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution1);

        TextView t = (TextView) findViewById(R.id.textView12);
        TextView t1 = (TextView) findViewById(R.id.textView14);
        TextView t2 = (TextView) findViewById(R.id.textView19);


        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String course = intent.getStringExtra("course");
        String website = intent.getStringExtra("website");



        t.setText(description);
        t1.setText(course);
        t2.setText(website);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.i1:
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                break;
            case R.id.i2:
                Toast t= Toast.makeText(this,"Future Update",Toast.LENGTH_SHORT);
                t.show();

                break;
            case R.id.i3:
                try {
                    Intent im = new Intent(Intent.ACTION_SEND);
                    im.setType("text/plain");
                    im.putExtra(Intent.EXTRA_SUBJECT, "InfoCareer");
                    String sAux = "\nLet me recommend you Kerala's best career application which includes wide range of information on courses, institutions, entrance exams and scholarships\n\n\n";
                    sAux = sAux + "http://bit.ly/InfoCareer-Best_Career_App";
                    im.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(im, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
                break;
            case R.id.i4:
                Intent i4 = new Intent(this, about.class);
                startActivity(i4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
