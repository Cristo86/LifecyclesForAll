package ar.com.cristianduarte.lifecyclesforall;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by cduarte on 3/2/16.
 */
public class OnScreenMethodLogger {

    private TextView logTxt;
    private SimpleDateFormat sdf;

    public OnScreenMethodLogger(TextView logTxt) {
        this.logTxt = logTxt;
        this.logTxt.setMovementMethod(new ScrollingMovementMethod());
        this.sdf = new SimpleDateFormat("mm:ss.SSSS");
    }

    public void set(CharSequence txt) {
        logTxt.append(txt);
    }

    public CharSequence txt() {
        return logTxt.getText();
    }

    public void log(String...info) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        logTxt.append(sdf.format(new Date()) + " - " + stackTraceElement.getClassName().substring(
                stackTraceElement.getClassName().lastIndexOf('.')+1) + "."
                + stackTraceElement.getMethodName() + (info!=null && info.length>0? Arrays.toString(info):"") + "\n");
    }

    public void log(int lvl, String...info) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3+lvl];
        logTxt.append(sdf.format(new Date()) + " - " + stackTraceElement.getClassName().substring(
                stackTraceElement.getClassName().lastIndexOf('.')+1) + "."
                + stackTraceElement.getMethodName() + (info!=null&& info.length>0? Arrays.toString(info):"") + "\n");
    }

}
