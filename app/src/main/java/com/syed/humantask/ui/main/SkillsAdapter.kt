package com.syed.humantask.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syed.humantask.R
import com.syed.humantask.databinding.ItemSkillListBinding
import com.syed.humantask.model.Skill
import java.text.SimpleDateFormat
import java.util.*


class SkillsAdapter(private val baseContext: Context, private var skillsList: MutableList<Skill>) :
    RecyclerView.Adapter<SkillsAdapter.SkillsViewHolder>() {

    private var context: Context = baseContext

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val itemBinding =
            ItemSkillListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SkillsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        val skillItem: Skill = skillsList[position]
        holder.bind(skillItem, context)
    }


    override fun getItemCount(): Int = skillsList.size

    fun updateList(updatedList: MutableList<Skill>?) {
        updatedList?.let {
            skillsList.clear()
            skillsList.addAll(it)
        }
    }


    class SkillsViewHolder(private val itemBinding: ItemSkillListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(skillItem: Skill, context: Context) {
            itemBinding.tvSkillTitle.text = skillItem.tileName
            val dateString: String? = convertLongToTime(skillItem.availability?.startTime ?: 0)
            itemBinding.tvEventTime.text = dateString ?: ""
            //update availability, as per providerStatus: 0-green, 1-yellow, 2-grey)
            val resourceId = when (skillItem.availability?.status) {
                0 -> {
                    R.drawable.drawable_circle_green
                }
                1 -> {
                    R.drawable.drawable_circle_yellow
                }
                2 -> {
                    R.drawable.drawable_circle_grey
                }
                else -> {
                    R.drawable.drawable_circle_green
                }
            }
            Glide.with(context)
                .load(resourceId)
                .into(itemBinding.ivAvailability)

            skillItem.providerInfo.let { providerInfo ->
                itemBinding.rvProviderList.also {
                    val layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    it.layoutManager = layoutManager
                    //it.addItemDecoration(OverlapDecoration())
                    it.setHasFixedSize(true)
                    it.adapter = ProviderAdapter(context, providerInfo)
                }
            }
        }

        private fun convertLongToTime(time: Long): String? {
            val date = Date(time)
            val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
            return format.format(date)
        }
    }
}