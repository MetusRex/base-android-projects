package pl.bakm.testapp.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by marcinbak on 16.06.13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedAvatar {

    private final static String TAG_WIDTH_25 = "w25";
    private final static String TAG_WIDTH_50 = "w50";
    private final static String TAG_WIDTH_80 = "w80";
    private final static String TAG_WIDTH_150 = "w150";

    @JsonProperty(TAG_WIDTH_25)
    private String mAvatarUrl25;

    @JsonProperty(TAG_WIDTH_50)
    private String mAvatarUrl50;

    @JsonProperty(TAG_WIDTH_80)
    private String mAvatarUrl80;

    @JsonProperty(TAG_WIDTH_150)
    private String mAvatarUrl150;

    public String getAvatarUrl25() {
        return mAvatarUrl25;
    }

    public void setAvatarUrl25(String avatarUrl25) {
        mAvatarUrl25 = avatarUrl25;
    }

    public String getAvatarUrl50() {
        return mAvatarUrl50;
    }

    public void setAvatarUrl50(String avatarUrl50) {
        mAvatarUrl50 = avatarUrl50;
    }

    public String getAvatarUrl80() {
        return mAvatarUrl80;
    }

    public void setAvatarUrl80(String avatarUrl80) {
        mAvatarUrl80 = avatarUrl80;
    }

    public String getAvatarUrl150() {
        return mAvatarUrl150;
    }

    public void setAvatarUrl150(String avatarUrl150) {
        mAvatarUrl150 = avatarUrl150;
    }
}
