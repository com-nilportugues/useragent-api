package com.nilportugues.useragent.app.modules.context.useragent.infrastructure.devicedetection;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.nilportugues.useragent.app.modules.context.useragent.infrastructure.devicedetection.cache.GuavaCacheBuilder;
import com.nilportugues.useragent.app.modules.context.useragent.infrastructure.devicedetection.cache.UaCacheAdaptor;
import fiftyone.mobile.detection.Dataset;
import fiftyone.mobile.detection.DatasetBuilder;
import fiftyone.mobile.detection.Match;
import fiftyone.mobile.detection.Provider;
import fiftyone.mobile.detection.cache.ICacheBuilder;

import javax.inject.Named;
import javax.inject.Singleton;
import java.net.URL;
import java.util.Date;

@Singleton
@Named
public class DeviceDetectionService {

    private static final String DEGREES_LITE_DAT = "51Degrees-LiteV3.2.dat";
    private final Provider provider;

    @SuppressWarnings("unchecked")
    public DeviceDetectionService() throws Exception {
        final URL resource = getClass().getClassLoader().getResource(DEGREES_LITE_DAT);

        final ICacheBuilder builder = new GuavaCacheBuilder();

        final Dataset dataset = DatasetBuilder.file()
            .configureCachesFromCacheSet(DatasetBuilder.CacheTemplate.MultiThreadLowMemory)
            .setCacheBuilder(DatasetBuilder.CacheType.NodesCache, builder)
            .setCacheBuilder(DatasetBuilder.CacheType.ProfilesCache, null)
            .lastModified(new Date())
            .build(resource.getFile());

        final Cache uaCache = CacheBuilder.newBuilder()
            .initialCapacity(100000)
            .maximumSize(100000)
            .concurrencyLevel(5) // set to number of threads that can access cache at same time
            .build();

        provider = new Provider(dataset, new UaCacheAdaptor(uaCache));
    }

    public Match detectDeviceFromUserAgent(String userAgent) throws Exception {
        return provider.match(userAgent);
    }
}
