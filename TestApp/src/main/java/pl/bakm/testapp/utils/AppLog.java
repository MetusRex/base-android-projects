package pl.bakm.testapp.utils;

import android.util.Log;

/**
 * Created by marcinbak on 16.06.13.
 * <p/>
 * Logging wrapper for the project - creates tag (calling class name) by itself, adds line number and method name to help identify the log location.
 */
public class AppLog {

    private final static boolean __DEBUG = true;

    private final static int LOGLEVEL = __DEBUG ? Log.VERBOSE : Log.ERROR + 1;

    private final static int LOG_STACK_LEVEL = 1;

    //do not touch ;)
    private final static int STACK_PREV_CALLS = 4;
    private final static boolean ERROR = LOGLEVEL - 1 < Log.ERROR;
    private final static boolean WARN = LOGLEVEL - 1 < Log.WARN;
    private final static boolean INFO = LOGLEVEL - 1 < Log.INFO;
    private final static boolean DEBUG = LOGLEVEL - 1 < Log.DEBUG;
    private final static boolean VERBOSE = LOGLEVEL - 1 < Log.VERBOSE;

    public static void d(String message) {
        if (DEBUG) {
            Log.d(getTag(), wrapMessage(message));
        }
    }

    public static void i(String message) {
        if (INFO) {
            Log.i(getTag(), wrapMessage(message));
        }
    }

    public static void w(String message) {
        if (WARN) {
            Log.w(getTag(), wrapMessage(message));
        }
    }

    public static void v(String message) {
        if (VERBOSE) {
            Log.v(getTag(), wrapMessage(message));
        }
    }

    public static void e(String message) {
        if (ERROR) {
            Log.e(getTag(), wrapMessage(message));
        }
    }

    public static void e(String message, Throwable tr) {
        if (ERROR) {
            Log.e(getTag(), wrapMessage(message), tr);
        }
    }

    public static void e(String message, Exception e) {
        if (ERROR) {
            Log.e(getTag(), wrapMessage(message), e);
        }
    }

    private static String getTag() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > STACK_PREV_CALLS) {
            return stackTrace[STACK_PREV_CALLS].getClassName().replaceAll(".*\\.", "");
        }

        return new String();
    }

    /**
     * Wraps log message with information about method name and code line. Depending on the value of
     * BdeLog.LOG_STACK_LEVEL includes previous call traces.
     *
     * @param message message to be wrapped
     * @return wrapped string message
     */
    private static String wrapMessage(String message) {
        int wrapperLevel = LOG_STACK_LEVEL + STACK_PREV_CALLS;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuffer sb = new StringBuffer();
        for (int i = STACK_PREV_CALLS; i < wrapperLevel; i++) {

            if (stackTrace.length <= i) {
                break;
            }
            if (stackTrace[i].getLineNumber() == 1) {
                wrapperLevel++;
                continue;
            }

            sb.append('[');
            sb.append(stackTrace[i].getMethodName());
            sb.append(": ");
            sb.append(stackTrace[i].getLineNumber());
            sb.append("] ");
        }
        sb.append(message);

        return sb.toString();
    }

}
