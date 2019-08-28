package com.psw.adsloader.webptest

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadWithWebp()

    }

    override fun onStart() {
        super.onStart()
    }

    fun loadWithWebp(){
        GlideApp.with(this)
            .asDrawable()
            .load("https://res.cloudinary.com/demo/image/upload/fl_awebp/bored_animation.webp")
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
            .load("https://res.cloudinary.com/demo/image/upload/bored_animation.gif")
            .into(object : SimpleTarget<GifDrawable>() {
                override fun onResourceReady(resource: GifDrawable, transition: Transition<in GifDrawable>?) {
                    imageView.setImageDrawable(resource)
                    resource.start()
                }
            })
    }


}
