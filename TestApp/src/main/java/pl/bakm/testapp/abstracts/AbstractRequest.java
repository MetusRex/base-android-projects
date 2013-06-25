package pl.bakm.testapp.abstracts;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import pl.bakm.testapp.utils.AppLog;

/**
 * Created by marcinbak on 25.06.13.
 *
 * Abstract request class with with a listener for successful response.
 *
 * @param <V> model class representing result of download/parsing
 */
public abstract class AbstractRequest<V> extends Request<V> {

    public interface SuccessResponseListener<V> {
        public void onParsingFinished(V model);
    }

    public static int DEFAULT_PAGE_SIZE = 10;
    private SuccessResponseListener mSuccessResponseListener;

    public AbstractRequest(Response.ErrorListener listener, SuccessResponseListener successResponseListener, String url) {
        this(listener, successResponseListener, Method.GET, url);
    }

    public AbstractRequest(Response.ErrorListener listener, SuccessResponseListener successResponseListener, int method, String url) {
        super(method, url, listener);
        this.mSuccessResponseListener = successResponseListener;
        AppLog.w("created request");
    }

    @Override
    protected abstract Response<V> parseNetworkResponse(NetworkResponse response);

    @Override
    protected void deliverResponse(V response) {
        AppLog.w("delivering request");
        if (!isCanceled()) mSuccessResponseListener.onParsingFinished(response);
    }

}
