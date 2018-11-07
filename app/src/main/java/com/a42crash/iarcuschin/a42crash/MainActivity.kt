package com.a42crash.iarcuschin.a42crash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    @BindView(R.id.editText)
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)
    }

    @OnClick(R.id.button)
    fun onButtonClick() {
        val text = editText.text.toString()
//        if (text.equals("42")) {
            throw Exception("We found the answer!")
//        } else {
//            Toast.makeText(this, "¿\"" + text + "\"?", Toast.LENGTH_SHORT).show();
//        }
    }
}
