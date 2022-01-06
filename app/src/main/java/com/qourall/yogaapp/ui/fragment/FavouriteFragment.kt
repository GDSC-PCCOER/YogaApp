package com.qourall.yogaapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.qourall.yogaapp.R
import com.qourall.yogaapp.ui.adapter.FavPoseAdapter
import com.qourall.yogaapp.ui.adapter.PoseAdapter
import com.qourall.yogaapp.ui.viewModel.PoseViewModel


class FavouriteFragment : Fragment() {

    private val viewModel by viewModels<PoseViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllFavPose()

        val recyclerView = view.findViewById<RecyclerView>(R.id.fav_rv)

        viewModel.allFavPose.observe(viewLifecycleOwner, {
            val adapter = FavPoseAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })

    }

}