package com.example.wbinternw5part2.view.hero.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wbinternw5part2.R
import com.example.wbinternw5part2.model.data.DataModel
import com.squareup.picasso.Picasso

class HeroAdapter(
    private val onListItemClickListener: OnHeroListItemClickListener
) : RecyclerView.Adapter<HeroAdapter.RecyclerItemViewHolder>() {

    private var data: ArrayList<DataModel.Results> = arrayListOf()

    fun setData(newData: List<DataModel.Results>) {
        val diffUtilsCallBack = DiffUtilsCallBack(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffUtilsCallBack)
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.hero_recycler_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel.Results) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.name).text = data.biography.fullName

                Picasso.with(itemView.context)
                    .load(data.image.url)
                    .error(R.drawable.ic_baseline_no_photography_24)
                    .into(itemView.findViewById<ImageView>(R.id.image))

                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }

        private fun openInNewWindow(dataModel: DataModel.Results) {
            onListItemClickListener.onItemClick(dataModel)
        }
    }

    interface OnHeroListItemClickListener {
        fun onItemClick(dataModel: DataModel.Results)
    }
}