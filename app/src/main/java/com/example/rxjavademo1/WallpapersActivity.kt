package com.example.rxjavademo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class WallpapersActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureLayout()
        createObservable()
    }

    /**
     * Configure Screen Layout
     */

    private fun configureLayout() {

        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    /**
     * Configure Viewpager Adapter & Timer
     * Default time interval is 3 seconds.
     */
    private fun configureViewPager(wallpapers: List<WallpaperDo>) {

        var currentPage = 0

        var adapter = WallpapersPagerAdapter(applicationContext, wallpapers);
        viewPager.adapter = adapter

        // Timer operator for Auto scroll of images
        Observable.interval(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: Long) {
                    if (currentPage === wallpapers.size-1) {
                        currentPage = 0
                    }
                    viewPager.setCurrentItem(currentPage++, true)
                }

                override fun onError(e: Throwable) {}
            })
    }

    /**
     * Fetching images from assets.
     */
    private fun getImagesFromAssets(): ArrayList<WallpaperDo> {
        var wallpapers: ArrayList<WallpaperDo>? = ArrayList()
        var assetsList: Array<String>? = assets.list("")
        for (str in assetsList!!) {

            var wallpaperDo = WallpaperDo()
            wallpaperDo.image = "file:///android_asset/$str"

            wallpapers!!.add(wallpaperDo)
        }

        return wallpapers!!;
    }

    /**
     *  Observable to fetch images from assets.
     */
    private fun createObservable() {

        var listObservable: Observable<List<WallpaperDo>> = Observable.just(getImagesFromAssets())
        listObservable.subscribe(object :
            Observer<List<WallpaperDo>> {
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(list: List<WallpaperDo>) {
                configureViewPager(list)
            }

            override fun onError(e: Throwable) {}
        })
    }
}
