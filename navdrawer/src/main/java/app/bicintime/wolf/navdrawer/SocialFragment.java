package app.bicintime.wolf.navdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ratan on 7/29/2015.
 */
public class SocialFragment extends Fragment {


    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    Fragment_footer fr1,fr2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentManager fManager= getChildFragmentManager();

        FragmentTransaction ft = fManager.beginTransaction();
        fr1= new Fragment_footer();
        ft.add(R.id.top, fr1 , "top");
        fr2 = new Fragment_footer();
        ft.add(R.id.bottom, fr2, "bottom");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        fManager.executePendingTransactions();
        return inflater.inflate(R.layout.social_layout,null);
    }


}
