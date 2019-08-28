package com.psw.adsloader.webptest

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

// GlideApp을 사용하려면 이렇게 빈클래스를 정의해야 한다.
// 뭔가 괴상함.
@GlideModule
class MyAppGlideModule : AppGlideModule()