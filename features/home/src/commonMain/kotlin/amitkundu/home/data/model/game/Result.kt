package amitkundu.home.data.model.game
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val id: Int,
    val name: String,
    val slug: String,

    val background_image: String? = "",
    val released: String? = null,
    val metacritic: Int? = null,

    val playtime: Int = 0,
    val rating: Double = 0.0,
    val rating_top: Int = 0,
    val ratings_count: Int = 0,
    val reviews_count: Int = 0,
    val reviews_text_count: Int = 0,
    val suggestions_count: Int = 0,

    val saturated_color: String? = null,
    val updated: String? = null,

    val tba: Boolean = false
)
