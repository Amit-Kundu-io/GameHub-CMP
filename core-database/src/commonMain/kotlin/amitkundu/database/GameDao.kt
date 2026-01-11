package amitkundu.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert
    suspend fun insert(task: GameEntity)

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<GameEntity>>
}