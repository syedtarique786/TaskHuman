package com.syed.humantask.ui.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.syed.humantask.R
import com.syed.humantask.databinding.ItemProviderInfoBinding
import com.syed.humantask.model.ProviderInfoX


class ProviderAdapter(val baseContext: Context, val providerInfo: List<ProviderInfoX>?) :
    RecyclerView.Adapter<ProviderAdapter.SkillsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val itemBinding =
            ItemProviderInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SkillsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        val providerInfo: ProviderInfoX? = providerInfo?.get(position)
        providerInfo?.let {
            holder.bind(it, baseContext)
        }
    }


    override fun getItemCount(): Int {
        Log.d("ProviderAdapter", "List size ${providerInfo?.size ?: 0}")
        return providerInfo?.size ?: 0
    }


    class SkillsViewHolder(private val itemBinding: ItemProviderInfoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(skillItem: ProviderInfoX, baseContext: Context) {
            skillItem.profileImage?.isNotEmpty().let {
                with(itemBinding.indicator) {
                    val requestOptions = RequestOptions()
                        .override(600, 200)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .fitCenter()

                    Glide.with(baseContext)
                        .asBitmap()
                        .apply(requestOptions)
                        .load(skillItem.profileImage)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_background)
                        .into(this@with)
                }
            }

        }
    }
}