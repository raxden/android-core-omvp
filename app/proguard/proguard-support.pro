-dontwarn android.support.**

-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }

-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

-keep class android.support.v13.app.** { *; }
-keep interface android.support.v13.app.** { *; }