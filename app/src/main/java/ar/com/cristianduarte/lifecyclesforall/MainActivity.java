package ar.com.cristianduarte.lifecyclesforall;

import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {

    private OnScreenMethodLogger logger;
    private OrientationEventListener mOrientationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logger = new OnScreenMethodLogger((TextView) findViewById(R.id.activityLogTxt));

        if (savedInstanceState!=null) {
            logger.set(savedInstanceState.getCharSequence("log"));
        }

        logger.log();

        mOrientationListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
            private Integer lastOrientationLogged = null;
            public void onOrientationChanged(int orientation) {
                if (orientation % 90 == 0
                        && (lastOrientationLogged==null || lastOrientationLogged != orientation)) {
                    logger.log(String.valueOf(orientation));
                    lastOrientationLogged = orientation;
                }
            }
        };
        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        logger.log();
    }

    @Override
    protected void onResume() {
        super.onResume();
        logger.log();
    }

    @Override
    protected void onPause() {
        super.onPause();
        logger.log();
    }

    @Override
    protected void onStart() {
        super.onStart();
        logger.log();
    }

    @Override
    protected void onStop() {
        super.onStop();
        logger.log();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logger.log(); // wtf..
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logger.log();
        outState.putCharSequence("log", logger.txt());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        if (logger!=null) {
            logger.log(1);
        }
    }

}
