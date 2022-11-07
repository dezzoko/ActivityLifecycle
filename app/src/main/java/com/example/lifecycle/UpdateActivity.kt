package com.example.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifecycle.MainActivity.Companion.KEY_RESULT
import com.example.lifecycle.databinding.ActivityMain2Binding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMain2Binding.inflate(layoutInflater)
            setContentView(binding.root)


        binding.updateButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra(KEY_RESULT,10)
            setResult(RESULT_OK,intent)
            finish()
        }
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}