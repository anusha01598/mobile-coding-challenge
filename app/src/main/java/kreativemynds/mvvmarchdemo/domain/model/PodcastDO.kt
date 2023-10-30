@file:JvmName("PodcastKt")

package kreativemynds.mvvmarchdemo.domain.model

data class PodcastDto(
    val audio_length_sec: Int,
    val country: String,
    val description: String,
    val earliest_pub_date_ms: Long,
    val email: String,
    val explicit_content: Boolean,
    val genre_ids: List<Int>,
    val id: String,
    val image: String,
    val is_claimed: Boolean,
    val itunes_id: Int,
    val language: String,
    val latest_episode_id: String,
    val latest_pub_date_ms: Long,
    val listen_score: Int,
    val listen_score_global_rank: String,
    val listennotes_url: String,
    val publisher: String,
    val rss: String,
    val thumbnail: String,
    val title: String,
    val total_episodes: Int,
    val type: String,
    val update_frequency_hours: Int,
    val website: String
)

fun PodcastDto.toPodcast(): Podcast {
    return Podcast(
        id = id,
        thumbnail = thumbnail,
        title = title,
        publisher = publisher,
        description = description
    )
}