package m128kb.sqldelighttesting

import kotlinx.coroutines.flow.Flow
import testing.MyDbEntity


interface PersonDateSource {

    fun getAllPerson(): Flow<List<MyDbEntity>>

    suspend fun insertPerson(name: String, age: Long, id: Long? = null)

    suspend fun delete(id: Long?)

}