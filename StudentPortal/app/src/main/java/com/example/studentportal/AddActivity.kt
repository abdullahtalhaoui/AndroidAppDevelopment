package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add.toolbar
import kotlinx.android.synthetic.main.content_add.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }
    private fun initViews() {
        confButton.setOnClickListener { onSaveClick() }
    }

    private fun onSaveClick() {
        if (etAddTitle.text.toString().isNotBlank()) {
            val title = Portal(etAddTitle.text.toString(), etAddUrl.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL, title)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this,"All fields must me filled in"
                , Toast.LENGTH_SHORT).show()
        }
    }

}