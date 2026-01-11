package amitkundu.database

actual class DatabaseFactory {
    actual fun create(): AppDatabase {
        error("Room only works on Android")
    }
}