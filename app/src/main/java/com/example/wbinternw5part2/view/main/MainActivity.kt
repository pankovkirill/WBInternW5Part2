package com.example.wbinternw5part2.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbinternw5part2.R
import com.example.wbinternw5part2.databinding.ActivityMainBinding
import com.example.wbinternw5part2.model.data.NAMES
import com.example.wbinternw5part2.view.hero.HeroFragment
import com.example.wbinternw5part2.view.main.adapter.MainAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(name: String) {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.container, HeroFragment.newInstance(name))
                    .commit()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainActivityRecyclerView.adapter = adapter
        adapter.setData(NAMES)
    }
}