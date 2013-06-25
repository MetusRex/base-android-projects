package pl.bakm.testapp.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import pl.bakm.testapp.abstracts.AbstractRequest;
import pl.bakm.testapp.impl.model.JsonModel;

/**
 * Created by marcinbak on 16.06.13.
 */
public class SampleJsonRequest extends AbstractRequest<JsonModel> {

    private final static String JSON_URL = "http://api.gettuned.in/activity/test";
    public final static DateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yy hh:mm", Locale.GERMAN);
    private final ObjectMapper mMapper = new ObjectMapper();

    public SampleJsonRequest(Response.ErrorListener listener, SuccessResponseListener successResponseListener) {
        this(listener, successResponseListener, 0, null);
    }

    public SampleJsonRequest(Response.ErrorListener listener, SuccessResponseListener successResponseListener, int offset, String cacheKey) {
        super(listener, successResponseListener, Method.GET, getJsonUrl(offset, cacheKey));
    }

    private static String getJsonUrl(int offset, String cacheKey) {
        StringBuilder sb = new StringBuilder();
        sb.append(JSON_URL)
                .append("?offset=").append(offset)
                .append("&limit=").append(DEFAULT_PAGE_SIZE);

        if(cacheKey != null)
            sb.append("&cachekey=").append(cacheKey);
        return sb.toString();
    }

    @Override
    protected Response<JsonModel> parseNetworkResponse(NetworkResponse response) {

        AppLog.w("parsing request");
        mMapper.setDateFormat(DATE_FORMAT);

        try {
            JsonModel result = mMapper.readValue(response.data, JsonModel.class);
            if(result == null)
                return Response.error(new VolleyError("No result"));
            else
                return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }
}
