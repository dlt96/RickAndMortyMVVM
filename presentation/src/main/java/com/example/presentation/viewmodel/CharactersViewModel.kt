package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.domain.datatype.Either
import com.example.domain.datatype.biMap
import com.example.domain.datatype.fold
import com.example.domain.datatype.map
import com.example.domain.model.CharacterListModel
import com.example.domain.model.CharacterModel
import com.example.domain.model.ErrorModel
import com.example.domain.usecaseContract.GetCharacterListCaseContract
import com.example.presentation.mapper.CharacterDisplayMapper
import com.example.presentation.mapper.ErrorDisplayMapper
import com.example.presentation.model.CharacterDisplayModel
import com.example.presentation.model.ErrorDisplayModel
import com.example.presentation.model.TransitionDisplay
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharacterListCase: GetCharacterListCaseContract,
    scope: ViewModelScope = viewModelScope()
) : ViewModel(), ViewModelScope by scope {

    private val loadedItems: MutableList<CharacterModel> = mutableListOf()
    private var nextPagePointer: Int? = 1

    private val characterList =
        MutableLiveData<Either<ErrorModel, CharacterListModel>>()

    private val selectedItemDisplay = MutableLiveData<TransitionDisplay?>()

    val detailDisplay: LiveData<TransitionDisplay?> = Transformations.map(selectedItemDisplay) { display ->
        display
    }

    val listDisplay: LiveData<Either<ErrorDisplayModel, List<CharacterDisplayModel>>> =
        Transformations.map(characterList) {
            it.biMap(
                { error ->
                    ErrorDisplayMapper.transform(error)
                },
                { domainModel ->
                    CharacterDisplayMapper.transform(domainModel)
                }
            )
        }

    fun start() {
        loadCharacters()
    }

    fun onItemSelected(item: TransitionDisplay?) {
        selectedItemDisplay.value = item
    }

    fun onFinishPage() {
        loadCharacters()
    }

    private fun loadCharacters() {
        launch {
            getCharacterListCase.getPage(nextPagePointer).let { result ->
                nextPagePointer = result.fold(
                    { null },
                    { it.nextPage }
                )
                characterList.postValue(
                    result.map {
                        loadedItems.addAll(it.characterList)
                        CharacterListModel(
                            loadedItems, it.nextPage
                        )
                    })
            }
        }
    }

    fun onPressBack() {
        selectedItemDisplay.value = null
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}