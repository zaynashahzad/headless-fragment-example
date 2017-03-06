package zaynashahzad.me.headlessfragmentexample.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class NetworkChecker extends Fragment {

    public static final String TAG = "network_checker_tag";
    private Activity mActivity;

    public NetworkChecker() {
        // nothing to do. nothing to do. creation can only happen from static method below.
    }

    public static NetworkChecker newInstance() {
        return new NetworkChecker();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // get the activity context associated with this fragment
        if (context instanceof Activity){
            mActivity = (Activity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Don't destory. Retain across activity re-creation.
        setRetainInstance(true);
    }

    /**
     * Checks to see if the current device has wifi or mobile data
     * @return true if has wifi, false otherwise
     */
    public boolean isInternetConnected() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) mActivity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI || activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE))
                return true;
            // catch specific exceptions. This is bad coding practice. Stay safe kids! :)
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}

