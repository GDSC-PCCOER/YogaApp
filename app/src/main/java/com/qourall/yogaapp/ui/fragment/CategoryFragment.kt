package com.qourall.yogaapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.qourall.yogaapp.R
import com.qourall.yogaapp.ui.adapter.CategoryAdapter
import com.qourall.yogaapp.ui.viewModel.CategoryViewModel

class CategoryFragment : Fragment() {


    private val viewModel by viewModels<CategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = view.findViewById(R.id.cat_rv)
        val catProgress : ProgressBar = view.findViewById(R.id.load_cat)
        catProgress.visibility = View.VISIBLE

        viewModel.allCat.observe(viewLifecycleOwner, {
            catProgress.visibility = View.GONE
            val adapter = CategoryAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })
    }
}