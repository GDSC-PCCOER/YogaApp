package com.qourall.yogaapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.qourall.yogaapp.R
import com.qourall.yogaapp.ui.adapter.CategoryAdapter
import com.qourall.yogaapp.ui.adapter.PoseAdapter
import com.qourall.yogaapp.ui.viewModel.PoseViewModel

class PoseFragment : Fragment() {

    private val viewModel by viewModels<PoseViewModel>()

    private val args : PoseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.category != "all"){
            viewModel.getCatPoses(args.category)
        }
        else{
            viewModel.getPoses()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.pose_rv)
        val poseProgress : ProgressBar = view.findViewById(R.id.load_pose)
        poseProgress.visibility = View.VISIBLE

        if (args.category == "all"){
            viewModel.allPose.observe(viewLifecycleOwner, {
                poseProgress.visibility = View.GONE
                val adapter = PoseAdapter(requireContext(), it)
                recyclerView.adapter = adapter
            })
        } else {
            viewModel.allCatPose.observe(viewLifecycleOwner, {
                poseProgress.visibility = View.GONE
                val adapter = PoseAdapter(requireContext(), it)
                recyclerView.adapter = adapter
            })
        }


    }

}