package com.nilportugues.useragent.app.modules.context.useragent.infrastructure.devicedetection.cache;

import com.google.common.cache.Cache;
import fiftyone.mobile.detection.cache.ICache;
import fiftyone.mobile.detection.cache.IPutCache;

public class CachingConfiguration {

    @SuppressWarnings("WeakerAccess")

    /**
     * Class adapting a google Guava cache to the 51 Degrees ICache interface.
     * This allows it to be used by the device detection API.
     */
    public static class GuavaCacheAdaptor<K, V> implements ICache<K, V> {
        protected final Cache<K, V> cache;

        public GuavaCacheAdaptor(Cache<K, V> cache) {
            this.cache = cache;
        }

        @Override
        public V get(K key) {
            return cache.getIfPresent(key);
        }

        @Override
        public long getCacheSize() {
            return cache.size();
        }

        @Override
        public long getCacheMisses() {
            return cache.stats().missCount();
        }

        @Override
        public long getCacheRequests() {
            return cache.stats().requestCount();
        }

        @Override
        public double getPercentageMisses() {
            return getCacheMisses() / getCacheRequests();
        }

        @Override
        public void resetCache() {
            cache.invalidateAll();
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static class PutCacheAdaptor<K, V> extends GuavaCacheAdaptor<K, V> implements IPutCache<K, V> {

        public PutCacheAdaptor(Cache<K, V> cache) {
            super(cache);
        }

        @Override
        public void put(K key, V value) {
            cache.put(key, value);
        }
    }

}
