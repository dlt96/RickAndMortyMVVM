package com.example.rickandmortymvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bumptech.glide.Glide
import com.example.presentation.model.CharacterDetailDisplay
import com.example.rickandmortymvvm.R
import com.example.rickandmortymvvm.view.activity.DISPLAY_KEY


class DetailFragment : Fragment() {

    @BindView(R.id.iv_character)
    lateinit var avatarImage: ImageView
    @BindView(R.id.tv_detail_name)
    lateinit var name: TextView
    @BindView(R.id.tv_planet)
    lateinit var planetName: TextView
    @BindView(R.id.tv_type)
    lateinit var typeName: TextView
    @BindView(R.id.b_contact)
    lateinit var contactButton: Button
    @BindView(R.id.card)
    lateinit var card: View

    private var unbind: Unbinder? = null

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false).also {
            unbind = ButterKnife.bind(this, it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val display = arguments?.getParcelable<CharacterDetailDisplay>(DISPLAY_KEY)
        (display != null).let { showCard ->
            card.isVisible = showCard
            avatarImage.isVisible = showCard
            name.isVisible = showCard
            typeName.visibility = if (showCard && display?.type != null) View.VISIBLE else View.GONE
            planetName.isVisible = showCard
            contactButton.isVisible = showCard
        }

        display?.let {

            Glide.with(this)
                .load(display.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(avatarImage)

            name.text = display.name
            display.type?.let { type -> typeName.text = getString(R.string.type_label, type) }
            planetName.text = getString(R.string.planet_label, display.planet)
            //contactButton.setOnClickListener { mainListener?.showError(ErrorDisplay.NotImplementedError) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbind?.unbind()
    }
}
