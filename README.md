base-android-projects
=====================

Sample Android project I created using Android Studio. Main points I wanted to show:
- using Maven dependencies with Gradle (jackson-databind library is not taken from repository because gradle seems not to handle chained dependencies)
- using Google's Volley networking framework
- using Jackson json parser with annotations
- include OkHttp to Volley
- using ListViews and ListAdapter together with Volley (created two abstract classes which should be easy to extend and reuse)