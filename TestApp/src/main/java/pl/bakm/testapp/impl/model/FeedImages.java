package pl.bakm.testapp.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by marcinbak on 16.06.13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedImages {

    private final static String TAG_POSTER = "poster";

    @JsonProperty(TAG_POSTER)
    private FeedPoster mPoster;

    public FeedPoster getPoster() {
        return mPoster;
    }

    public void setPoster(FeedPoster poster) {
        mPoster = poster;
    }
}
