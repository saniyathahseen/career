package com.example.user.career;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class scholarship extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship);
        dbhelper db=new dbhelper(this);
        Cursor c=db.sName();
        ListView list = (ListView) findViewById(android.R.id.list);
        String s[]={"Name"};
        int[] v = new int[]{android.R.id.text1};
        SimpleCursorAdapter ad=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,c,s,v);
        list.setAdapter(ad);
        list.setOnItemClickListener(this);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, scholarship1.class);
        i.putExtra("sid", String.valueOf(id));
        startActivity(i);
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
            case R.id.i3:

                break;
            case R.id.i4:
                Intent i4=new Intent(this,about.class);
                startActivity(i4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
