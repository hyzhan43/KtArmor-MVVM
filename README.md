# 前言

学习了Kotlin有一段时间了, 每次写项目/Demo的时候, 总是用到`网络请求`、`MVP`、`MVVM`、`常用工具类`、`通用自定义View`, 索性把这些整合到一起, 搭成一个Android的脚手架——KtArmor. 框架是我个人经验的积累, 总结. 如有不妥, 望各位大佬指出.

# 什么是KtArmor ?

`KtArmor` 寓意着 为Android 赋予`战斗装甲`, 方便开发者快速进行Android 开发。节约开发者开发时间。

- `架构模式`: `MVVM` + `Kotlin` + `Androidx` + `ViewModel` + `LiveData`
- `网络请求`: `Retrofit` + `Okhttp` + `Coroutine` + ~~`RxJava`~~ + ~~`DataBinding`~~  
- `代码`：简洁优雅, 易扩展
- `文档`: 有相关的KtArmor-MVVM 系列文档说明 (后续补充)
- `功能`:  
    - 基本`IActivity`、`IFragment`  
    - MVVM框架封装 `IMvmActivity`、`IMvmFragment`、`BaseViewModel`、`BaseRepository`封装  
    - 网络请求封装 `BaseOkHttpClient`、`BaseRetrofit`、`RetrofitFactory`  
    - 常用控件`PlaceHolderView(占位布局)`， `LoadingView(加载框)`  
    - 常用扩展封装 `KtWing` 框架(`SharedPreferences`、`StartActivity`、`Log`、`Toast`(不重复显示))等  
    - ....

# 亮点1——无需初始化

一般而言，使用第三方框架都需要进行相关初始化操作。如下: 

```kotlin
class BaseApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        // 初始化KtArmor (不再需要)
        // KtArmor.init(this, MyRetrofitConfig()) 
    }
}
```

若无需定制 `Retrofit` 相关配置的话，KtArmor-MVVM 会自动初始化，无需手动 `init`

# 亮点2——无需继承

大部分的 Android 快速开发的脚手架，都是需要继承 `BaseXXXActivity`、`XXXActivity` 等 Activity 封装的基类，但是这往往不能解决`多继承`的问题，也这样大大限制了开发者自由，所以在 `KtArmor-MVVM` 框架中，采用了 `接口`的形式，无需继承 BaseXXXActivity，实现对应接口(`IActivity，IMvmActivity`) 即可，后面有相关Login使用教程例子参考。

# 亮点3——自动“注入”

## @BindViewModel
```kotlin
class LoginActivity : AppCompatActivity(), IMvmActivity {

    @BindViewModel  // 看这里！！
    lateinit var viewModel: LoginViewModel

    //...省略其他
}
```
通过 `@BindViewModel` 注解viewModel 变量，KtArmor-MVVM 通过`反射`，自动创建 `LoginViewModel`实例， 并赋值给 viewModel 变量。直接使用即可！

## @BaseUrl

```kotlin
@BaseUrl(API.BASE_URL)  // 看这里！！
interface ApiService {
    @POST(API.LOGIN)
    suspend fun login(@Query("username") username: String,
                      @Query("password") password: String): BaseResponse<LoginRsp>
}
```

我们通常使用Retrofit 的时候，都会创建对应 `Service` 接口类， 通过在 Service 上 标识 @BaseUrl 注解，并传入 对应 baseUrl，KtArmor-MVVM 就会将 `baseUrl` “注入” 到 Retrfit 中。目前只是针对`单个` baseUrl， 后续将考虑多个 baseUrl 动态切换的相关处理。

# KtArmor-MVVM框架引入

## 注意！
- 由于采用了`Androidx`, 所以如果不是Androidx的话, 引入会有`兼容性`问题(会报错!). 
- 版本: minSdkVersion `19`

以下默认是`Androidx` 项目下引入

先在 build.gradle(Project:XXXX) 的 repositories 添加:
```Java
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
然后在 build.gradle(Module:app) 的 dependencies 添加:

```
implementation 'com.hyzhan:KtArmor:1.0.7' //最新版本
```

# 快速上手

我们先从一个 简单登录功能来熟悉一下 `KtArmor-MVVM` 使用流程。

## LoginActivity
```kotlin
class LoginActivity : AppCompatActivity(), IMvmActivity {

    @BindViewModel
    lateinit var viewModel: LoginViewModel

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initListener() {
        mBtnLogin.setOnClickListener {
            viewModel.login(mEtAccount.str(), mEtPassword.str())
        }
    }

    override fun dataObserver() {
        viewModel.loginData.observe(this, Observer {
            toast("登录成功")
        })
    }
}
```

- 1、新建一个 `LoginActivity` 类，并实现 `IMvmActivity` `“接口”`。  

- 2、然后使用 `@BindViewModel` 绑定一个 ViewModel，KtArmor-MVVM 会自动注入赋值。

- 3、getLayoutId()方法，返回一个 R.layout.activity_login （布局id）

- 4、initListener()方法中设置 登录按钮事件 (mBtnLogin)，通过 `viewModel` 来发起网络请求。

- 5、然后 在dataObserver（）监听回调的结果即可。

然后我们再来看看， VM 层如何实现，也就是 LoginViewModel。

## LoginViewModel
```kotlin
class LoginViewModel : BaseViewModel<LoginRepository>() {

    val loginData = MutableLiveData<LoginRsp>()

    fun login(account: String, password: String) {
        
        // 校验参数
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            showToast(R.string.account_or_password_empty)
            return
        }

        // DSL 方式发起 网络请求
        quickLaunch<LoginRsp> {

            onStart { showLoading() }
            
            request { repository.login(account, password) }

            onSuccess { loginData.value = it }
        }
    }
}
```
- 需创建 LoginViewModel类，继承`BaseViewModel`，并传入 `LoginRepository`，自动注入 LoginRepositoy 实例。
- `quickLaunch<XXX>` 方法是使用 `DSL` 方式发起网络请求
- `repository` login 方法真正发起网络请求
- `onSuccess()` 方法 监听回调结果，并设置给 `LoginData`

最后，我们来看看 Repository 的处理。

## LoginRepository

```kotlin
// 注意这里！
@BaseUrl(API.BASE_URL)
interface ApiService {
    @POST(API.LOGIN)
    suspend fun login(@Query("username") username: String,
                      @Query("password") password: String): BaseResponse<LoginRsp>
}

// ServiceFactory.kt
object ServiceFactory {
    // 初始化 ApiService
    val apiService by lazy { RetrofitFactory.create(ApiService::class.java) }
}

// LoginRepository.kt
class LoginRepository {
    suspend fun login(account: String, password: String): BaseResponse<LoginRsp> {
        return ServiceFactory.apiService.login(account, password)
    }
}
```
- 创建一个 ApiService，这里需要在class 类上 设置 `@BaseUrl` 注解，value 值为 baseUrl，其他按照正常 `retrofit` 使用即可。
- 然后使用 `RetrofitFactory.create()` 来创建对应 ApiService。RetrofitFactory封装了`Retrofit` 创建流程。
- 最后 在LoginRepository 中即可使用 ServiceFactory.apiService.login 来发起网络请求即可。

# 期待

`KtArmor-MVVM` 框架是一款小而美的框架，也是我个人经验的积累, 总结，希望大家喜欢。  

如果你有更好的建议欢迎 pr，issues 一起交流学习。

如有不妥, 望各位大佬指出。

# 示例项目

## [Kotlin 版 玩Android](https://github.com/hyzhan43/PlayAndroid/tree/KtArmor-MVVM)

# 其他相关

### [Kotlin的魔能机甲——KtArmor（一）](https://juejin.im/post/5d319e3651882540be3a2be7)

### [Kotlin的魔能机甲——KtArmor插件篇（二）](https://juejin.im/post/5d3eb9e7f265da03a31d1072)

### [Kotlin的魔能机甲——KtArmor（三）](https://juejin.im/post/5d4a872e51882575595c40f1)

### [Kotlin的魔能机甲——KtArmor网络调用封装(四)](https://juejin.im/post/5da1a753f265da5b932e6f5b)
