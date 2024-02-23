package m128kb.sqldelighttesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import m128kb.sqldelighttesting.ui.theme.SQLDelightTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SQLDelightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    PersonList()

                }
            }
        }
    }
}


@Composable
fun PersonList(
    viewModel: PersonViewModel = hiltViewModel()
) {

    val person = viewModel.persons.collectAsState(initial = emptyList()).value

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {

            person.let {
                it.forEach { entity ->
                    Row {

                        Text(
                            text = entity.name + " " + entity.age,
                            modifier = Modifier.padding(8.dp)

                        )
                        IconButton(onClick = {
                            viewModel.onDeletePerson(entity.id)
                        }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        }
                    }
                }
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            TextField(value = viewModel.fname, onValueChange = viewModel::onFirstNameChange)
        }

        FloatingActionButton(
            onClick = viewModel::insertPerson,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Text(text = "+")
        }
    }


}

