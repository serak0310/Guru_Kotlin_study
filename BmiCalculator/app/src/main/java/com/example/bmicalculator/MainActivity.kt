package com.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var resultButton : Button
    lateinit var heightEditText : EditText
    lateinit var weightEditText: EditText
    lateinit var nameEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultButton = findViewById(R.id.resultButton)
        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        nameEditText = findViewById(R.id.nameEditText)

        loadData()

        resultButton.setOnClickListener{
            saveData(nameEditText.text.toString(), heightEditText.text.toString().toInt(), weightEditText.text.toString().toInt())

            var intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("name", nameEditText.text.toString())
            intent.putExtra("height", heightEditText.text.toString())
            intent.putExtra("weight", weightEditText.text.toString())

            startActivity(intent)

        }
    }

    //데이터 저장 메소드
    private fun saveData(name:String, height: Int, weight: Int){
        var pref = this.getPreferences(0)
        var editor = pref.edit()

        editor.putString("KEY_NAME", nameEditText.text.toString()).apply()
        editor.putInt("KEY_HEIGHT", heightEditText.text.toString().toInt()).apply()
        editor.putInt("KEY_WEIGHT", weightEditText.text.toString().toInt()).apply()
    }

    //데이터 불러오기 메소드
    private fun loadData(){
        var pref = this.getPreferences(0)

        var name = pref.getString("KEY_NAME", null)
        var height = pref.getInt("KEY_HEIGHT", 0)
        var weight = pref.getInt("KEY_WEIGHT", 0)

        if(name!=null && height != 0 && weight !=0){
            nameEditText.setText(name.toString())
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}

