package com.impeccabletech.truecallerassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.impeccabletech.truecallerassignment.repository.MainRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

open class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    protected val _character15 = MutableLiveData<Char?>()
    val character15: LiveData<Char?> = _character15

    protected val _every15thCharacter = MutableLiveData<List<Char>>()
    val every15thCharacter: LiveData<List<Char>> = _every15thCharacter

    protected val _wordCount = MutableLiveData<Map<String, Int>>()
    val wordCount: LiveData<Map<String, Int>> = _wordCount

    fun fetchWebContent() {
        _isLoading.value = true // Start loading
        viewModelScope.launch {
            val content = mainRepository.fetchWebContent() ?: return@launch
            processContent(content)
            _isLoading.value = false // End loading

        }
    }

    private fun processContent(content: String) {
        viewModelScope.launch {
            val character15Task = async { get15thCharacter(content) }
            val every15thCharacterTask = async { getEvery15thCharacter(content) }
            val wordCountTask = async { getWordCount(content) }

            // Wait for all tasks to complete and post their results
            _character15.postValue(character15Task.await())
            _every15thCharacter.postValue(every15thCharacterTask.await())
            _wordCount.postValue(wordCountTask.await())
        }

    }

    // Task 1: Function to get the 15th character
    private fun get15thCharacter(content: String): Char? {
        return content.getOrNull(14) // index starts at 0
    }

    // Task 2: Function to get every 15th character
    private fun getEvery15thCharacter(content: String): List<Char> {
        return content.filterIndexed { index, character -> (index + 1) % 15 == 0 }.toList()
    }

    // Task 3: Function to count the occurrences of each word
    private fun getWordCount(content: String): Map<String, Int> {
        return content.splitToSequence("\\s+".toRegex())
            .map { it.lowercase() }
            .filter { it.isNotBlank() }
            .groupingBy { it }
            .eachCount()
    }
}
