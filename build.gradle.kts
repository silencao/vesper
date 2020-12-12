allprojects {
    group = "im.silen"
    version = "0.0.5"

    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public"       ) }
        maven { url = uri("https://maven.aliyun.com/repository/google"       ) }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/spring"       ) }
        maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
    }
}
