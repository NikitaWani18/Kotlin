package com.example.kotlinlogin.ui.main

import AndroidVersionModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlogin.R


class CustomRecyclerAdapter(
    val context: Context,
    val androidVersionList: ArrayList<AndroidVersionModel>
) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0?.txtTitle?.text = androidVersionList[p1].codeName
        p0?.txtContent?.text =
            "Min : ${androidVersionList[p1].versionName}, Sec : ${androidVersionList[p1].apiLevel}"
        p0?.image.setImageResource(androidVersionList[p1].imgResId!!)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(p0?.getContext())
        val view = inflater.inflate(R.layout.recycler_view_item, p0, false)
        return ViewHolder(view).listen { pos, type ->
            val item = androidVersionList.get(pos)
            //TODO do other stuff here
            (view.getContext() as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace( R.id.container, EggTimerFragment.newInstance())
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return androidVersionList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.findViewById<TextView>(R.id.appOSTitle_txtVw)
        val txtContent = itemView.findViewById<TextView>(R.id.appOSDetails_txtVw)
        val image = itemView.findViewById<ImageView>(R.id.appOS_imageVw)
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }


}