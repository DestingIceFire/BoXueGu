-optimizationpasses 5    #指定代码的压缩级别
-dontusemixedcaseclassnames  #是否使用大小写混合
-dontpreverify  # 混淆时是否做预校验
-verbose    #混淆时是否记录日志
# 这个过滤器是谷歌推荐的算法，一般不改变
-optimizations !code/simplification/artithmetic,!field/*,!class/merging/*
# 保留了继承自Activity、Application这些类的子类
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends android.database.sqlite.SQLiteOpenHelper{*;}
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * implements androidx.versionedparcelable.VersionedParcelable

-keepclasseswithmembernames class * {  # 保持native方法不被混淆
    native <methods>;
}

-keepclasseswithmembers class * { # 保持自定义控件类不被混淆
    public <init>(android.content.Cintext, android.util.AttributeSet);
}

-keepclasseswithmembers class * { # 保持自定义控件类不被混淆
    public <init>(android.content.Cintext, android.util.AttributeSet,int);
}

-keepclassmembers class * extends android.App.Activity{
    public void * (android.view.View);
}

# 枚举类不能被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留Parcelable序列化的类不能被混淆
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
}