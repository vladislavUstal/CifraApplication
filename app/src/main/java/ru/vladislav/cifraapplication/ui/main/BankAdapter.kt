package ru.vladislav.cifraapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.vladislav.cifraapplication.data.model.Bank
import ru.vladislav.cifraapplication.databinding.ItemBankBinding

class BankAdapter(private var bankList: List<Bank>) :
    RecyclerView.Adapter<BankAdapter.ViewHolder>() {

    fun refresh(_bankList: List<Bank>) {
        val tourDiffUtilCallback = DiffCallback(this.bankList, _bankList)
        val tourDiffResult = DiffUtil.calculateDiff(tourDiffUtilCallback)
        this.bankList = _bankList
        tourDiffResult.dispatchUpdatesTo(this)
    }

    abstract class ViewHolder(
        binding: ViewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(item: Bank)
    }

    class MainViewHolder(
        private val binding: ItemBankBinding,
    ) : ViewHolder(binding) {

        override fun bind(item: Bank) = with(binding) {
            tvBankName.text = item.bank
        }

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBankBinding.inflate(layoutInflater, parent, false)
                return MainViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MainViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bankList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return bankList.size
    }

    class DiffCallback(
        private var oldList: List<Bank> = listOf(),
        private var newList: List<Bank> = listOf(),
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].bank == newList[newItemPosition].bank
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (
                    oldList[oldItemPosition].bank == newList[newItemPosition].bank &&
                            oldList[oldItemPosition].bankName == newList[newItemPosition].bankName
                    )
        }

    }

}