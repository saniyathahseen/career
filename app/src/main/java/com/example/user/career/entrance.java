package com.example.user.career;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class entrance extends AppCompatActivity {
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        ExpandableListView list1 = (ExpandableListView) findViewById(R.id.list4);
        dbhelper db=new dbhelper(this);
        listDataHeader = db.eCategory();
        listDataChild =prepareListData(listDataHeader);
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        list1.setAdapter(listAdapter);
        //----


        list1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent ints = new Intent(getApplicationContext(), entrance1.class);
                dbhelper db=new dbhelper(getApplicationContext());

                int rowid = db.getEntranceId(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                ints.putExtra("eid", String.valueOf(rowid));

                startActivity(ints);
                return false;
            }
        });



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private  HashMap<String, List<String>>  prepareListData(List<String> head) {

        dbhelper db = new dbhelper(this);
        HashMap<String, List<String>> childList = new HashMap<String, List<String>>();

        for (int i = 0; i < head.size(); i++) {
            List<String> l1 = new ArrayList<String>();
            l1 = db.eName(head.get(i));
            childList.put(listDataHeader.get(i), l1);
        }
        return childList;
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
