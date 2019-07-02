# 前言
学习了Kotlin有一段时间了, 每次写项目/Demo的时候, 总是用到`网络请求`、`MVP`、`MVVM`、`常用工具类`、`通用自定义View`, 索性把这些整合到一起, 搭成一个Android的脚手架——KtArmor. 框架是我个人经验的积累, 总结. 如有不妥, 望各位大佬指出.

# 什么是KtArmor ?

`KtArmor` 寓意着 为Android 赋予`战斗装甲`, 方便开发者快速进行Android 开发。节约开发者开发时间。为了满足开发者需求, 我整合了两个分支, 分别对应着 `MVP`, `MVVM`.

* MVP分支  
架构模式: `MVP` + `Kotlin`  
网络请求: `RxJava` + `Retrofit` + `Okhttp`

* MVVM分支  
架构模式: `MVVM`+ `Androidx` + `Kotlin` + `LiveData` + `ViewModel`  
网络请求: `Coroutines` + `Retrofit` + `Okhttp`

# MVP框架引入

# MVVM框架引入  

## 注意！
由于采用了`Androidx`, 所以如果不是Androidx的话, 引入会有`兼容性`问题(会报错!). 

版本: minSdkVersion `19`

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
implementation 'com.hyzhan:KtArmor:1.0.0'
```

# 使用
在Application 中初始化KtArmor 框架
```Kotlin
class BaseApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        // 初始化KtArmor
        KtArmor.init(this, API.BASE_URL)
    }
}
```

第二个参数为 网络请求的 `baseUrl`  
第三、四、五 是网络连接相关可选参数, 可以根据需要进行修改(一般默认就行)
* connectTime
* readTime
* writeTime


# Login 示例
新建LoginActivity 继承`LifecycleActivity`基类, 并传入`ViewModel` 泛型(`AccountViewModel`), 并
```Kotlin
class LoginActivity : LifecycleActivity<AccountViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {

        mBtnLogin.setOnClickListener {
            viewModel.login(mEtAccount.str(), mEtPassword.str())
        }
    }

    override fun dataObserver() {
        viewModel.loginData.observe(this, Observer {
            toast(it?.errorMsg ?: "错误")
        })
    }
}
```
当点击登陆按钮的时候, 通过`viewModel`发起请求,
viewModel 是传入`AccountViewModel`的实例, 通过`反射`生成, 直接使用即可
```Java
viewModel.login(mEtAccount.str(), mEtPassword.str())
```
在AccountViewModel里, 继承基类`BaseViewModel`, 并传递对应的Repository(`AccountRepository`)

```Kotlin
class AccountViewModel : BaseViewModel<AccountRepository>() {

    val loginData = MutableLiveData<BaseResponse<LoginRsp>>()

    fun login(account: String, password: String) {

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            sharedData.value = SharedData(strRes = R.string.account_or_password_empty)
        } else {
            launchUI { loginData.value = repository.login(account, password) }
        }
    }
}
```
login方法里, 进行account 和password校验, 可以根据 `sharedData`进行统一数据处理, 并会回调对应的方法, 如下默认`SharedData`是 `Tips`类型.

```Java
sharedData.value = SharedData(strRes = R.string.account_or_password_empty)
```
`Tips`类型 会回调`showTips`方法, 在对应Activity中, 可以复写父类方法自行处理.默认是`toast`
```Kotlin
open fun showTips(@StringRes strRes: Int) {
        toast(getString(strRes))
    }
```
再者就是调用 repository层发起请求, `AccountRepository` 需要继承 `ApiRepository`

```Kotlin
class AccountRepository : ApiRepository() {

    suspend fun login(account: String, password: String): BaseResponse<LoginRsp> {

        // TODO local DB

        // network
        return apiService.login(account, password).await()
    }
}
```
在这里可以分别进行`数据库请求`, 或者`网络请求`.由于是Login示例这里就只演示网络请求.

```Java
interface ApiService {

    @POST(API.LOGIN)
    fun login(@Query("username") username: String,
              @Query("password") password: String): Deferred<BaseResponse<LoginRsp>>
}
```
以上是Login示例, 若要看完整实例代码, 可以查看MVVM分支, 对应app模块下有完整代码.

# 未完待续~