package br.com.fiap.isgood.adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.isgood.databinding.FragmentRestauranteSocialmidiaItemBinding
import br.com.fiap.isgood.models.Restaurante
import com.bumptech.glide.Glide
import br.com.fiap.isgood.adapters.ListRestauranteSocialMidiaAdapter.*
import com.google.android.material.snackbar.Snackbar

class ListRestauranteSocialMidiaAdapter :
    ListAdapter<Restaurante.SocialLinks, ListRestauranteSocialMidiaViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListRestauranteSocialMidiaViewHolder {
        return ListRestauranteSocialMidiaViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ListRestauranteSocialMidiaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Restaurante.SocialLinks>(){
            override fun areContentsTheSame(
                oldItem: Restaurante.SocialLinks,
                newItem: Restaurante.SocialLinks
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: Restaurante.SocialLinks,
                newItem: Restaurante.SocialLinks
            ): Boolean {
                return oldItem.publicUri == newItem.publicUri
            }
        }
    }
    class ListRestauranteSocialMidiaViewHolder(
        private val itemBinding : FragmentRestauranteSocialmidiaItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root){

        fun bind (socialLinks: Restaurante.SocialLinks){
            itemBinding.run {
                Log.i("ListRestauranteSocialMidiaViewHolder", "Adicionando Link para Mídia Social: '${socialLinks.toastText}'")
                //Define a imagem
                Glide.with(itemBinding.ivSocialMediaLogo.context)
                    .load(socialLinks.kind)
                    .into(itemBinding.ivSocialMediaLogo)

                //Cria a ação de clicar
                itemBinding.ivSocialMediaLogo.setOnClickListener{
                    val goUrl = Intent(Intent.ACTION_VIEW, Uri.parse(socialLinks.publicUri))
                    if (socialLinks.toastText.isNotBlank())
                        Toast.makeText(itemBinding.root.context, socialLinks.toastText, Toast.LENGTH_SHORT).show()
                    itemBinding.root.context.startActivity(goUrl)
                }
            }
        }

        companion object{
            fun create (parent : ViewGroup) : ListRestauranteSocialMidiaViewHolder {
                val itemBinding = FragmentRestauranteSocialmidiaItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return ListRestauranteSocialMidiaViewHolder(itemBinding)
            }
        }
    }




}