package com.example.wbinternw5part2.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wbinternw5part2.R
import com.example.wbinternw5part2.databinding.FragmentDetailsBinding
import com.example.wbinternw5part2.model.data.DataModel
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderData()
    }

    private fun renderData() {
        val data = arguments?.getParcelable<DataModel.Results>(KEY)

        with(binding) {
            data?.let {
                Picasso
                    .with(context)
                    .load(data.image.url)
                    .error(R.drawable.ic_baseline_no_photography_24)
                    .into(binding.image)

                fullName.text = data.biography.fullName
                name.text = data.name
                intelligence.text = data.powerStats.intelligence
                speed.text = data.powerStats.speed
                power.text = data.powerStats.power
            }
        }
    }

    companion object {
        private const val KEY = "details"

        fun newInstance(data: DataModel.Results) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY, data)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}