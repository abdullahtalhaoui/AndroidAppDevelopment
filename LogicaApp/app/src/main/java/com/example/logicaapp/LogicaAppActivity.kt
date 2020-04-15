package com.example.logicaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_logica. *

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logica)
        btnSubmit.setOnClickListener{
        checkAnswer()
        }
    }
    private fun checkAnswer() {
        val answer1 = etAnswer1.text.toString()
        val answer2 = etAnswer2.text.toString()
        val answer3 = etAnswer3.text.toString()
        val answer4 = etAnswer4.text.toString()
        var correctAnswers=0

        if (answer1 == getString(R.string.True))(
                ++correctAnswers
                )
        if (answer2 == getString(R.string.False))(
                ++correctAnswers
                )
        if (answer3 == getString(R.string.False))(
                ++correctAnswers
                )
        if (answer4 == getString(R.string.False))(
                ++correctAnswers
                )
        Toast.makeText(this, getString(R.string.CorrectText) + " "+correctAnswers, Toast.LENGTH_LONG).show()
    }
}
