package com.example.hospital_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME="hospital_database";
    //private static final int DATABASE_VERSION = 4;


    private static final String TABLE_DRUGS = "drugs";
    private static final String KEY_DRUGID = "id";
    private static final String KEY_DRUGNAME = "name";
    private static final String KEY_MANUFACTURER = "manufacturer";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DESCRIPTION = "description";

    private static final String CREATE_TABLE_DRUGS = "CREATE TABLE " + TABLE_DRUGS + "(" + KEY_DRUGID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DRUGNAME + " TEXT, "+ KEY_MANUFACTURER + " TEXT, "+ KEY_QUANTITY + " TEXT, "+ KEY_PRICE + " TEXT, "+ KEY_DESCRIPTION + " TEXT );";//   private static final String CREATE_TABLE_DOCTORS = "CREATE TABLE " + UserMaster.Doctors.TABLE_NAME + "(" +UserMaster.Doctors.doctor_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + UserMaster.Doctors.doctor_name + " TEXT,"+ UserMaster.Doctors.doctor_age + "TEXT," +UserMaster.Doctors.doctor_designation+ " TEXT," +UserMaster.Doctors.doctor_address+ "TEXT," +UserMaster.Doctors.doctor_phone+ "TEXT," +UserMaster.Doctors.doctor_ward + "TEXT)";
    private static final String CREATE_TABLE_DOCTORS =  "CREATE TABLE " + UserMaster.Doctors.TABLE_NAME+ "(ID INTEGER PRIMARY KEY, name TEXT, age TEXT, designation TEXT, address TEXT, phone TEXT, ward TEXT)";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DRUGS);
        db.execSQL(CREATE_TABLE_DOCTORS);
    }

    public Boolean addDrugsDetail(String name, String manufacturer, String quantity, String price, String description) {
        SQLiteDatabase db = getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_DRUGNAME, name);
        values.put(KEY_MANUFACTURER, manufacturer);
        values.put(KEY_QUANTITY, quantity);
        values.put(KEY_PRICE, price);
        values.put(KEY_DESCRIPTION, description);
        // insert row in drugs table
        long insert = db.insert(TABLE_DRUGS, null, values);

        if(insert>=1){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<DrugModel> getAllDrugs() {
        // ArrayList<DrugsModel> drugsModelArrayList = new ArrayList<DrugsModel>();
        ArrayList<DrugModel> drugsModelArrayList = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_DRUGS,null);



//        String selectQuery = "SELECT  * FROM " + TABLE_DRUGS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            drugsModelArrayList = new ArrayList<DrugModel>();
            do {
                DrugModel drugsModel = new DrugModel();
                drugsModel.setId(c.getInt(c.getColumnIndex(KEY_DRUGID)));
                drugsModel.setName(c.getString(c.getColumnIndex(KEY_DRUGNAME)));
                drugsModel.setManufacturer(c.getString(c.getColumnIndex(KEY_MANUFACTURER)));
                drugsModel.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));
                drugsModel.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                drugsModel.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                // adding to drugs list
                drugsModelArrayList.add(drugsModel);
            } while (c.moveToNext());
        }
        return drugsModelArrayList;


    }
    public ArrayList<DrugModel> getDrug(String id){

        ArrayList<DrugModel> drugArray = null;
        SQLiteDatabase db=getReadableDatabase();
        String[] args={id};
        Cursor c=db.rawQuery("SELECT * FROM "+TABLE_DRUGS+" WHERE "+KEY_DRUGID+" = ?",args);
        if(c.moveToFirst()){
            drugArray=new ArrayList<DrugModel>();
            do{
                DrugModel drugMod=new DrugModel();
                drugMod.setId(c.getInt(c.getColumnIndex(KEY_DRUGID)));
                drugMod.setName(c.getString(c.getColumnIndex(KEY_DRUGNAME)));
                drugMod.setManufacturer(c.getString(c.getColumnIndex(KEY_MANUFACTURER)));
                drugMod.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));
                drugMod.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                drugMod.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                drugArray.add(drugMod);

            }while (c.moveToNext());
        }
        return drugArray;

    }

    public Boolean updateDrugs(int id,String name, String manufacturer,String quantity,String price,String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(this.KEY_DRUGNAME, name);
        values.put(KEY_MANUFACTURER,manufacturer);
        values.put(KEY_QUANTITY,quantity);
        values.put(KEY_PRICE,price);
        values.put(KEY_DESCRIPTION,description);
        // update row in drugs table base on drugs.is value
        //String args=Integer.toString(id);
        int row=db.update(TABLE_DRUGS, values,KEY_DRUGID + " = ?",new String[]{String.valueOf(id)});
        if(row>=1){
            return true;
        }else {
            return false;
        }
    }


    public Boolean deleteDrug(int id) {

        // delete row in drugs table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        int row=db.delete(TABLE_DRUGS, KEY_DRUGID + " = ?",
                new String[]{String.valueOf(id)});

        if(row>=1){
            return true;
        }else {
            return false;
        }

    }


    public ArrayList<DrugModel> searchDrugs(String drug) {
        // ArrayList<DrugsModel> drugsModelArrayList = new ArrayList<DrugsModel>();
        ArrayList<DrugModel> drugsModels = null;

        try {

            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_DRUGS + " WHERE " + KEY_DRUGNAME + " LIKE ?", new String[] { "%" + drug + "%" });


//        String selectQuery = "SELECT  * FROM " + TABLE_DRUGS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (c.moveToFirst()) {
                drugsModels = new ArrayList<DrugModel>();
                do {
                    DrugModel drugsModel = new DrugModel();
                    drugsModel.setId(c.getInt(c.getColumnIndex(KEY_DRUGID)));
                    drugsModel.setName(c.getString(c.getColumnIndex(KEY_DRUGNAME)));
                    drugsModel.setManufacturer(c.getString(c.getColumnIndex(KEY_MANUFACTURER)));
                    drugsModel.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));
                    drugsModel.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                    drugsModel.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                    // adding to drugs list
                    drugsModels.add(drugsModel);
                } while (c.moveToNext());
            }
        }catch(Exception e) {
            drugsModels = null;
        }
        return drugsModels;
    }

    //------------------------------------------------------------------------------
    //Doctor Management ==================

    public ArrayList<Doctors> getdoctorInfo() {

        ArrayList<Doctors> doctorsArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + UserMaster.Doctors.TABLE_NAME,null);

        if(data.moveToFirst()) {
            do{
                Doctors doctors = new Doctors();
                doctors.setId(data.getInt(0) + "");
                doctors.setName(data.getString(1));
                doctors.setAge(data.getString(2));
                doctors.setDesignation(data.getString(3));
                doctors.setAddress(data.getString(4));
                doctors.setPhone(data.getString(5));
                doctors.setWard(data.getString(6));

                doctorsArrayList.add(doctors);
            }while (data.moveToNext());
        }
        return doctorsArrayList;
    }


    public boolean addData(String name, String age, String desig, String adrs, String tp, String ward) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserMaster.Doctors.doctor_name,name);
        values.put(UserMaster.Doctors.doctor_age,age);
        values.put(UserMaster.Doctors.doctor_designation,desig);
        values.put(UserMaster.Doctors.doctor_address,adrs);
        values.put(UserMaster.Doctors.doctor_phone,tp);
        values.put(UserMaster.Doctors.doctor_ward,ward);

        long rowID = db.insert(UserMaster.Doctors.TABLE_NAME,null,values);

        if(rowID == -1) {
            return false;
        }else{
            return true;
        }
    }

    public boolean UpdateDoctor(int id,String name, String age, String desig, String adrs, String tp, String ward) {

        ContentValues values = new ContentValues();

        values.put(UserMaster.Doctors.doctor_name,name);
        values.put(UserMaster.Doctors.doctor_age,age);
        values.put(UserMaster.Doctors.doctor_designation,desig);
        values.put(UserMaster.Doctors.doctor_address,adrs);
        values.put(UserMaster.Doctors.doctor_phone,tp);
        values.put(UserMaster.Doctors.doctor_ward,ward);

        SQLiteDatabase db = this.getWritableDatabase();

        int rowID = db.update(UserMaster.Doctors.TABLE_NAME,values, UserMaster.Doctors.doctor_ID +" = " + id,null);
        db.close();
        if(rowID == -1) {
            return  false;
        }else
            return true;
    }

    public boolean DeleteDoctor(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int rowID = db.delete(UserMaster.Doctors.TABLE_NAME, UserMaster.Doctors.doctor_ID + " = " + id,null);
        db.close();
        if(rowID == -1) {
            return  false;
        }else
            return true;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int i1) {

    }

}
