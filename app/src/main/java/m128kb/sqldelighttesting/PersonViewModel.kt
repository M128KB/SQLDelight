package m128kb.sqldelighttesting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class PersonViewModel @Inject constructor (
    private val personDateSource: PersonDateSource
) : ViewModel() {

    val persons = personDateSource.getAllPerson()

    var fname by mutableStateOf("")
        private set

    var age by mutableLongStateOf(Random.nextLong())
        private set

    fun insertPerson() {

        if (fname.isEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            personDateSource.insertPerson(fname, age)
            fname = ""

        }

    }

    fun onFirstNameChange(value: String) {
        fname = value
    }


    fun onDeletePerson(id:Long){
        viewModelScope.launch {
            personDateSource.delete(id)
        }

    }

}