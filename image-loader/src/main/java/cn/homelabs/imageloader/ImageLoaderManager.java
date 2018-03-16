package cn.homelabs.imageloader;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import cn.homelabs.imageloader.glide.GlideImageLoader;
import okhttp3.OkHttpClient;

/**
 * Created by ROOT on 2017/7/27.
 */

public class ImageLoaderManager implements ImageLoader {

    private static volatile ImageLoaderManager _instance;
    private Config config;
    private ImageLoader imageLoader;

    private ImageLoaderManager() {
        imageLoader = new GlideImageLoader();
    }

    public static ImageLoaderManager get() {
        if (_instance == null) {
            synchronized (ImageLoaderManager.class) {
                if (_instance == null) {
                    _instance = new ImageLoaderManager();
                }
            }
        }
        return _instance;
    }

    public void config(Config config) {
        this.config = config;
    }

    public OkHttpClient okHttpClient() {
        return this.config != null ? this.config.okHttpClient : null;
    }

    public void setImageLoader(ImageLoader loader) {
        if (loader != null) {
            this.imageLoader = loader;
        }
    }

    public void showImage(@NonNull View view, @NonNull Object model) {
        imageLoader.showImage(view, model, null);
    }

    public void showImage(@NonNull View view, @DrawableRes int resId) {
        imageLoader.showImage(view, resId, null);
    }


    @Override
    public void showImage(@NonNull View view, @NonNull Object model, @Nullable ImageLoaderOptions options) {
        imageLoader.showImage(view, model, options);
    }

    @Override
    public void resume(@NonNull Context context) {
        imageLoader.resume(context);
    }

    @Override
    public void pause(@NonNull Context context) {
        imageLoader.pause(context);
    }


    public static class Config {
        private OkHttpClient okHttpClient;

        public Config() {
        }

        public Config okHttpClient(OkHttpClient client) {
            this.okHttpClient = client;
            return this;
        }
    }
}
