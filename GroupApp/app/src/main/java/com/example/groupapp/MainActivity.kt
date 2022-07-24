package com.example.groupapp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var myHelper: myDBHelper
    lateinit var edtName: EditText
    lateinit var edtNumber: EditText
    lateinit var edtNameResult: EditText
    lateinit var edtNumberResult: EditText
    lateinit var btnInit: Button
    lateinit var btnInsert: Button
    lateinit var btnSelect: Button
    lateinit var btnDelete: Button
    lateinit var btnModify: Button
    lateinit var sqlDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById<EditText>(R.id.edtName)
        edtNumber = findViewById<EditText>(R.id.edtNumber)
        edtNameResult = findViewById<EditText>(R.id.edtNameResult)
        edtNumberResult = findViewById<EditText>(R.id.edtNumberResult)
        btnInit = findViewById<Button>(R.id.btnInit)
        btnInsert = findViewById<Button>(R.id.btnInsert)
        btnSelect = findViewById<Button>(R.id.btnSelect)
        btnDelete = findViewById<Button>(R.id.btnDelete)
        btnModify = findViewById<Button>(R.id.btnModify)

        myHelper = myDBHelper(this)

        // 초기화
        btnInit.setOnClickListener{
            sqlDB = myHelper.writableDatabase
            myHelper.onUpgrade(sqlDB, 1, 2)
            sqlDB.close()

            btnSelect.callOnClick()
        }

        // 입력
        btnInsert.setOnClickListener{
            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL("INSERT INTO groupTBL VALUES ( '"
                    + edtName.text.toString() + "' , "
                    + edtNumber.text.toString() + ");")
            sqlDB.close()
            Toast.makeText(applicationContext, "입력됨", Toast.LENGTH_SHORT).show()

            btnSelect.callOnClick()
        }

        // 조회
        btnSelect.setOnClickListener {
            sqlDB = myHelper.readableDatabase
            var cursor: Cursor
            cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null)
            var strNames = "그룹이름" + "\r\n" + "--------" + "\r\n"
            var strNumbers = "인원" + "\r\n" + "--------" + "\r\n"
            while (cursor.moveToNext()) {
                strNames += cursor.getString(0) + "\r\n"
                strNumbers += cursor.getString(1) + "\r\n"
            }
            edtNameResult.setText(strNames)
            edtNumberResult.setText(strNumbers)
            cursor.close()
            sqlDB.close()
        }

        // 삭제
        btnDelete.setOnClickListener {

        }

        // 수정
        btnModify.setOnClickListener {

        }
    }


    inner class myDBHelper(context: Context) : SQLiteOpenHelper (context, "groupDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE groupTBL ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER);")
        }
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS groupTBL")
            onCreate(db)
        }
    }

}