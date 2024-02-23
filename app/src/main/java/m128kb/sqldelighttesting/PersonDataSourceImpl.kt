package m128kb.sqldelighttesting

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import testing.MyDbEntity
import javax.inject.Inject

class PersonDataSourceImpl @Inject constructor(
    db:MyDB
):PersonDateSource {


    private val queries = db.myDBQueries

    override fun getAllPerson(): Flow<List<MyDbEntity>> {
        return queries.getAllPersons().asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun insertPerson(name: String, age: Long, id: Long?) {
        withContext(Dispatchers.IO){
            queries.insertPerson(name = name, age = age, id = id)
        }
    }

    override suspend fun delete(id: Long?) {
        withContext(Dispatchers.IO){
            queries.deleteById(id!!)
        }
    }
}