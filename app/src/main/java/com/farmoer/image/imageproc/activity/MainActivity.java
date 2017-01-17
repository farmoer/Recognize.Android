package com.farmoer.image.imageproc.activity;
import android.app.Activity;
import android.os.Bundle;
import com.farmoer.image.imageproc.R;
import com.farmoer.image.imageproc.fragment.Camera2BasicFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }

}
