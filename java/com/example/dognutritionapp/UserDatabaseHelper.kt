package com.example.dognutritionapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_EMAIL = "email" // New email column
        private const val COLUMN_PHONE = "phone" // New phone column
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_USERNAME TEXT UNIQUE,"
                + "$COLUMN_PASSWORD TEXT,"
                + "$COLUMN_EMAIL TEXT,"
                + "$COLUMN_PHONE TEXT)") // Add email and phone columns
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun insertUser(user: User): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_USERNAME, user.username)
        contentValues.put(COLUMN_PASSWORD, user.password)
        contentValues.put(COLUMN_EMAIL, user.email) // Insert email
        contentValues.put(COLUMN_PHONE, user.phone) // Insert phone
        return db.insert(TABLE_USERS, null, contentValues)
    }

    @SuppressLint("Range")
    fun getUser(username: String): User? {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_USERS,
            arrayOf(COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_EMAIL, COLUMN_PHONE), // Fetch email and phone
            "$COLUMN_USERNAME = ?",
            arrayOf(username), null, null, null)

        return if (cursor.moveToFirst()) {
            val user = User(
                cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)),
                cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)), // Get email
                cursor.getString(cursor.getColumnIndex(COLUMN_PHONE))  // Get phone
            )
            cursor.close()
            user
        } else {
            cursor.close()
            null
        }
    }
}
