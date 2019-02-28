#include <jni.h>
#include <string>
#include <android/log.h>


extern "C"
JNIEXPORT jstring

JNICALL
Java_com_p_xuyabo_ndkdemoapplication_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "cpp2Hello from C++";
    __android_log_print(ANDROID_LOG_INFO, "hello-libs::", "1222");
    return env->NewStringUTF(hello.c_str());
}
