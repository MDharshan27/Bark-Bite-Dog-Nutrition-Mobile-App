package com.example.dognutritionapp

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "checkout.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "checkout_details"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_MOBILE = "mobile"
        const val COLUMN_PROVINCE = "province"
        const val COLUMN_DISTRICT = "district"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_ADDRESS TEXT,
                $COLUMN_MOBILE TEXT,
                $COLUMN_PROVINCE TEXT,
                $COLUMN_DISTRICT TEXT
            )
        """
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert data into the database
    fun insertCheckoutData(name: String, email: String, address: String, mobile: String, province: String, district: String) {
        val db = writableDatabase
        val insertQuery = """
            INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_EMAIL, $COLUMN_ADDRESS, $COLUMN_MOBILE, $COLUMN_PROVINCE, $COLUMN_DISTRICT)
            VALUES ('$name', '$email', '$address', '$mobile', '$province', '$district')
        """
        db.execSQL(insertQuery)
        db.close()
    }

    // Retrieve saved data from the database
    @SuppressLint("Range")
    fun getCheckoutData(): Map<String, String> {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ID DESC LIMIT 1"
        val cursor = db.rawQuery(query, null)

        val data = mutableMapOf<String, String>()
        if (cursor.moveToFirst()) {
            data["name"] = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            data["email"] = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
            data["address"] = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS))
            data["mobile"] = cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE))
            data["province"] = cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE))
            data["district"] = cursor.getString(cursor.getColumnIndex(COLUMN_DISTRICT))
        }
        cursor.close()
        db.close()
        return data
    }
}
