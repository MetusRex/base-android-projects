package pl.bakm.testapp.abstracts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import pl.bakm.testapp.MainActivity;
import pl.bakm.testapp.R;
import pl.bakm.testapp.utils.AppLog;
import pl.bakm.testapp.utils.SampleJsonRequest;

/**
 * Created by marcinbak  on 25.06.13.
 *
 * Abstract paging adapter, that requests new load of data after reaching defined item.
 *
 * @param <T> model class representing one item used to create view in the adapter.
 * @param <V> model class representing result of download/parsing
 */
public abstract class AbsFeedListAdapter<T, V> extends BaseAdapter implements ListAdapter, AbstractRequest.SuccessResponseListener<V>, Response.ErrorListener {

    protected final List<T> mElements;

    private Context mContext;
    private boolean isLoading;

    /**
     * Flag telling us our last network call returned 0 results and we do not need to execute any new requests
     */
    private boolean moreDataToLoad;
    private boolean mAttached;

    public AbsFeedListAdapter() {
        mElements = new ArrayList<T>();
        this.mAttached = false;
    }

    @Override
    public int getCount() {
        return this.mElements.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mElements.get(i);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        AppLog.w("getView: " + i + ", mElements.get(i).getShow().getTitle()");

        //check to see if we need to load more data
        if (shouldLoadMoreData(mElements, i)) {
            loadMoreData(viewGroup.getContext());
        }

        T item = mElements.get(i);

        if (convertView == null) {
            convertView = createView(viewGroup.getContext(), item);
        }

        if (convertView.getTag() == null || (Integer) convertView.getTag() != i) {


            updateViewData(convertView, item);

            convertView.setTag(i);
        }

        return convertView;
    }


    /**
     * Method responsible for creating view for the list.
     *
     * @param item element object
     * @return view to be displayed in the list
     */
    protected abstract View createView(Context context, T item);

    /**
     * Method setting necessary data to views from model item.
     * Set texts, images etc
     *
     * @param view parent view
     * @param item
     */
    protected abstract void updateViewData(View view, T item);

    /**
     * Method extracting list of elements from downloaded and parsed data.
     *
     * @param model model to extract list from
     * @return extracted list of items
     */
    protected abstract List<T> getElementsFromDownloadModel(V model);

    /**
     * Basing on a model decide if there are more pages to be downloaded.
     *
     * @param model model to base decision on
     * @return result of decision whether there are more data to be downloaded
     */
    protected abstract boolean hasMoreData(V model);

    protected abstract AbstractRequest<V> createRequest();

    private static void setVisibility(boolean visible, View view) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    private boolean shouldLoadMoreData(List<T> data, int position) {
        // If showing the last set of data, request for the next set of data
        boolean scrollRangeReached = (position > (data.size() - SampleJsonRequest.DEFAULT_PAGE_SIZE));
        return (scrollRangeReached && !isLoading && moreDataToLoad);
    }

    public void loadMoreData(Context ctx) {
        if (this.mContext == null)
            this.mContext = ctx;
        isLoading = true;
        AppLog.v("Load more data");

        ((MainActivity) ctx).getRequestQueue().add(createRequest());
    }

    @Override
    public void onParsingFinished(V model) {
        List<T> list = getElementsFromDownloadModel(model);
        mElements.addAll(list);

        if (list != null && list.size() > 0
                && hasMoreData(model)) {
            moreDataToLoad = true;
        } else {
            moreDataToLoad = false;
        }

        if (mAttached) {
            dismissProgressBar(mContext);
            notifyDataSetChanged();
        }
        AppLog.v("New feed retrieved");

        isLoading = false;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (mContext == null) return;

        dismissProgressBar(mContext);
        new AlertDialog.Builder(mContext).setTitle("Error!")
                .setMessage("Error during downloading or parsing")
                .setCancelable(true)
                .setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loadMoreData(mContext);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(R.string.btn_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create().show();
        isLoading = false;
    }

    private void dismissProgressBar(Context ctx) {
        if (ctx != null)
            ((Activity) ctx).findViewById(R.id.progress_indicator).setVisibility(View.GONE);
    }

    public void setAttached(boolean attached) {
        mAttached = attached;
    }

    public void setVisibleItems(int visibleItems) {
        if (visibleItems > AbstractRequest.DEFAULT_PAGE_SIZE)
            AbstractRequest.DEFAULT_PAGE_SIZE = visibleItems;
    }

}