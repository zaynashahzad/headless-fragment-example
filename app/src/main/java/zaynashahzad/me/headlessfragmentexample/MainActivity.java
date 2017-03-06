package zaynashahzad.me.headlessfragmentexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import zaynashahzad.me.headlessfragmentexample.fragments.NetworkChecker;

public class MainActivity extends AppCompatActivity {

    NetworkChecker mNetworkChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Pretend this does something", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // we're going to do something with this little fragment helper guy later!
        addHeadlessFragmentToActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateViewForInternetConnection();
    }

    private void updateViewForInternetConnection() {
        TextView textview = (TextView) findViewById(R.id.fragment_text_view);
        if (textview != null) {
            if (mNetworkChecker.isInternetConnected()) {
                textview.setText("Yup, internet is available! Huzzah!");
            } else {
                textview.setText("Derp. No interwebz. Sad panda.");
            }
        }
    }

    private void addHeadlessFragmentToActivity() {
        // was it already attached to the activity in the past? If not, add it now.
        mNetworkChecker = (NetworkChecker) getFragmentManager().findFragmentByTag(NetworkChecker.TAG);

        if (mNetworkChecker == null) {
            mNetworkChecker = NetworkChecker.newInstance();
            getFragmentManager()
                    .beginTransaction()
                    .add(mNetworkChecker, NetworkChecker.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // Blah, blah. Do something.
        return super.onOptionsItemSelected(item);
    }
}
