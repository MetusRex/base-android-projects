package pl.bakm.testapp.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by marcinbak on 15.06.13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedUser {

    private final static String TAG_USERNAME = "username";
    private final static String TAG_ID = "id";
    private final static String TAG_FRIEND = "isFriend";
    private final static String TAG_AVATAR = "Avatar";

    @JsonProperty(TAG_USERNAME)
    private String mUsername;

    @JsonProperty(TAG_ID)
    private String mId;

    @JsonProperty(TAG_FRIEND)
    private boolean mFriend;

    @JsonProperty(TAG_AVATAR)
    private FeedAvatar mAvatar;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public boolean isFriend() {
        return mFriend;
    }

    public void setFriend(boolean friend) {
        mFriend = friend;
    }

    public FeedAvatar getAvatar() {
        return mAvatar;
    }

    public void setAvatar(FeedAvatar avatar) {
        mAvatar = avatar;
    }
}
