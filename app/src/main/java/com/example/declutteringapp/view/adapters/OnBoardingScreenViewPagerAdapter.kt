package com.example.declutteringapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RawRes
import androidx.recyclerview.widget.RecyclerView
import com.example.declutteringapp.R
import com.example.declutteringapp.model.Item
import com.example.declutteringapp.viewmodel.OnBoardingViewModel
import kotlinx.android.extensions.LayoutContainer
import java.io.InputStream


class LocalAdapter(val viewModel: OnBoardingViewModel) : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {
    private var items = listOf<Item>()
//    var id: Int =
 //       resor.getIdentifier("yourpackagename:drawable/$StringGenerated", null, null)
//val drawble = context!!.resources.getDrawable(R.drawable.ic_training,context!!.theme)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.onboarding_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LocalAdapter.ViewHolder, position: Int) {
        val item = items[position]


        holder.titleTv.text  = item.title
        holder.imageViewOn.setImageResource(item.imagesOn);

/*
        if (position < itemCount - 1) {
            holder.nextBtn.isEnabled = true
            holder.nextBtn.setOnClickListener {
                // next
                viewModel.nextPageEvent.value = position + 1
            }
        }
        else {
            holder.nextBtn.isEnabled = false
        }*/
    }

    fun replaceItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer{

        val titleTv = itemView.findViewById<TextView>(R.id.titleTextView)
       // = itemView.findViewById<ImageView>(R.id.animationView)
       val imageViewOn: ImageView = itemView.findViewById(R.id.animationView)

     //   val nextBtn= itemView.findViewById<Button>(R.id.nextButton)
        }

    fun ImageView.getRawInput(@RawRes resourceId: Int): InputStream {
        return resources.openRawResource(resourceId)
    }



}

/*
class OnBoardingScreenViewPagerAdapter(private val mContext: Context) : PagerAdapter() {
    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val modelObject = OnBoardingModel.values()[position]
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(modelObject.layoutResId, collection, false) as ViewGroup
        collection.addView(layout)
        return layout
    }
    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }
    override fun getCount(): Int {
        return OnBoardingModel.values().size
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

}*/










/*
class OnBoardingScreenViewPagerAdapter (list:ArrayList<Fragment>, fm: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fm,lifecycle) {



    val fragmentList=list;
    override fun getItemCount(): Int {

        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
    }
*/
