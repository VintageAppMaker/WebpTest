## animated webp, gif using GlideApp

> Glide를 사용하여 webp, gif animated 적용하기

gradle 설정

~~~

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'

    def glide_version = '4.8.0'
    implementation "com.github.bumptech.glide:glide:$glide_version"
    implementation "com.zlc.glide:webpdecoder:1.6.$glide_version"
    kapt "com.github.bumptech.glide:compiler:$glide_version"

}


~~~

GlideApp을 사용하기 위해서
~~~kt

package com.psw.adsloader.webptest

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

// GlideApp을 사용하려면 이렇게 빈클래스를 정의해야 한다.
// 뭔가 괴상함.
@GlideModule
class MyAppGlideModule : AppGlideModule()

~~~

예제코드

~~~kt

    fun loadWithWebp(){
        GlideApp.with(this)
            .asDrawable()
            .load("https://raw.githubusercontent.com/VintageAppMaker/WebpTest/master/app/src/main/assets/test.webp")
            .into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    imageView.setImageDrawable(resource)
                    if (resource is Animatable) {
                        (resource as Animatable).start()
                    }
                }
            })
    }

    fun loadWithGif(){
        GlideApp.with(this).asGif()
            .load("https://github.com/VintageAppMaker/WebpTest/blob/master/app/src/main/assets/test.gif?raw=true")
            .into(object : SimpleTarget<GifDrawable>() {
                override fun onResourceReady(resource: GifDrawable, transition: Transition<in GifDrawable>?) {
                    imageView.setImageDrawable(resource)
                    resource.start()
                }
            })
    }

~~~