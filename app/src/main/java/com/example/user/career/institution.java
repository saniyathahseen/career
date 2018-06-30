package com.example.user.career;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.user.career.model.InstitutionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class institution extends AppCompatActivity {
    List<String> listDataHeader;
    HashMap<String, List<InstitutionModel>> listDataChild;
    String iType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution);
        ExpandableListView list1 = (ExpandableListView) findViewById(R.id.list3);
        dbhelper db = new dbhelper(this);


        if (getIntent().hasExtra("Type"))
            iType = getIntent().getStringExtra("Type");

        if (iType != null && !iType.isEmpty())
            listDataHeader = db.iCategory(iType);
        else
            listDataHeader = db.iCategory();


        listDataChild = prepareListData(listDataHeader);
        ExpandableListAdapterInstitute listAdapter = new ExpandableListAdapterInstitute(this, listDataHeader, listDataChild);
        list1.setAdapter(listAdapter);


        list1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {


            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent ints = new Intent(getApplicationContext(), institution1.class);

                ints.putExtra("category", listDataHeader.get(groupPosition));
                ints.putExtra("name", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getName());
                ints.putExtra("description", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getDescription());
                ints.putExtra("course", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getCourse());
                ints.putExtra("website", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getWebsite());

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

    private HashMap<String, List<InstitutionModel>> prepareListData(List<String> head) {

        dbhelper db = new dbhelper(this);
        HashMap<String, List<InstitutionModel>> childList = new HashMap<String, List<InstitutionModel>>();

        for (int i = 0; i < head.size(); i++) {
            List<InstitutionModel> l1 = new ArrayList<>();
            if (iType != null)
                l1 = db.getInstitute(head.get(i),iType);
            else
                l1 = db.iName(head.get(i));

            childList.put(listDataHeader.get(i), l1);
        }
        return childList;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.i1:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.i2:
                Toast t = Toast.makeText(this, "Future Update", Toast.LENGTH_SHORT);
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
