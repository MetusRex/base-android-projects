package pl.bakm.testapp.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by marcinbak on 15.06.13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonModel {

    private final static String TAG_FEED = "feed";
    private final static String TAG_META = "meta";

    @JsonProperty(TAG_FEED)
    private List<FeedElement> mFeedElements;

    @JsonProperty(TAG_META)
    private FeedMeta mMeta;

    public List<FeedElement> getFeedElements() {
        return mFeedElements;
    }

    public void setFeedElements(List<FeedElement> feedElements) {
        mFeedElements = feedElements;
    }

    public FeedMeta getMeta() {
        return mMeta;
    }

    public void setMeta(FeedMeta meta) {
        mMeta = meta;
    }
}
