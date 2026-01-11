package amitkundu.database

import android.content.Context
import androidx.room.Room

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_KEY.DB_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}