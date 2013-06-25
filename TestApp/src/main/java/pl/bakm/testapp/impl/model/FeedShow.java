package pl.bakm.testapp.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by marcinbak on 15.06.13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedShow {

    private final static String TAG_ID = "id";
    private final static String TAG_EPISODE_ID = "episode_id";
    private final static String TAG_TITLE = "title";
    private final static String TAG_IMAGES = "images";

    @JsonProperty(TAG_ID)
    private int mId;

    @JsonProperty(TAG_EPISODE_ID)
    private int mEpisodeId;

    @JsonProperty(TAG_TITLE)
    private String mTitle;

    @JsonProperty(TAG_IMAGES)
    private FeedImages mImages;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getEpisodeId() {
        return mEpisodeId;
    }

    public void setEpisodeId(int episodeId) {
        mEpisodeId = episodeId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public FeedImages getImages() {
        return mImages;
    }

    public void setImages(FeedImages images) {
        mImages = images;
    }
}
