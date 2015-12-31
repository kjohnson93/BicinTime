package app.bicintime.wolf.navdrawer;

import android.support.v4.app.Fragment;

/**
 * Created by wolf on 12/31/2015.
 */
public class RootFragment extends Fragment implements OnBackPressListener {

    @Override
    public boolean onBackPressed() {
        return new BackPressImpl(this).onBackPressed();
    }
}