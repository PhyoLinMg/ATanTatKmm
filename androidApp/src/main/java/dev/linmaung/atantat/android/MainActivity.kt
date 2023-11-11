package dev.linmaung.atantat.android

import Student
import StudentSharedRepo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.linmaung.atantat.kmm.shared.cache.DriverFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val students= MutableStateFlow<List<Student>>(emptyList())

        val studentSharedRepo= StudentSharedRepo(driverFactory = DriverFactory(this))


        setContent {
            MyApplicationTheme {
                val coroutineScope= rememberCoroutineScope()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(onClick = {
                        coroutineScope.launch {
                            studentSharedRepo.insertStudent(name="Lin Maung", className = "1A")
                            students.update { studentSharedRepo.getStudentList().getOrDefault(emptyList()) }
                        }
                    }) {
                        Text("Add Student Name: Lin Maung, class: 1A")
                    }
                    LazyColumn{
                        items(students.value){
                            Text("${it.name} : ${it.className}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
