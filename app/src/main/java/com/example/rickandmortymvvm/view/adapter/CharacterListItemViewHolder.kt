package com.example.rickandmortymvvm.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.presentation.model.CharacterDisplayModel
import com.example.presentation.model.TransitionDisplay
import com.example.rickandmortymvvm.R

sealed class CharacterListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class CharacterViewHolder(view: View) : CharacterListItemViewHolder(view) {
        @BindView(R.id.tv_character_name)
        lateinit var name: TextView
        @BindView(R.id.card)
        lateinit var card: View
        @BindView(R.id.iv_avatar)
        lateinit var image: ImageView

        init {
            ButterKnife.bind(this, view)
        }

        fun bind(item: CharacterDisplayModel, clickListener: (TransitionDisplay) -> Unit) {
            Glide.with(image.context)
                .load(item.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(image)
            name.text = item.name
            item.id.toString().let { id ->
                ViewCompat.setTransitionName(image, "image_$id")
                ViewCompat.setTransitionName(name, "name_$id")
                ViewCompat.setTransitionName(card, "card_$id")
            }

            card.setOnClickListener {
                clickListener(
                    TransitionDisplay(
                        display = item.toDetailDisplay(),
                        image = image,
                        name = name,
                        card = card
                    )
                )
            }
        }
    }

    class LoadingViewHolder(view: View, val onBindCallback: () -> Unit) :
        CharacterListItemViewHolder(view) {
        fun bind() {
            onBindCallback()
        }
    }
}