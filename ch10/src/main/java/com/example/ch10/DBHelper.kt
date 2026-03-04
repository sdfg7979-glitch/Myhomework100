package com.example.ch10

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "UserDB", null, 1) {

    // 데이터베이스 생성 시 테이블 정의
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE User (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                email TEXT,
                gender TEXT
            )
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS User")
        onCreate(db)
    }

    // 데이터를 DB에 실제로 저장하는 함수
    fun insertUser(user: User): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("name", user.name)
            put("email", user.email)
            put("gender", user.gender)
        }

        val result = db.insert("User", null, contentValues)
        db.close()
        return result
    }
}