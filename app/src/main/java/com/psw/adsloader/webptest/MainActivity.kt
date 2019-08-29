package com.psw.adsloader.webptest

import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fn : ( () -> Unit )?  = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGif.setOnClickListener { loadWithGif(); fn = null }
        btnWebp.setOnClickListener{
            fn = fn ?: loadWithWebp()
            fn!!()
        }
    }

    fun loadWithWebp() : () -> Unit{
        var res: Animatable? = null

        return fun () {

            // 읽었다면 실행, 멈춤
            if(res != null ){
                if ( res!!.isRunning ){
                    res!!.stop()
                    toast("webp stop")
                } else {
                    res!!.start()
                    toast("webp start(continue)")
                }
                return
            }

            // 처음이면 읽어오기
            GlideApp.with(this)
                .asDrawable()
                .load("https://raw.githubusercontent.com/VintageAppMaker/WebpTest/master/app/src/main/assets/test.webp")
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        imageView.setImageDrawable(resource)
                        if (resource is Animatable) {
                            res = resource
                            (resource as Animatable).start()
                            toast("webp start(first)")
                        }
                    }
                })
        }
    }

    fun loadWithGif(){
        GlideApp.with(this).asGif()
            .load("https://github.com/VintageAppMaker/WebpTest/blob/master/app/src/main/assets/test.gif?raw=true")
            .into(object : SimpleTarget<GifDrawable>() {
                override fun onResourceReady(resource: GifDrawable, transition: Transition<in GifDrawable>?) {
                    imageView.setImageDrawable(resource)
                    resource.start()
                    toast("gif start")
                }
            })
    }

    fun Context?.toast (s : String ){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }


}
