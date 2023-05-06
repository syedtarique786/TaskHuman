package com.syed.humantask.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syed.humantask.databinding.ItemSkillListBinding
import com.syed.humantask.model.Skill

class SkillsAdapter(private val skillsList: List<Skill>) :
    RecyclerView.Adapter<SkillsAdapter.SkillsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val itemBinding =
            ItemSkillListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SkillsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        val paymentBean: Skill = skillsList[position]
        holder.bind(paymentBean)
    }


    override fun getItemCount(): Int = skillsList.size


    /*inner class SkillsViewHolder(
        val skillItemViewBinding: ItemSkillListBinding
    ) : RecyclerView.ViewHolder(skillItemViewBinding.root)
*/
    class SkillsViewHolder(private val itemBinding: ItemSkillListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(skillItem: Skill) {
            itemBinding.tvSkillTitle.text = skillItem.tileName

        }
    }
}