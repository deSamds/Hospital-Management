package com.example.hospital_management;

import android.provider.BaseColumns;

public class UserMaster {

    private UserMaster(){}

    public static class Doctors implements BaseColumns {
        public static final String TABLE_NAME = "doctors";
        public static final String doctor_ID = "id";
        public static final String doctor_name = "name";
        public static final String doctor_age = "age";
        public static final String doctor_designation = "designation";
        public static final String doctor_address = "address";
        public static final String doctor_phone = "phone";
        public static final String doctor_ward = "ward";
    }
}
