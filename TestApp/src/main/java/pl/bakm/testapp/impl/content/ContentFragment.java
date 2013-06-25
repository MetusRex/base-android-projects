package pl.bakm.testapp.impl.content;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.RequestQueue;

import pl.bakm.testapp.R;
import pl.bakm.testapp.utils.AppLog;

/**
 * Created by marcinbak on 15.06.13.
 */
public class ContentFragment extends Fragment implements AbsListView.OnScrollListener {

    private final RequestQueue mRequestQueue;
    private final FeedListAdapter mAdapter;

    public final static Object REQUEST_TAG = new Object();

    public ContentFragment(RequestQueue queue, Context context) {
        this.mRequestQueue = queue;

        this.mAdapter = new FeedListAdapter();
        this.mAdapter.loadMoreData(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        AppLog.e("onAttach");
    }

    //views
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AppLog.e("onCreateView");
        View view = inflater.inflate(R.layout.json_feed_page, null);
        ListView listView = (ListView) view.findViewById(R.id.feed_list);

        listView.setAdapter(this.mAdapter);
        listView.setOnScrollListener(this);
        this.mAdapter.setAttached(true);
        return view;
    }

    @Override
    public void onDestroy() {
        this.mRequestQueue.cancelAll(REQUEST_TAG);
        this.mAdapter.setAttached(false);
        super.onDestroy();
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mAdapter.setVisibleItems(2*visibleItemCount);
    }
}
