package com.example.jetpack_compose_learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel()){

    val text1: MutableState<String> = remember{
        mutableStateOf("Hello World")
    }

    var text2: String by remember {
        mutableStateOf("Hello World")
    }


    val (text: String, setText: (String) -> Unit ) = remember{
        mutableStateOf("Hello World")
    }

    val text3: State<String> = viewModel.liveData.observeAsState("Hello World")

    Column() {

        Text("Hello World")
        Button(onClick = {
            text1.value = "변경"
            print(text1.value)
            text2 = "변경"
            print(text2)
            setText("변경")
            viewModel.changeValue("변경")
        }) {
            Text("click")
        }
        TextField(value = text, onValueChange = setText)
    }
}

class MainViewModel: ViewModel(){
    private val _value: MutableState<String> = mutableStateOf("Hello World")
    val value: State<String> = _value

    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData

    fun changeValue(value: String) {
          _value.value = value
    }
}