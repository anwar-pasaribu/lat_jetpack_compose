package com.example.android.latcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.android.latcompose.ui.LatComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    LatComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = listOf("Anwar", "Holmes")) {
    val counterState = remember { mutableStateOf(0) }
    Column {
        for ((index, name) in names.withIndex()) {
            Greeting(name = name)
            if (index != names.size - 1) {
                Divider(color = Color.DarkGray, thickness = 1.dp)
            }
        }
        Divider(color = Color.Transparent, thickness = 32.dp)
        Counter(count = counterState.value, countFunction = {
            counterState.value = it
        })
    }
}

@Composable
fun Greeting(name: String) {
    Surface(color = Color.LightGray, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Hello $name!", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun Counter(count: Int, countFunction: (Int) -> Unit) {
    Button(
        onClick = { countFunction(count + 1) },
        backgroundColor = if (count == 3) Color.Green else Color.LightGray
    ) {
        val timesWord = if (count > 1) "times" else "time"
        Text(text = "Button press $count $timesWord")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}