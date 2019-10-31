package com.example.rickandmortymvvm.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmortymvvm.R
import android.view.LayoutInflater
import com.example.presentation.model.*

class CharactersAdapter(
    private val clickCallback: (transitionDisplays: TransitionDisplay) -> Unit,
    private val loadingReached: () -> Unit
) : ListAdapter<ListItemDisplayModel, CharacterListItemViewHolder>(
    object :
        DiffUtil.ItemCallback<ListItemDisplayModel>() {
        override fun areItemsTheSame(oldItem: ListItemDisplayModel, newItem: ListItemDisplayModel) =
            oldItem.areItemsTheSame(newItem)

        override fun areContentsTheSame(
            oldItem: ListItemDisplayModel,
            newItem: ListItemDisplayModel
        ) =
            oldItem.areContentsTheSame(newItem)
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context).run {
        when (viewType) {
            HomeItemViewType -> {
                inflate(R.layout.character_list_item, parent, false).let {
                    CharacterListItemViewHolder.CharacterViewHolder(it)
                }
            } else ->
        {
            inflate(R.layout.loading_holder, parent, false).let { view ->
                CharacterListItemViewHolder.LoadingViewHolder(view, loadingReached)
            }
        }
        }
    }

    override fun onBindViewHolder(holder: CharacterListItemViewHolder, position: Int) {
        when (val model = getItem(position)) {
            is CharacterDisplayModel -> {
                if (holder is CharacterListItemViewHolder.CharacterViewHolder) {
                    holder.bind(model) { display -> clickCallback(display) }
                }
            }
            is LoadingDisplayModel -> {
                if (holder is CharacterListItemViewHolder.LoadingViewHolder) {
                    holder.bind()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).isLoadingType()
    }
}