# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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
#防止UMShare和Weixin jar被混淆
-dontusemixedcaseclassnames
	-dontshrink
	-dontoptimize
	-dontwarn com.google.android.maps.**
	-dontwarn android.webkit.WebView
	-dontwarn com.umeng.**
	-dontwarn com.tencent.weibo.sdk.**
	-dontwarn com.facebook.**
	-keep public class javax.**
	-keep public class android.webkit.**
	-dontwarn android.support.v4.**
	-keep enum com.facebook.**
	-keepattributes Exceptions,InnerClasses,Signature
	-keepattributes *Annotation*
	-keepattributes SourceFile,LineNumberTable

	-keep public interface com.facebook.**
	-keep public interface com.tencent.**
	-keep public interface com.umeng.socialize.**
	-keep public interface com.umeng.socialize.sensor.**
	-keep public interface com.umeng.scrshot.**
	-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
	-keep public class com.umeng.socialize.* {*;}


	-keep class com.facebook.**
	-keep class com.facebook.** { *; }
	-keep class com.umeng.scrshot.**
	-keep public class com.tencent.** {*;}
	-keep class com.umeng.socialize.sensor.**
	-keep class com.umeng.socialize.handler.**
	-keep class com.umeng.socialize.handler.*
	-keep class com.umeng.weixin.handler.**
	-keep class com.umeng.weixin.handler.*
	-keep class com.umeng.qq.handler.**
	-keep class com.umeng.qq.handler.*
	-keep class UMMoreHandler{*;}
	-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
	-keep class com.tencent.mm.sdk.modelmsg.** implements 	com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
	-keep class im.yixin.sdk.api.YXMessage {*;}
	-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
	-keep class com.tencent.mm.sdk.** {
  	 *;
	}
	-keep class com.tencent.mm.opensdk.** {
   *;
	}
	-dontwarn twitter4j.**
	-keep class twitter4j.** { *; }

	-keep class com.tencent.** {*;}
	-dontwarn com.tencent.**
	-keep public class com.umeng.com.umeng.soexample.R$*{
    public static final int *;
	}
	-keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
		}
	-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
	}

	-keep class com.tencent.open.TDialog$*
	-keep class com.tencent.open.TDialog$* {*;}
	-keep class com.tencent.open.PKDialog
	-keep class com.tencent.open.PKDialog {*;}
	-keep class com.tencent.open.PKDialog$*
	-keep class com.tencent.open.PKDialog$* {*;}

	-keep class com.sina.** {*;}
	-dontwarn com.sina.**
	-keep class  com.alipay.share.sdk.** {
	   *;
	}
	-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
	}

	-keep class com.linkedin.** { *; }
	-keepattributes Signature

#地区3级联动选择器

-keep class com.lljjcoder.**{
	*;
	}
#避免混淆Bugly，在Proguard混淆文件中增加以下配置

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}
-dontwarn com.yanzhenjie.permission.**

-dontwarn com.lwkandroid.imagepicker.**
-keep class com.lwkandroid.imagepicker.**{*;}

-keepclassmembers class ** {
    public void on*Event(...);
}
-keep class c.t.**{*;}
-keep class com.tencent.map.geolocation.**{*;}
-keep class com.tencent.tencentmap.lbssdk.service.**{*;}


-dontwarn  org.eclipse.jdt.annotation.**
-dontwarn  c.t.**

#腾讯地图 2D sdk
-keep class com.tencent.mapsdk.**{*;}
-keep class com.tencent.tencentmap.**{*;}

#腾讯地图 3D sdk
-keep class com.tencent.tencentmap.**{*;}
-keep class com.tencent.map.**{*;}
-keep class com.tencent.beacontmap.**{*;}
-keep class navsns.**{*;}
-dontwarn com.qq.**
-dontwarn com.tencent.beacon.**

#腾讯地图检索sdk
-keep class com.tencent.lbssearch.**{*;}
-keep class com.google.gson.examples.android.model.** { *; }

-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn com.meizu.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class com.meizu.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}

# RongCloud SDK
-keep class io.rong.** {*;}
-keep class * implements io.rong.imlib.model.MessageContent {*;}
-dontwarn io.rong.push.**
-dontnote com.xiaomi.**
-dontnote com.google.android.gms.gcm.**
-dontnote io.rong.**
-keep public class * extends android.content.BroadcastReceiver
-keep class com.paobuqianjin.pbq.step.model.broadcast.RongYunMessageReceiver {*;}

# Location
-keep class com.amap.api.**{*;}
-keep class com.amap.api.services.**{*;}
#轮播
-keep class com.youth.banner.** {
    *;
 }
#阿里云oss
-keep class com.alibaba.sdk.android.oss.** { *; }
-dontwarn okio.**
-dontwarn org.apache.commons.codec.binary.**