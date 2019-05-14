import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.jackson.responseObject
import me.tongfei.progressbar.*
import java.io.File

internal class Emoji {
    val emoji: Map<String, String> = HashMap()
}

class Slack(private val token: String) {
    private val slackApiEmojiEndpoint = "https://slack.com/api/emoji.list"

    fun downloadEmojiList(downloadPath: String) {
        val (_, _, result) = slackApiEmojiEndpoint
            .httpGet()
            .authentication()
            .bearer(token)
            .responseObject<Emoji>()

        val emojiModel = result.get()

        val progress = ProgressBar(
            "Downloading",
            emojiModel.emoji.size.toLong(),
            ProgressBarStyle.ASCII
        )

        for ((emojiName, emojiURL) in emojiModel.emoji) {
            progress.step()
            if (emojiURL.startsWith("alias:")) continue

            val fileExt = emojiURL.split(".").last()

            Fuel.download(emojiURL)
                .fileDestination { _, _ ->
                    File(downloadPath, "$emojiName.$fileExt")
                }.response()
        }
        progress.close()
    }
}