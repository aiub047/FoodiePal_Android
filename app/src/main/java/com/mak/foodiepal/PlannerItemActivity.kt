package com.mak.foodiepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mak.foodiepal.databinding.ActivityPlannerItemBinding

class PlannerItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlannerItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlannerItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivDeleteIcon.setOnClickListener {
            Log.i("AIUB", binding.ivDeleteIcon.tag.toString())
        }
    }
}