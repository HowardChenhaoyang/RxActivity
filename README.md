# RxActivity
android  onActivityResult的优雅封装

问题：
activity.startActivityForResult的回调在activity的onActivityResult中进行。
如下：
```
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
      super.onActivityResult(requestCode, resultCode, data)
      //结果处理
   }
```
随着代码的复杂化这会带来一个问题：比如我在一个非Activity的类里调用startActivityForResult，那么回掉还得必须在Activity中进行处理，代码逻辑会突然中断。

因此，重点在于如何监听activity的onActivityResult的回调。

解决方案1：可以写一个BaseActivity,该BaseActivity维持一个ActivityCallBack的引用，提供方法供外部实现。

伪代码：
```
interface ActivityCallBack{
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}
```
```
class BaseActivity: AppCompatActivity() {
    var activityCallBack: ActivityCallBack? = null
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityCallBack?.onActivityResult(requestCode, resultCode, data)
    }
}
```

但是这样的话，对原代码具有侵入型。需要原代码的所有activity都具有共同父类，还得修改原代码的BaseActivity。因此还得进一步寻求解决办法。

解决方案2：fragment拥有自己的生命周期和onActivityResult回调。我们可以借助fragment来实现对activity的生命周期的监听。可以clone本项目查看具体实现。

使用方法：
```
startActivityWithCallBack(Intent(this, SecondActivity::class.java), 1) { callBackWrapper ->
    //处理回调
}
```

附：1. glide中对此也有具体的应用，通过借助fragment，在activity销毁之后，停止对图片的网络请求。
   2. 6.0之后的动态权限获取也可以采用此方法。
