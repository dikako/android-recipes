package ids.dika.recipes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ids.dika.recipes.R
import ids.dika.recipes.activity.DetailActivity
import ids.dika.recipes.databinding.ItemRowRecommendationBinding
import ids.dika.recipes.model.DataRecipes
import java.text.ParsePosition

class ListRecommendationAdapter : RecyclerView.Adapter<ListRecommendationAdapter.ListViewHolder>() {
    private val dataRec = ArrayList<DataRecipes>()

    fun setData(items: ArrayList<DataRecipes>) {
        dataRec.clear()
        dataRec.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemRowRecommendationBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_recommendation, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataRec.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = dataRec[position]

        with(holder) {
            binding.tvMeal.text = data.strMeal
            binding.tvArea.text = data.strArea
            binding.tvCategory.text = data.strCategory
            Glide.with(itemView.context)
                .load(data.strMealThumb)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivMeal)

            itemView.setOnClickListener {

                val context: Context = it!!.context
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_RECIPE, data)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)

            }
        }
    }
}
