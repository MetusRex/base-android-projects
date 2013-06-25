package pl.bakm.testapp.impl.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import pl.bakm.testapp.R;
import pl.bakm.testapp.abstracts.AbsFeedListAdapter;
import pl.bakm.testapp.cache.ImageCacheManager;
import pl.bakm.testapp.impl.model.FeedElement;
import pl.bakm.testapp.impl.model.JsonModel;
import pl.bakm.testapp.abstracts.AbstractRequest;
import pl.bakm.testapp.utils.SampleJsonRequest;

/**
 * Created by marcinbak on 15.06.13.
 * <p/>
 * Adapter.
 */
public class FeedListAdapter extends AbsFeedListAdapter<FeedElement, JsonModel> {

    private final static int[] stars_ids = new int[]{R.id.star_image_1, R.id.star_image_2, R.id.star_image_3, R.id.star_image_4, R.id.star_image_5};

    private String mCacheKey;

    @Override
    public long getItemId(int i) {
        return this.mElements.get(i).getId();
    }

    @Override
    protected void updateViewData(View view, FeedElement item) {

        TextView showTitle = (TextView) view.findViewById(R.id.show_title);
        TextView username = (TextView) view.findViewById(R.id.username);
        TextView date = (TextView) view.findViewById(R.id.date_created);

        NetworkImageView showPoster = (NetworkImageView) view.findViewById(R.id.show_image);
        NetworkImageView userImage = (NetworkImageView) view.findViewById(R.id.user_image);

        showTitle.setText(item.getShow().getTitle());
        username.setText(item.getUser().getUsername());
        date.setText(SampleJsonRequest.DATE_FORMAT.format(item.getDateCreated()));

        showPoster.setImageUrl(item.getShow().getImages().getPoster().getPosterUrl120(), ImageCacheManager.getInstance().getImageLoader());
        userImage.setImageUrl(item.getUser().getAvatar().getAvatarUrl80(), ImageCacheManager.getInstance().getImageLoader());

        for (int j = 0; j < stars_ids.length; j++) {
            setVisibility(j < item.getRating(), view.findViewById(stars_ids[j]));
        }
        setVisibility(item.isFavorite(), view.findViewById(R.id.fav_image));

        setVisibility(item.isTunedIn(), view.findViewById(R.id.watched_icon));
        setVisibility(item.isTunedIn(), view.findViewById(R.id.watched_text));

    }

    @Override
    protected View createView(Context context, FeedElement item) {
        return LayoutInflater.from(context).inflate(R.layout.feed_list_item, null);
    }

    @Override
    protected List<FeedElement> getElementsFromDownloadModel(JsonModel model) {
        mCacheKey = model.getMeta().getCacheKey();
        return model.getFeedElements();
    }

    @Override
    protected boolean hasMoreData(JsonModel model) {
        return model.getMeta().isMore();
    }

    @Override
    protected AbstractRequest<JsonModel> createRequest() {
        SampleJsonRequest req = new SampleJsonRequest(this, this, mElements.size(), mCacheKey);
        req.setTag(ContentFragment.REQUEST_TAG);

        return req;
    }

    private static void setVisibility(boolean visible, View view) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }
}