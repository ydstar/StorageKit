# StorageKit

## YdKit通用组件库
YdKit 是一组功能丰富的 Android 通用组件。

* [LogKit](https://github.com/ydstar/LogKit) — 轻量级的 Android 日志系统。
* [RestfulKit](https://github.com/ydstar/RestfulKit) — 简洁但不简单的 Android 网络组件库。
* [StorageKit](https://github.com/ydstar/StorageKit) — 高性能 Android 离线缓存框架。
* [ExecutorKit](https://github.com/ydstar/ExecutorKit) — 简洁易用的 Android 多线程操作框架。
* [CrashKit](https://github.com/ydstar/CrashKit) — 简洁易用的 Android Crash日志捕捉组件。
* [PermissionKit](https://github.com/ydstar/PermissionKit) — 简洁易用的 Android 权限请求组件。
* [NavigationBarKit](https://github.com/ydstar/NavigationBarKit) — 简洁易用的 Android 顶部导航条组件。
* [RefreshKit](https://github.com/ydstar/RefreshKit) — 简洁易用的 Android 下拉刷新和上拉加载组件。
* [AdapterKit](https://github.com/ydstar/AdapterKit) — 简洁易用的 Android 列表组件。
* [BannerKit](https://github.com/ydstar/BannerKit) — 简洁易用的 Android 无限轮播图组件。
* [MultiViewKit](https://github.com/ydstar/MultiViewKit) — 简洁易用的 Android 多视图组件。
* [TabBottomKit](https://github.com/ydstar/TabBottomKit) — 简洁易用的 Android 底部导航组件。

## 简介
基于Jetpack Room高性能离线缓存框架

## 导入方式

仅支持`AndroidX`
```
dependencies {
     implementation 'com.android.ydkit:storage-kit:1.0.0'
}
```

## 使用方法

#### 存储,支持任意数据类型
```java

StorageKit.saveCache(KEY, data)

```

#### 获取存储

```
val data = StorageKit.getCache(KEY)

```

#### 删除存储
```java
StorageKit.deleteCache(KEY)

```



## License
```text
Copyright [2021] [ydStar]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
