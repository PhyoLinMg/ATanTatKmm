import dev.linmaung.atantat.kmm.shared.cache.CoreDatabase
import dev.linmaung.atantat.kmm.shared.cache.DriverFactory
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.cancellation.CancellationException

class StudentSharedRepo(private val driverFactory: DriverFactory) {

    private val database = CoreDatabase(driverFactory)

    suspend fun insertStudent(name: String, className: String): Result<Unit> {
        return try {
            Result.success(database.insertStudent(name, className))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteStudent(id:Int): Result<Unit>{
        return try {
            Result.success(database.clearStudent(id=id))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getStudentList():Result<List<Student>>{
        return try{
            Result.success(database.getStudentList())
        } catch (e:Exception){
            Result.failure(e)
        }
    }




}