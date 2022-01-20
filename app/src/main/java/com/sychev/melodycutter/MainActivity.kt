package com.sychev.melodycutter

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.sychev.melodycutter.databinding.ActivityMainBinding
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import java.nio.channels.InterruptedByTimeoutException
import android.provider.MediaStore
import androidx.loader.content.CursorLoader
import com.sychev.melodycutter.FileUtils.getPath


class MainActivity : AppCompatActivity() {

    private lateinit var m_fileName: Uri
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            m_fileName = uri
            val intent =  Intent(this@MainActivity, EditorActivity::class.java)
            Log.d("MAIN", "Put uri $uri")
            intent.putExtra("uri", FileUtils.getPath(baseContext, uri))
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open -> {
                callOpenFileDialog()
                true
            }
            R.id.action_save -> {
                // TODO: add save file action
                Toast.makeText(this, "You select save item", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_crop -> {
                // TODO: check selection and crop it
                Toast.makeText(this, "You select crop item", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun callOpenFileDialog() {

        getContent.launch("audio/*")
    }

}