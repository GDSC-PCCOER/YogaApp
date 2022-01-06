package com.qourall.yogaapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.qourall.yogaapp.R
import com.qourall.yogaapp.data.localDB.Pose
import com.qourall.yogaapp.data.models.PoseDetails
import com.qourall.yogaapp.ui.adapter.CategoryAdapter
import com.qourall.yogaapp.ui.viewModel.PoseViewModel
import com.qourall.yogaapp.utils.Utils.loadSvg
import kotlinx.coroutines.*

class PoseDetailFragment : Fragment() {

    private val args: PoseDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<PoseViewModel>()

    private lateinit var pose: PoseDetails
    private var favStatus : MutableLiveData<Boolean>? = null

    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pose_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView : RecyclerView = view.findViewById(R.id.det_cat_rv)
        val pImg : ImageView = view.findViewById(R.id.p_img)
        val favImg : ImageView = view.findViewById(R.id.star_img)

        val sName : TextView = view.findViewById(R.id.name)
        val eName : TextView = view.findViewById(R.id.e_name)
        val nameCard = view.findViewById<MaterialCardView>(R.id.s_name_card)
//        val desc : TextView = view.findViewById(R.id.description)
//        val sName : TextView = view.findViewById(R.id.name)

        viewModel.getPoseDetails(args.poseId)

        viewModel.poseDetails.observe(viewLifecycleOwner, {
            pose = it
            viewModel.checkFav(it.id)
            sName.text = it.sanskrit_name
            eName.text = it.english_name
            pImg.loadSvg(it.img_url)
            val adapter = CategoryAdapter(requireContext(), it.yoga_categories)
            recyclerView.adapter = adapter
        })

        viewModel.isFav.observe(viewLifecycleOwner, { status ->
            favStatus = MutableLiveData(status)

            if (status){
                favImg.setImageResource(R.drawable.ic_baseline_favorite_24)
                favStatus!!.value = true
            } else{
                favImg.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                favStatus!!.value = false
            }

            nameCard.setOnClickListener {
                if (status){
                    viewModel.removeFav(pose.id)
                    viewModel.checkFav(pose.id)
                }else {
                    viewModel.addFav(pose.id)
                }
            }

        })

        viewModel.isAdded.observe(viewLifecycleOwner, {
            if(it){
                viewModel.checkFav(pose.id)
            }
        })



    }

}