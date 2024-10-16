package com.impeccabletech.truecallerassignment.preview

import com.impeccabletech.truecallerassignment.repository.MainRepositoryImpl
import com.impeccabletech.truecallerassignment.viewmodel.MainViewModel

class FakeMainViewModel : MainViewModel(MainRepositoryImpl(FakeApiService())) {
    init {
        _character15.value = 'T'
        _every15thCharacter.value = listOf('T', 'r', 'u', 'e', 'c', 'a', 'l', 'l', 'e', 'r')
        _wordCount.value = mapOf("Truecaller" to 3, "Engineer" to 2)
    }
}