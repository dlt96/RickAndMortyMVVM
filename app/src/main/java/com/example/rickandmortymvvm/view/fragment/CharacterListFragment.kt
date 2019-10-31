package com.example.rickandmortymvvm.view.fragment

import android.os.Build
import android.os.Bundle
import android.transition.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.domain.datatype.fold
import com.example.presentation.model.ErrorDisplayModel
import com.example.presentation.viewmodel.CharactersViewModel
import com.example.rickandmortymvvm.R
import com.example.rickandmortymvvm.view.adapter.CharactersAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharacterListFragment : Fragment() {

    @BindView(R.id.rv_characters)
    lateinit var recyclerView: RecyclerView

    private val viewModel: CharactersViewModel by sharedViewModel()
    private var unbind: Unbinder? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            exitTransition = Fade()
        }
    }

    companion object {
        fun newInstance() = CharacterListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false).also {
            unbind = ButterKnife.bind(this, it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapter = CharactersAdapter(
            clickCallback = { transition ->
                viewModel.onItemSelected(transition)
            }, loadingReached = {
                viewModel.onFinishPage()
            }
        )

        recyclerView.adapter = adapter
        viewModel.listDisplay.observe(this, Observer { characters ->
            characters.fold({
                showError(it)
            }, { list ->
                characters?.let { adapter.submitList(list) }
            })

        })
        viewModel.start()
    }

    private fun showError(error: ErrorDisplayModel) {

        AlertDialog.Builder(requireContext()).also { dialog ->

            dialog.setTitle(getString(R.string.error_dialog_header))

            dialog.setMessage(error.message)

            dialog.setIcon(android.R.drawable.ic_dialog_alert)

        }.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbind?.unbind()
    }
}