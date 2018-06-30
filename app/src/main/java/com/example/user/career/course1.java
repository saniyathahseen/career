package com.example.user.career;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class course1 extends AppCompatActivity {
    Button b,b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course1);
         b=(Button)findViewById(R.id.button);
         b1=(Button)findViewById(R.id.button3);
         b2=(Button)findViewById(R.id.button4);
         b3=(Button)findViewById(R.id.button5);
    }
    public void butt(View v)
    {
        Intent i=new Intent(this,course.class);
        i.putExtra("s","After SSLC");
        startActivity(i);
    }
    public void butt1(View v)
    {
        Intent i=new Intent(this,course.class);
        i.putExtra("s","After 12th");
        startActivity(i);
    }
    public void butt2(View v)
    {
        Intent i=new Intent(this,course.class);
        i.putExtra("s","After UG");
        startActivity(i);
    }
    public void butt3(View v)
    {
        Intent i=new Intent(this,course.class);
        i.putExtra("s","After PG");
        startActivity(i);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId())
        {
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
                } catch(Exception e) {
                    //e.toString();
                }
                break;
            case R.id.i4:
                Intent i4=new Intent(this,about.class);
                startActivity(i4);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
