#include <jni.h>
#include <string>

// Decryption KEY
extern "C"
JNIEXPORT jstring JNICALL
Java_image_gallery_organize_Helper_Constant_getKey1(JNIEnv *env, jobject thiz) {
    // jitendra test iv gtHpvQWrV1PET91L
    // test encryopted iv BAF0A55FA4FA828F3A3AC5CAC97DB3930CC8EE72A457C3AAA35F79627FD13728

    std::string hello = "BAF0A55FA4FA828F3A3AC5CAC97DB3930CC8EE72A457C3AAA35F79627FD13728";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_image_gallery_organize_Helper_Constant_getKey2(JNIEnv *env, jobject instance) {

    // jitendra test key ApsYSCwCSmnLs8pk
    // test encrypted key 09850CCAEA3EFD27D5AEA7B6A7A862AB0CC8EE72A457C3AAA35F79627FD13728

    std::string hello = "09850CCAEA3EFD27D5AEA7B6A7A862AB0CC8EE72A457C3AAA35F79627FD13728";
    return env->NewStringUTF(hello.c_str());
}




extern "C"
JNIEXPORT jstring JNICALL
Java_image_gallery_organize_Helper_Constant_getBase(JNIEnv *env,jobject instance) {
//jitendra insert api url link
    std::string hello = "https://gallery1.hallao.com/app/api/";
    return env->NewStringUTF(hello.c_str());
}



