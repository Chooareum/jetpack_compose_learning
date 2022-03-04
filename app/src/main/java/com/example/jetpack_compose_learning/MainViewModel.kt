package com.example.jetpack_compose_learning

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val url = mutableStateOf("https://www.google.com")

    private val _undoSharedFlow = MutableSharedFlow<Boolean>()
    val undoShardFlow = _undoSharedFlow.asSharedFlow()

    private val _redoSharedFlow = MutableSharedFlow<Boolean>()
    val redoShardFlow = _redoSharedFlow.asSharedFlow()

    fun undo(){
        viewModelScope.launch {
            //동일한 값을 계속 발행할 시 유용한 기능
            _undoSharedFlow.emit(true)
        }
    }

    fun redo(){
        viewModelScope.launch {
            //동일한 값을 계속 발행할 시 유용한 기능
            _redoSharedFlow.emit(true)
        }

    }

}