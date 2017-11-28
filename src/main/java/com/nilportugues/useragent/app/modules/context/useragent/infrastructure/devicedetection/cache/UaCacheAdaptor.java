package com.nilportugues.useragent.app.modules.context.useragent.infrastructure.devicedetection.cache;

import com.nilportugues.useragent.app.modules.context.useragent.infrastructure.devicedetection.cache.CachingConfiguration.GuavaCacheAdaptor;
import fiftyone.mobile.detection.cache.ILoadingCache;
import fiftyone.mobile.detection.cache.IValueLoader;

import java.io.IOException;

/**
 * The user agent cache used by the Provider requires a cache that implements ILocadingCache.
 * This class extends the Guava cache adapter to allow it to be used as a loading cache.
 */
public class UaCacheAdaptor<K, V> extends GuavaCacheAdaptor<K, V> implements ILoadingCache<K, V> {

    public UaCacheAdaptor(com.google.common.cache.Cache<K, V> cache) {
        super(cache);
    }

    @Override
    public V get(K key, IValueLoader<K, V> loader) throws IOException {
        V result = get(key);
        if (result == null) {
            result = loader.load(key);
            if (result != null) {
                cache.put(key, result);
            }
        }
        return result;
    }

    @Override
    public V get(K key) {
        return cache.getIfPresent(key);
    }
}
