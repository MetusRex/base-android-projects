package pl.bakm.testapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Window;
import android.view.WindowManager;

/**
 * Useful display utils class.
 *
 */
public class DisplayUtils {

    public static int sDisplayWidth;
    public static int sDisplayHeight;
    public static int sHeaderHeight;
    public static int sdensityDpi;
    public static float sdpi;
    public static double sScaledDensity;
    public static int sArticleInnerMargin;

    public static void init(Activity activity) {
        sdensityDpi = activity.getResources().getDisplayMetrics().densityDpi;
        sdpi = activity.getResources().getDisplayMetrics().density;
        sScaledDensity = activity.getResources().getDisplayMetrics().scaledDensity;

    /*
     * Caution: this section always forces to apply the width and height in
     * potrait mode
     */
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            sDisplayWidth = activity.getResources().getDisplayMetrics().heightPixels + getNavigationBarHeight(activity);//+ getStatusBarHeight(activity);
            sDisplayHeight = activity.getResources().getDisplayMetrics().widthPixels;

        } else {
            sDisplayWidth = activity.getResources().getDisplayMetrics().widthPixels;
            sDisplayHeight = activity.getResources().getDisplayMetrics().heightPixels + getNavigationBarHeight(activity);//+ getStatusBarHeight(activity);
        }
        sHeaderHeight = getActivityHeaderHeight(activity);

    }

    /*
     * from dp to px
     */
    public static int toPx(double dp) {
        return (int) (dp * sdpi + 0.5f);
    }

    /*
     * from dp to px
     */
    public static int toPx(double dp, Context context) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /*
     * from px to dp
     */
    public static double toDp(int px) {
        return px / sdpi;
    }

    /*
     * from px to dp
     */
    public static double toDp(int px, Context context) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    /*
     * from sp to text size
     */
    public static int toTextSize(double sp) {
        return (int) (sp * sScaledDensity);
    }

    /*
     * get height without header
     */
    public static int getActivityHeaderHeight(Activity activity) {
        Rect r = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(r);
        return r.top;
    }

    public static int getActivityWindowHeight(Activity activity) {
        return sDisplayHeight - getActivityHeaderHeight(activity);
    }

    public static int sContentFrameWidthPotrait = 0;
    public static int sContentFrameWidthLandscape = 0;
    private static int sMenuWidth;

    public static void setMenuLayoutParams(int mBehindWidth, Context context) {
        sContentFrameWidthLandscape = sDisplayHeight - mBehindWidth;
        sContentFrameWidthLandscape = sDisplayWidth - mBehindWidth;
    }

    public static Point getFitScreenDimension(Context context) {
        Point p = new Point();
        if (getOrientation(context) == Configuration.ORIENTATION_PORTRAIT) {
            p.x = DisplayUtils.sDisplayWidth;
            p.y = 0;
        } else {
            p.x = 0;
            p.y = DisplayUtils.sDisplayHeight;
        }
        return p;
    }

    public static int getOrientation(Context c) {
        return c.getResources().getConfiguration().orientation;
    }

    public static boolean isPortraitOrientation(Context ctx) {
        return getOrientation(ctx) == Configuration.ORIENTATION_PORTRAIT;
    }

    public static int getScreenFullWidth(Context ctx) {
        return isPortraitOrientation(ctx) ? sDisplayWidth : sDisplayHeight - sMenuWidth;
    }

    public static int getStatusBarHeight(Activity activity) {
        // <sdk
        // root="">platforms/android-<version>/data/res/values/dimens.xml</version></sdk>
        int result = 0;
        boolean fullScreen = ((activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0);
        if (!fullScreen) {
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = activity.getResources().getDimensionPixelSize(resourceId);
            }
        }

        return result;
    }

    public static int getNavigationBarHeight(Activity activity) {
        // <sdk
        // root="">platforms/android-<version>/data/res/values/dimens.xml</version></sdk>

        int result = 0;
        int resourceId = activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
