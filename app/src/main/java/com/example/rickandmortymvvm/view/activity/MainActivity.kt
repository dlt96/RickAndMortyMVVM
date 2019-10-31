package com.example.rickandmortymvvm.view.activity

import android.os.Build
import android.os.Bundle
import android.transition.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import butterknife.ButterKnife
import com.example.presentation.model.TransitionDisplay
import com.example.presentation.viewmodel.CharactersViewModel
import com.example.rickandmortymvvm.R
import com.example.rickandmortymvvm.view.fragment.CharacterListFragment
import com.example.rickandmortymvvm.view.fragment.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


const val DISPLAY_KEY = "DISPLAY_KEY"

class MainActivity : AppCompatActivity() {

    private val charactersViewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        supportFragmentManager?.beginTransaction()?.replace(R.id.container, CharacterListFragment.newInstance())
            ?.commitNow()

        charactersViewModel.detailDisplay.observe(this, Observer { transition ->
            transition?.let {
                openDetailScreenForCharacter(transition)
            }
        })
    }

    private fun openDetailScreenForCharacter(transition: TransitionDisplay) {
        val fragment = DetailFragment.newInstance()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.sharedElementEnterTransition = DetailsTransition()
            fragment.enterTransition = Fade()
            fragment.sharedElementReturnTransition = DetailsTransition()
        }
        fragment.arguments = bundleOf(DISPLAY_KEY to transition.display)
        supportFragmentManager
            .beginTransaction()
            .addSharedElement(transition.image, "characterImage")
            .addSharedElement(transition.card, "card")
            .addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    class DetailsTransition : TransitionSet() {
        init {
            ordering = ORDERING_TOGETHER
            addTransition(ChangeBounds()).addTransition(ChangeTransform()).addTransition(
                ChangeImageTransform()
            )
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        charactersViewModel.onPressBack()
    }
}
