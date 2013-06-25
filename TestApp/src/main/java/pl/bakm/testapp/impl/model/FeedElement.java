package pl.bakm.testapp.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by marcinbak on 15.06.13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedElement {

    private final static String TAG_CREATED = "created";
    private final static String TAG_ID = "id";
    private final static String TAG_RATING = "rating";
    private final static String TAG_FAVORITE = "favorite";
    private final static String TAG_TYPE = "type";
    private final static String TAG_TUNEDIN = "tunedin";
    private final static String TAG_AGE = "age";
    private final static String TAG_USER = "user";
    private final static String TAG_SHOW = "show";

    @JsonProperty(TAG_CREATED)
    private Date mDateCreated;

    @JsonProperty(TAG_ID)
    private int mId;

    @JsonProperty(TAG_RATING)
    private int mRating;

    @JsonProperty(TAG_FAVORITE)
    private int mFavorite;

    @JsonProperty(TAG_TUNEDIN)
    private int mTunedIn;

    @JsonProperty(TAG_TYPE)
    private String mType;

    @JsonProperty(TAG_AGE)
    private long mAge;

    @JsonProperty(TAG_SHOW)
    private FeedShow mShow;

    @JsonProperty(TAG_USER)
    private FeedUser mUser;

    public Date getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        mDateCreated = dateCreated;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        mRating = rating;
    }

    public boolean isFavorite() {
        return mFavorite == 1;
    }

    public void setFavoriteB(boolean favorite) {
        mFavorite = favorite ? 1 : 0;
    }

    public int getFavorite() {
        return mFavorite;
    }

    public void setFavorite(int favorite) {
        mFavorite = favorite;
    }

    public boolean isTunedIn() {
        return mTunedIn == 1;
    }

    public void setTunedInB(boolean tunedIn) {
        mTunedIn = tunedIn ? 1 : 0;
    }

    public int getTunedIn() {
        return mTunedIn;
    }

    public void setTunedIn(int tunedIn) {
        mTunedIn = tunedIn;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public long getAge() {
        return mAge;
    }

    public void setAge(long age) {
        mAge = age;
    }

    public FeedShow getShow() {
        return mShow;
    }

    public void setShow(FeedShow show) {
        mShow = show;
    }

    public FeedUser getUser() {
        return mUser;
    }

    public void setUser(FeedUser user) {
        mUser = user;
    }
}
