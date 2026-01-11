package amitkundu.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val GameId: Int,

    val name: String,
    val playtime: Int ,
    val background_image: String,
    val rating: Double,

)