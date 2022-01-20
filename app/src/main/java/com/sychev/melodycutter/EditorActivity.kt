package com.sychev.melodycutter

import android.media.MediaExtractor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText as makeToastText

class EditorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val strUri  = intent.getStringExtra("uri")

        val extractor = MediaExtractor()
        if (strUri != null) {
            extractor.setDataSource(strUri)
        }
        Log.d("EDITOR", "Count tracks is ${extractor.trackCount}")

        makeToastText(this, strUri, Toast.LENGTH_LONG).show()
    }
}