package com.example.alestereposteria.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alestereposteria.R
import com.example.alestereposteria.databinding.CardViewItemPurchaseBinding
import com.example.alestereposteria.sever.Purchase
import com.squareup.picasso.Picasso

class PurchaseAdapter(
    private val purchaseList: ArrayList<Purchase>,
    private val onItemClicked: (Purchase) -> Unit
): RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PurchaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_purchase, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = purchaseList[position]
        holder.bind(purchase)
        holder.itemView.setOnClickListener { onItemClicked(purchaseList[position]) }
    }

    override fun getItemCount(): Int = purchaseList.size

    fun appendItems(newList: ArrayList<Purchase>) {
        purchaseList.clear()
        purchaseList.addAll(newList)
        notifyDataSetChanged()
    }

    class PurchaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = CardViewItemPurchaseBinding.bind(itemView)
        private val context = binding.root
        fun bind(purchase: Purchase) {
            with(binding) {
                userTitleTextView.text = purchase.product
                productExampleTextView.text = purchase.cakefilling
                deliveryDateLabelTextView.text = purchase.purchase_date
                //     Glide.with(context).load(book.urlPicture).into(pictureBookImageView)
                //Picasso.get().load(purchase.urlPicture).into(posterImageView)
            }
        }
    }

}