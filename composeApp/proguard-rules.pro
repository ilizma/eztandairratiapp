### Firebase
-keepattributes Signature
-keepattributes *Annotation*                            # Keep Crashlytics anotations
-keepattributes SourceFile,LineNumberTable              # Keep file names/lines numbers
-keep public class * extends java.lang.Exception        # Keep custom exceptions
-keep class com.google.firebase.** { *; }
-keep class com.crashlytics.** { *; }                   # Ensures faster builds with Crashlytics
-dontwarn com.crashlytics.**

-dontwarn org.jetbrains.annotations.**
-keep class kotlin.Metadata { *; }

-keep class com.ilizma.api.model.** { *; }
-keep class com.ilizma.schedule.data.model.** { *; }