package com.example.wbinternw5part2.view.hero.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.wbinternw5part2.model.data.DataModel

class DiffUtilsCallBack(
    private val oldList: List<DataModel.Results>,
    private val newList: List<DataModel.Results>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == oldList[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}