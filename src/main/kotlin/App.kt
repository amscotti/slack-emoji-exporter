import java.io.File

const val SLACK_ENV = "SLACK_API_TOKEN"

fun main() {
    require(System.getenv(SLACK_ENV).isNotEmpty()) { "SLACK_API_TOKEN not found." }
    val token: String = System.getenv(SLACK_ENV)
    val slack = Slack(token)
    val downloadPath = "${System.getProperty("user.dir")}${File.separator}downloads"
    File(downloadPath).mkdir()
    slack.downloadEmojiList(downloadPath)
}