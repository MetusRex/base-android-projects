package pl.bakm.testapp.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by marcinbak on 16.06.13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedPoster {

    private final static String TAG_WIDTH_380 = "w380";
    private final static String TAG_WIDTH_180 = "w180";
    private final static String TAG_WIDTH_120 = "w120";

    @JsonProperty(TAG_WIDTH_380)
    private String mPosterUrl380;

    @JsonProperty(TAG_WIDTH_180)
    private String mPosterUrl180;

    @JsonProperty(TAG_WIDTH_120)
    private String mPosterUrl120;

    public String getPosterUrl380() {
        return mPosterUrl380;
    }

    public void setPosterUrl380(String posterUrl380) {
        mPosterUrl380 = posterUrl380;
    }

    public String getPosterUrl180() {
        return mPosterUrl180;
    }

    public void setPosterUrl180(String posterUrl180) {
        mPosterUrl180 = posterUrl180;
    }

    public String getPosterUrl120() {
        return mPosterUrl120;
    }

    public void setPosterUrl120(String posterUrl120) {
        mPosterUrl120 = posterUrl120;
    }
}
