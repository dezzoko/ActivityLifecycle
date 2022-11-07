package com.example.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lifecycle.databinding.ActivityMainBinding
import kotlinx.android.parcel.Parcelize

class MainActivity : AppCompatActivity() {
    private lateinit var state: State
    private var launcher: ActivityResultLauncher<Intent>? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        state = if (savedInstanceState == null) State(value = 0)
        else savedInstanceState.getParcelable(KEY_STATE)!!

        updateUi()

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    state.value += result.data?.getIntExtra(KEY_RESULT, 0)!!
                    updateUi()


                }
            }
        binding.GoActivityTwo.setOnClickListener {
            launcher?.launch(Intent(this, UpdateActivity::class.java))

        }

    }

    private fun updateUi() {
        binding.counter.text = state.value.toString()
        binding.cardView.radius = state.value.toFloat()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)

    }

    @Parcelize
    class State(
        var value: Int,
    ) : Parcelable

    companion object {
        const val KEY_STATE = "OK"
        const val KEY_RESULT = "OK"
    }
}





