package com.delarosa.composeapp.statecontent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class StateContent : ComponentActivity() {

    private val stateContentViewModel: StateContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {
                Text(text = "Remember")
                useRemember()

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                Text(text = "Compose State")
                useState()

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                Text(text = "State Flow")
                useFlow()

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                Text(text = "filter State Flow to make it run press the prev button")
                useFilterFlow()
            }
        }

    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun useRemember() {
        var textRemember by remember { mutableStateOf("hola remember") }

        TextField(modifier = Modifier.fillMaxWidth(), value = textRemember, onValueChange = {
            textRemember = it
        })

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun useState() {
        val textState by stateContentViewModel.textState

        Column {
            TextField(modifier = Modifier.fillMaxWidth(), value = textState, onValueChange = {
                stateContentViewModel.updateText(it)
            })

            Button(onClick = { stateContentViewModel.runCounter() }) {
                Text(text = "Run counter")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun useFlow() {
        val textStateFlow by stateContentViewModel.textStateFlow.collectAsState()

        Column {
            TextField(modifier = Modifier.fillMaxWidth(), value = textStateFlow, onValueChange = {
                stateContentViewModel.updateTextFlow(it)
            })

            Button(onClick = { stateContentViewModel.runCounterFlow() }) {
                Text(text = "Run counter State Flow")
            }
        }
    }


    @Composable
    private fun useFilterFlow() {
        val filterFlow by stateContentViewModel.filterStateFlow.collectAsState("")
        Text(modifier = Modifier.fillMaxWidth(), text = filterFlow)
    }
}


class StateContentViewModel : ViewModel() {

    //compose state
    private val _textState = mutableStateOf("hola mutabla")
    val textState: State<String> = _textState

    fun updateText(text: String) {
        _textState.value = text
    }

    fun runCounter() {
        viewModelScope.launch {
            for (i in (0..1000).withIndex()) {
                _textState.value = i.value.toString()
                delay(1)
            }
        }
    }


    // Flow state  ---> better with lifecycle since you can collect with collectAsStateWithLifecycle
    private val _textStateFlow = MutableStateFlow("0")
    val textStateFlow: StateFlow<String> = _textStateFlow

    val filterStateFlow = _textStateFlow.filter {
        it.toInt() % 42 == 0
    }


    fun updateTextFlow(text: String) {
        _textStateFlow.value = text
    }

    fun runCounterFlow() {
        viewModelScope.launch {
            for (i in (0..1000).withIndex()) {
                _textStateFlow.value = i.value.toString()
                delay(1)
            }
        }
    }

}
