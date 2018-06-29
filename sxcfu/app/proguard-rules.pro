# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Administrator\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 指定代码的压缩级别
-optimizationpasses 5
# 不使用大小写混合
-dontusemixedcaseclassnames
# 混淆第三方jar
-dontskipnonpubliclibraryclasses
# 混淆时不做预校验
-dontpreverify
 # 混淆时记录日志
-verbose
 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 保持哪些类不被混淆：四大组件，应用类，配置类等等
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.FragmentActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.**
-keep public class com.android.vending.licensing.ILicensingService


# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

 # 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

# 保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}



# 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 这个主要是在layout中写的onclick方法android:onclick="onClick"，不进行混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
 }

#保持注解
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
-keepattributes Signature


###====================华丽丽的分割线=================================###

#jpush v2.0.5 及以上的版本由于引入了protobuf 和 gson ，在上面基础之上增加排除混淆的配置。
#==================gson==========================
-keep class com.google.gson.** {*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.** {
    <fields>;
    <methods>;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-dontwarn com.google.gson.**

#==================protobuf======================
-dontwarn com.google.**
-keep class com.google.protobuf.** {*;}

-ignorewarnings
-dontwarn io.netty.**
-dontwarn com.umeng.analytics.**
-dontwarn org.apache.shiro.**
-dontwarn org.androidannotations.**
-dontwarn demo.**
-dontwarn com.alibaba.fastjson.**
-dontwarn com.aps.**
-dontwarn com.google.gson.**
-dontwarn com.sun.codemodel.**
-dontwarn u.aly.**



##AMap_V2.0 Location
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#=============umeng=============================
-dontwarn com.umeng.**
-keep class com.umeng.**{*;}
-keep class u.aly.**{*;}
-keep class com.google.**{*;}

#==================eventbus======================
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#==================glide======================
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

#=====================okhttp=====================
-dontwarn okhttp3.**

#==================retrofit======================
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

#==================retrolambda======================
-dontwarn java.lang.invoke.*

#==================rxjava======================
-dontwarn rx.**
-keep class rx.** { *; }

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#==================butterknife======================
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#==================webview======================
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmapx);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
#===================XMPUSH==============
-keep class com.xianglin.app.receiver.**{*;}

#==================webview upload file===================
-keepclassmembers class * extends android.webkit.WebChromeClient {
     public void openFileChooser(...);
     public void onShowFileChooser(...);
}

#SweetAlertDialog
-keep class cn.pedant.SweetAlert.** { *; }
