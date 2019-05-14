# Slack Emoji Exporter
A tool to export Slack Workspace's Emojis

## To Build
* Install Java v11
* Run `./gradlew jar`

## To Run
* Create an API tokens for Slack from the [Legacy Tokens page](https://api.slack.com/custom-integrations/legacy-tokens)
* `SLACK_API_TOKEN=xoxp-XXXXXX java -jar ./build/libs/slack-emoji-exporter-1.0-SNAPSHOT.jar`

## Docker

### Build
Run `docker build . -t kotlin_slack_emoji_exporter` to build a docker image.

### Run
There is an docker image you can use for you don't need to build the code yourself,
`docker run -e SLACK_API_TOKEN=xoxp-XXXXXX -v ~/emojis:/root/downloads amscotti/kotlin_slack_emoji_exporter`