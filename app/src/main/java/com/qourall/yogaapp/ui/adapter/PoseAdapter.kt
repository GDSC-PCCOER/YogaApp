package com.qourall.yogaapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.transform.CircleCropTransformation
import com.qourall.yogaapp.R
import com.qourall.yogaapp.data.localDB.Category
import com.qourall.yogaapp.data.localDB.Pose
import com.qourall.yogaapp.ui.fragment.CategoryFragmentDirections
import com.qourall.yogaapp.ui.fragment.PoseFragmentDirections
import com.qourall.yogaapp.utils.Utils.loadSvg


class PoseAdapter(private val context: Context, private val data: List<Pose>) : RecyclerView.Adapter<PoseAdapter.PoseViewHolder>() {

    class PoseViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val img: ImageView = v.findViewById(R.id.pose_img)
        val name: TextView = v.findViewById(R.id.pose_name)
        val s_name: TextView = v.findViewById(R.id.pose_name_sanskrit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoseViewHolder {
        return PoseViewHolder(LayoutInflater.from(context).inflate(R.layout.pose_item, parent, false))
    }

    override fun onBindViewHolder(holder: PoseViewHolder, position: Int) {
        holder.name.text = data[position].english_name
        holder.s_name.text = data[position].sanskrit_name


        holder.img.loadSvg(data[position].img_url)

        holder.itemView.setOnClickListener {
            val action = PoseFragmentDirections.actionPoseFragmentToPoseDetailFragment(data[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}