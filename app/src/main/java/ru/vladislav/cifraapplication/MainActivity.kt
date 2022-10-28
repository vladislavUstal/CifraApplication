package ru.vladislav.cifraapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.vladislav.cifraapplication.databinding.ActivityMainBinding
import ru.vladislav.cifraapplication.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, MainFragment.newInstance())
                .commitNow()
        }
    }

}