package amitkundu.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: GameEntity)

    @Query("SELECT * FROM games ORDER BY id DESC")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("DELETE FROM games WHERE id = :id")
    fun deleteGame(id: Int)

    @Query("SELECT COUNT(*) FROM games WHERE id = :gameId")
    suspend fun isGameExists(gameId: Int): Int

}