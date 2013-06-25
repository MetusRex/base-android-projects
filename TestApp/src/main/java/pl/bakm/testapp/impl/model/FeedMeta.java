package pl.bakm.testapp.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by marcinbak on 15.06.13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedMeta {

    private final static String TAG_CACHE_KEY = "cachekey";
    private final static String TAG_MORE = "more";
    private final static String TAG_TOTAL = "total";

    @JsonProperty(TAG_CACHE_KEY)
    private String mCacheKey;

    @JsonProperty(TAG_TOTAL)
    private int mTotal;

    @JsonProperty(TAG_MORE)
    private boolean mMore;

    public String getCacheKey() {
        return mCacheKey;
    }

    public void setCacheKey(String cacheKey) {
        mCacheKey = cacheKey;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        mTotal = total;
    }

    public boolean isMore() {
        return mMore;
    }

    public void setMore(boolean more) {
        mMore = more;
    }

}
