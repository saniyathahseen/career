package com.example.user.career;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.career.model.InstitutionModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/11/2018.
 */

public class dbhelper extends SQLiteOpenHelper {
    private static final int DB_version = 1;
    private static final String DBNAME = "dbcareer.db";
    private static String TAB_COURSE = "Course";
    private static String TAB_INSTITUTE = "Institute";
    private static String TAB_SCHOLARSHIPS = "Scholarship";
    private static String TAB_ENTRANCE = "Entrance";
    //course fields
    public static final String C_ID = "_id";
    public static final String C_TYPE = "Type";
    public static final String C_CATEGORY = "Category";
    public static final String C_NAME = "Name";
    public static final String C_DES = "Description";
    public static final String C_ELIGIBILITY = "Eligibility";
    //institute fields
    public static final String I_ID = "_id";
    public static final String I_TYPE = "Type";
    public static final String I_CATEGORY = "Category";
    public static final String I_NAME = "Name";
    public static final String I_DES = "Description";
    public static final String I_COURSE = "Course";
    public static final String I_WEBSITE = "Website";
    //scholarship fields
    public static final String S_ID = "_id";
    public static final String S_NAME = "Name";
    public static final String S_DES = "description";
    public static final String S_ELIGIBILITY = "Eligibility";
    public static final String S_AMOUNT = "Amount";
    public static final String S_WEBSITE = "Website";


    //entrance fields
    public static final String E_ID = "_id";
    public static final String E_CATEGORY = "Category";
    public static final String E_NAME = "Name";
    public static final String E_DESCRIPTION = "Description";
    public static final String E_ELIGIBILITY = "Eligibility";
    public static final String E_FEE = "Amount";
    public static final String E_WEBSITE = "Website";
    private String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/";
    Context mContext;
    public dbhelper(Context c)
    {
        super(c,DBNAME,null,DB_version);
        mContext = c;


        boolean dbExist = checkDatabase();

        if (!dbExist) {
            try
            {
                this.getReadableDatabase();
                copyDatabase();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


        }
    }

/*    public void openDatabase() {// For advance

        try {

            //Open the database
            String dbPath = DB_PATH + DBNAME;
            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);


        } catch (Exception e) {

            e.printStackTrace();
        }
    }*/

    private boolean checkDatabase() {

        boolean checkDb = false;

        try {

            String dbPath = DB_PATH + DBNAME;
            File dbFile = new File(dbPath);
            checkDb = dbFile.exists();
        } catch (SQLiteException e) {

        }

        return checkDb;
    }
   
    @Override
    public void onCreate(SQLiteDatabase db) {
/*
        String CREATE_COURSE = "CREATE TABLE " + TAB_COURSE + "(" + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + C_TYPE  + " TEXT," +C_CATEGORY+ " TEXT," + C_NAME  + " TEXT," +C_DES + " TEXT," + C_ELIGIBILITY + " TEXT" +  ")";
        db.execSQL(CREATE_COURSE);
        String CREATE_INSTITUTE = "CREATE TABLE " + TAB_INSTITUTE + "(" + I_ID + " INTEGER PRIMARY kEY AUTOINCREMENT, " + I_TYPE + " TEXT," + I_CATEGORY + " TEXT," + I_NAME + " TEXT," + I_DES + " TEXT," + I_COURSE + " TEXT," +I_WEBSITE+" TEXT" +")";
        db.execSQL(CREATE_INSTITUTE);
        String CREATE_SCHOLARSHIP = "CREATE TABLE " + TAB_SCHOLARSHIPS + "(" + S_ID + " INTEGER PRIMARY kEY , " + S_NAME + " TEXT, " + S_DES + " TEXT, "+ S_ELIGIBILITY + " TEXT, " + S_AMOUNT + " TEXT, " + S_WEBSITE+" TEXT " + ");";
        db.execSQL(CREATE_SCHOLARSHIP);
        Log.d("XXXXXX",CREATE_SCHOLARSHIP );
        String CREATE_ENTRANCE="CREATE TABLE " + TAB_ENTRANCE+ "(" +E_ID+" INTEGER PRIMARY kEY AUTOINCREMENT, "+E_CATEGORY+" TEXT,"+E_NAME+" TEXT,"+E_DESCRIPTION+" TEXT,"+E_ELIGIBILITY+" TEXT,"+E_FEE+" TEXT,"+E_WEBSITE+" TEXT "+")";
        db.execSQL(CREATE_ENTRANCE);*/
    }

    private void copyDatabase() throws IOException {

        //Open your local db as the input stream
        InputStream dbStreamInput = mContext.getAssets().open(DBNAME);

        // Path to the just created empty db
        String dbFilePath = DB_PATH + DBNAME;

        //Open the empty db as the output stream
        OutputStream dbStreamOutput = new FileOutputStream(dbFilePath);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = dbStreamInput.read(buffer)) > 0) {
            dbStreamOutput.write(buffer, 0, length);
        }

        //Close the streams
        dbStreamOutput.flush();
        dbStreamOutput.close();
        dbStreamInput.close();
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //db.execSQL("DROP TABLE IF EXISTS "+TAB_COURSE);
        //db.execSQL("DROP TABLE IF EXISTS "+TAB_INSTITUTE);
        //db.execSQL("DROP TABLE IF EXISTS "+TAB_SCHOLARSHIPS);
        //db.execSQL("DROP TABLE IF EXISTS "+TAB_ENTRANCE);
        //onCreate(db);
    }

    // insert to all tables
    public int insert(String TABLE_NAME, ContentValues content)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE_NAME,null,content);
        db.close();
        return 0;
    }
    // insert to all tables
    public int update(String TABLE_NAME, ContentValues content)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.update(TABLE_NAME,null,null,null);
        db.close();
        return 0;
    }
    // Query course table
    public Cursor getAllCourses()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_COURSE,new String[] {"_id","name"},null,null,null,null,null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;

    }
    public Cursor getCourses(String id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_COURSE, null,C_ID+"=?",new String[] {String.valueOf(id)},null,null,null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;

    }
    // Query institute table
    public Cursor getAllInstitution()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_INSTITUTE,new String[] {"_id","name"},null,null,null,null,null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;
    }


    public Cursor getInstitution(String id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_INSTITUTE,null,I_ID+"=?",new String[] {id},null,null,null);
        //Cursor result = db.rawQuery("SELECT Description, Eligibility, Amount, Website from Entrance  where _id ='"+id+"'", null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;

    }
    // Query scholarship table
    public Cursor getAllScolarship()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_SCHOLARSHIPS,new String[] {"_id","name"},null,null,null,null,null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;
    }
    public Cursor getScholarship(String ids)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_SCHOLARSHIPS,null,S_ID+"=?",new String[] {ids},null,null,null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;

    }
    // Query entrance table
    public Cursor getAllEntrance()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_ENTRANCE,new String[] {"_id","name"},null,null,null,null,null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;

    }
    public Cursor getEntrance(String id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_ENTRANCE,null,E_ID+"=?",new String[] {id},null,null,null);
        //Cursor result = db.rawQuery("SELECT Description, Eligibility, Amount, Website from Entrance  where _id ='"+id+"'", null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;

    }

    public Cursor sName()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_SCHOLARSHIPS,new String[] {"_id","Name"},null,null,null,null,null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;
    }
    public Cursor iType()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.query(TAB_INSTITUTE,new String[] {"_id","Type"},null,null,I_TYPE,null,null);
        if(result!=null)
            result.moveToFirst();
        db.close();
        return result;
    }
    public List<String>cCategory(String idi)
    {
        ArrayList<String>datalist=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT DISTINCT  Category from Course where Type ='"+idi+"'", null);
        result.moveToFirst();

        if (!result.isAfterLast()) {
            do {
                datalist.add(result.getString(0));
            } while (result.moveToNext());
            result.close();
        }
        return datalist;
    }

    public List<String > cName(String c_CATEGORY,String idi)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<String> datalist=new ArrayList<String>();
        Cursor result = db.rawQuery("SELECT Name from Course where Category='"+c_CATEGORY+"'and Type='"+idi+"'", null);
        result.moveToFirst();
        if (!result.isAfterLast()) {
            do {
                datalist.add(result.getString(0));
            } while (result.moveToNext());
            result.close();
        }
        return datalist;
    }
    public List<String>iCategory()
    {
        ArrayList<String>datalist=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT DISTINCT  Category from Institute ", null);
        result.moveToFirst();

        if (!result.isAfterLast()) {
            do {
                datalist.add(result.getString(0));
            } while (result.moveToNext());
            result.close();
        }
        return datalist;
    }

    /**
     * Select instituet category by type
     * 03042018AH
     * @param i_TYPE
     * @return
     */
    public List<String>iCategory(String i_TYPE)
    {
        ArrayList<String>datalist=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT DISTINCT  Category from Institute where Type='"+i_TYPE+"'", null);
        result.moveToFirst();

        if (!result.isAfterLast()) {
            do {
                datalist.add(result.getString(0));
            } while (result.moveToNext());
            result.close();
        }
        return datalist;
    }

    /**
     * get institute by category/District and type
     * 03042016AH
     * @param i_CATEGORY
     * @param iType
     * @return
     */

    public List<InstitutionModel > getInstitute(String i_CATEGORY,String iType)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<InstitutionModel> datalist=new ArrayList<>();
        Cursor result = db.rawQuery("SELECT * from Institute where Category='"+i_CATEGORY+"' and Type = '"+iType+"'", null);
        result.moveToFirst();
        if (!result.isAfterLast()) {
            do {

                InstitutionModel obj = new InstitutionModel();
                obj.setId(result.getString(0));
                obj.setCategory(result.getString(2));
                obj.setName(result.getString(3));
                obj.setDescription(result.getString(4));
                obj.setCourse(result.getString(5));
                obj.setWebsite(result.getString(6));

                datalist.add(obj);
            } while (result.moveToNext());
            result.close();
        }
        return datalist;
    }

    public List<InstitutionModel > iName(String i_CATEGORY)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<InstitutionModel> datalist=new ArrayList<>();
        Cursor result = db.rawQuery("SELECT * from Institute where Category='"+i_CATEGORY+"'", null);
        result.moveToFirst();
        if (!result.isAfterLast()) {
            do {

                InstitutionModel obj = new InstitutionModel();
                obj.setId(result.getString(0));
                obj.setCategory(result.getString(2));
                obj.setName(result.getString(3));
                obj.setDescription(result.getString(4));
                obj.setCourse(result.getString(5));
                obj.setWebsite(result.getString(6));

                datalist.add(obj);
            } while (result.moveToNext());
            result.close();
        }
        return datalist;
    }
    public List<String>eCategory()
    {
        ArrayList<String>datalist=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT DISTINCT  Category from Entrance ", null);
        result.moveToFirst();

        if (!result.isAfterLast()) {
            do {
                datalist.add(result.getString(0));
            } while (result.moveToNext());
            result.close();
        }
        return datalist;
    }

    public List<String > eName(String e_CATEGORY)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<String> datalist=new ArrayList<String>();
        Cursor result = db.rawQuery("SELECT Name from Entrance where Category='"+e_CATEGORY+"'", null);
        result.moveToFirst();
        if (!result.isAfterLast()) {
            do {
                datalist.add(result.getString(0));
            } while (result.moveToNext());
            result.close();
        }
        return datalist;
    }

    public int getEntranceId(String catname)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        int id =-1;
        Cursor result = db.rawQuery("SELECT " + E_ID + " from Entrance where Name ='"+catname+"'", null);
        result.moveToFirst();
        if (!result.isAfterLast()) {
          id = result.getInt(0);
        }
        result.close();
        return id;
    }
    public int getCourseId(String catname)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        int id =-1;
        Cursor result = db.rawQuery("SELECT " + C_ID + " from Course where Name ='"+catname+"'", null);
        result.moveToFirst();
        if (!result.isAfterLast()) {
            id = result.getInt(0);
        }
        result.close();
        return id;
    }
    public int getInstitutionId(String catname)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        int id =-1;
        Cursor result = db.rawQuery("SELECT " + I_ID + " from Institute where Name ='"+catname+"'", null);
        result.moveToFirst();
        if (!result.isAfterLast()) {
            id = result.getInt(0);
        }
        result.close();
        return id;
    }




}

