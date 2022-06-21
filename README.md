# Helper

- ** 在主要build.gradle中加入 Jitpack 仓库 [![](https://jitpack.io/v/YQcharlse/Helper.svg)](https://jitpack.io/#YQcharlse/Helper)  **
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- ** 在app's build.gradle中添加依赖**

```gradle
apply plugin: 'kotlin-kapt'

dependencies {
  ...
  implementation 'com.github.YQcharlse:Helper:1.0.0'
  kapt 'com.github.liujingxing.rxhttp:rxhttp-compiler:2.8.3'
}
```
  
- ** 在Application中初始化**

```kotlin
 SmartGenHelper.init(this,BuildConfig.DEBUG)
```
