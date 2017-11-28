package com.nilportugues.useragent.app.modules.context.useragent.infrastructure.devicedetection.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fiftyone.mobile.detection.cache.ICache;
import fiftyone.mobile.detection.cache.ICacheBuilder;

/**
 * For a cache to be used by the device detection API, it also needs a corresponding builder
 * that implements the ICacheBuidler interface.
 *
 * This is the ICacheBuilder implementation for the Guava cache.
 */
public class GuavaCacheBuilder implements ICacheBuilder {

    @SuppressWarnings("unchecked")
    public ICache build(int size) {
        Cache guavaCache = CacheBuilder.newBuilder()
            .initialCapacity(size)
            .maximumSize(size)
            .concurrencyLevel(5) // set to number of threads that can access cache at same time
            .build();

        return new CachingConfiguration.PutCacheAdaptor(guavaCache);
    }
}
