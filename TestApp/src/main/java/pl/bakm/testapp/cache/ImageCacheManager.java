package pl.bakm.testapp.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by marcinbak on 16.06.13.
 */
public class ImageCacheManager {

    private static ImageCacheManager mInstance;

    /**
     * Volley image loader
     */
    private ImageLoader mImageLoader;

    /**
     * Image cache implementation
     */
    private ImageLoader.ImageCache mImageCache;

    /**
     * @return instance of the cache manager
     */
    public static ImageCacheManager getInstance() {
        if (mInstance == null)
            mInstance = new ImageCacheManager();

        return mInstance;
    }

    /**
     * Initializer for the manager. Must be called prior to use.
     *
     * @param cacheSize      max size for the cache
     */
    public void init(RequestQueue requestQueue,int cacheSize) {
        mImageCache = new BitmapLruImageCache(cacheSize);
        mImageLoader = new ImageLoader(requestQueue, mImageCache);
    }

    public Bitmap getBitmap(String url) {
        try {
            return mImageCache.getBitmap(createKey(url));
        } catch (NullPointerException e) {
            throw new IllegalStateException("Disk Cache Not initialized");
        }
    }

    public void putBitmap(String url, Bitmap bitmap) {
        try {
            mImageCache.putBitmap(createKey(url), bitmap);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Disk Cache Not initialized");
        }
    }


    /**
     * Executes and image load
     *
     * @param url      location of image
     * @param listener Listener for completion
     */
    public void getImage(String url, ImageLoader.ImageListener listener) {
        mImageLoader.get(url, listener);
    }

    /**
     * @return instance of the image loader
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    /**
     * Creates a unique cache key based on a url value
     *
     * @param url url to be used in key creation
     * @return cache key value
     */
    private String createKey(String url) {
        return String.valueOf(url.hashCode());
    }

}