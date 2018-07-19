#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_p_xuyabo_ndkdemoapplication_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello22 from C++";
    return env->NewStringUTF(hello.c_str());
}
