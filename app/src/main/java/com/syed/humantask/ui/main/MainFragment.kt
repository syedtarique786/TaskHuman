package com.syed.humantask.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.syed.humantask.databinding.FragmentMainBinding
import com.syed.humantask.retrofit.ApiServices
import com.syed.humantask.retrofit.Repository


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val TAG = MainFragment::class.java.name
    private lateinit var factory: SkillViewModelFactory
    private var viewModel: SkillViewModel? = null
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated called ")

        val api = ApiServices()
        factory = SkillViewModelFactory(Repository(api))
        viewModel = ViewModelProviders.of(this, factory)[SkillViewModel::class.java]

        viewModel?.getAllSkill()

        viewModel?.skillList?.observe(viewLifecycleOwner, Observer { skillsList ->
            skillsList?.isNotEmpty().let {
                Log.d(TAG, "skillsList $skillsList")
                binding.rvSkillList.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = SkillsAdapter(skillsList)
                }
            }
        })
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel?.let {
            Log.d(TAG, "onSaveInstanceState called $it")
        }.also {
            Log.d(TAG, "onSaveInstanceState called null")
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "onViewStateRestored called ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called ")
    }


    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called ")
    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called ")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called ")
    }

}