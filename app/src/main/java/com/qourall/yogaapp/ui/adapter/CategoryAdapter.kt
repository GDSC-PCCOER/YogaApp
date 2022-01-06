package com.qourall.yogaapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.qourall.yogaapp.R
import com.qourall.yogaapp.data.localDB.Category
import com.qourall.yogaapp.ui.fragment.CategoryFragmentDirections

class CategoryAdapter(private val context: Context, private val data: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val name: TextView = v.findViewById(R.id.category_name)
        val desc: TextView = v.findViewById(R.id.desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.category_item, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.name.text = data[position].name
        holder.desc.text = data[position].description

        holder.itemView.setOnClickListener {
            try {
                val action = CategoryFragmentDirections.actionCategoryFragmentToPoseFragment(data[position].short_name)
                Navigation.findNavController(it).navigate(action)
            } catch (e: Exception){}
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}