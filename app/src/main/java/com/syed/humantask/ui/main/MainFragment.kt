package com.syed.humantask.ui.main

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syed.humantask.R
import com.syed.humantask.databinding.FragmentMainBinding
import com.syed.humantask.model.RequestBean
import com.syed.humantask.model.Skill
import com.syed.humantask.retrofit.ApiServices
import com.syed.humantask.retrofit.Repository
import com.syed.humantask.utils.ItemDecorator
import kotlinx.coroutines.runBlocking


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val TAG = MainFragment::class.java.name
    private lateinit var factory: SkillViewModelFactory
    private var viewModel: SkillViewModel? = null
    lateinit var binding: FragmentMainBinding
    private var skillAdapter: SkillsAdapter? = null

    // The list to populate in the adapter
    private lateinit var skillsList: MutableList<Skill>

    // The class to detect swipe so we can draw the ItemDecoration
    private lateinit var itemTouchHelper: ItemTouchHelper

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
        skillsList = arrayListOf()

        observeData(viewModel)
    }


    /**
     * Observing Data changes from ViewModel and taking action accordingly
     * */
    private fun observeData(viewModel: SkillViewModel?) {
        viewModel?.skillList?.observe(viewLifecycleOwner, Observer { list ->
            list?.isNotEmpty().let {
                skillsList.addAll(list)
                binding.rvSkillList.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    skillAdapter = SkillsAdapter(it.context as Context, skillsList)
                    it.adapter = skillAdapter
                    initializeLogic()
                }
            }
        })

        viewModel?.removedFromFav?.observe(viewLifecycleOwner) { response ->
            response?.let {
                if (!it.message.isNullOrEmpty()) {
                    showToastL(this@MainFragment.requireContext(), it.message)
                } else if (!it.errors.isNullOrEmpty()) {
                    showToastL(this@MainFragment.requireContext(), it.errors[0].reason)
                }
            }
        }

        viewModel?.addedToFav?.observe(viewLifecycleOwner) { response ->
            response?.let {
                if (!it.message.isNullOrEmpty()) {
                    showToastL(this@MainFragment.requireContext(), it.message)
                } else if (!it.errors.isNullOrEmpty()) {
                    showToastL(this@MainFragment.requireContext(), it.errors[0].reason)
                }
            }
        }
    }



    /**
     * Initializing Decorator logic
     * */
    private fun initializeLogic() {
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                val colorAlert =
                    ContextCompat.getColor(recyclerView.context, R.color.bg_red)
                val teal200 =
                    ContextCompat.getColor(recyclerView.context, R.color.teal_200)
                val defaultWhiteColor =
                    ContextCompat.getColor(recyclerView.context, R.color.white)

                //Max displacement caused by user's action.
                val displacement = dX.coerceAtMost(400.0f)
                // This is where to start decorating
                ItemDecorator.Builder(c, recyclerView, viewHolder, displacement, actionState).set(
                    backgroundColorFromStartToEnd = colorAlert,
                    backgroundColorFromEndToStart = teal200,
                    textFromStartToEnd = getString(R.string.action_removed),
                    textFromEndToStart = getString(R.string.action_add_to_fav),
                    textColorFromStartToEnd = defaultWhiteColor,
                    textColorFromEndToStart = defaultWhiteColor,
                    iconTintColorFromStartToEnd = defaultWhiteColor,
                    iconTintColorFromEndToStart = defaultWhiteColor,
                    iconResIdFromStartToEnd = R.drawable.ic_wish_list,
                    iconResIdFromEndToStart = R.drawable.ic_wishlist_added
                )

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val name = skillsList[position].tileName
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        //Call API
                        runBlocking {
                            val requestBean = RequestBean(name, null)
                            viewModel?.removeFromFavourite(requestBean)
                        }
                        //skillsList.removeAt(position)
                        //binding.rvSkillList.adapter?.notifyItemRemoved(position)
                        Log.d("MainActivity", "Removed history of id:$name")
                    }
                    ItemTouchHelper.RIGHT -> {
                        Log.d("MainActivity", "Removed history of id:$name")
                    }
                }
                // Once you have swept all the items, it will re-add them to the RecyclerView again
                if (skillsList.isNullOrEmpty()) {
                    skillsList.addAll(skillsList)
                    skillAdapter?.updateList(skillsList)
                }
            }
        }
        itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvSkillList)
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

    private fun showToastL(context: Context, message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showToastS(context: Context, message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

}