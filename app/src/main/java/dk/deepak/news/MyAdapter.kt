package dk.deepak.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


class MyAdapter(val ArticleArrayList: List<Article>, private val listener: MainActivity) :
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.eachrow, parent, false)

    val  viewHolder = MyViewHolder(itemView)
     itemView.setOnClickListener {
        listener.onItemClicked(ArticleArrayList[viewHolder.adapterPosition])
     }
        return viewHolder
    }

    override fun getItemCount(): Int {
      return ArticleArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = ArticleArrayList[position]
        holder.hTitle.text = currentItem.title
        holder.hAuthor.text = currentItem.author
        Picasso.get().load(currentItem.urlToImage).into(holder.hImage)

    }
    class MyViewHolder(itemView: View  ) : RecyclerView.ViewHolder(itemView){
      var hTitle : TextView
      var hImage : ShapeableImageView
      var hAuthor :TextView


        init {
             hTitle = itemView.findViewById<TextView>(R.id.HeadingTitle)
             hImage = itemView.findViewById<ShapeableImageView>(R.id.HeadingImage)
             hAuthor = itemView.findViewById<TextView>(R.id.author)
        }
    }
}


