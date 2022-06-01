package com.example.wbinternw5part2.view.hero

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbinternw5part2.R
import com.example.wbinternw5part2.databinding.FragmentHeroBinding
import com.example.wbinternw5part2.model.data.AppState
import com.example.wbinternw5part2.model.data.DataModel
import com.example.wbinternw5part2.view.details.DetailsFragment
import com.example.wbinternw5part2.view.hero.adapter.HeroAdapter
import com.example.wbinternw5part2.viewmodel.HeroViewModel

class HeroFragment : Fragment() {

    private var _binding: FragmentHeroBinding? = null
    private val binding get() = _binding!!

    private val adapter: HeroAdapter by lazy { HeroAdapter(onListItemClickListener) }

    private val viewModel: HeroViewModel by lazy {
        ViewModelProvider(this)[HeroViewModel::class.java]
    }

    private val onListItemClickListener: HeroAdapter.OnHeroListItemClickListener =
        object : HeroAdapter.OnHeroListItemClickListener {
            override fun onItemClick(dataModel: DataModel.Results) {
                parentFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.container, DetailsFragment.newInstance(dataModel))
                    .commit()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
    }

    private fun initView() {
        binding.heroRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.heroRecyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.liveDataForViewToObserve.observe(viewLifecycleOwner) { renderData(it) }

        val name = arguments?.getString(KEY)
        name?.let { viewModel.getData(it) }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(context, appState.error.message, Toast.LENGTH_SHORT).show()
                Log.d("TAG", appState.error.message.toString())
            }
            is AppState.Loading -> {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                appState.dataModel?.let {
                    if (it.results.isEmpty())
                        Toast.makeText(context, "No data available", Toast.LENGTH_SHORT).show()
                    else
                        adapter.setData(it.results)
                }
            }
        }
    }

    companion object {
        private const val KEY = "name"

        fun newInstance(name: String) = HeroFragment().apply {
            arguments = Bundle().apply {
                putString(KEY, name)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}