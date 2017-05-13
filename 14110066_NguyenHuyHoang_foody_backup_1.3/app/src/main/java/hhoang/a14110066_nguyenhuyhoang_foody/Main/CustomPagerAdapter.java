package hhoang.a14110066_nguyenhuyhoang_foody.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


//Class custom Pager
public class CustomPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fr = new ArrayList<Fragment>();
    public CustomPagerAdapter(FragmentManager frm, ArrayList<Fragment> fr){
        super(frm);
        this.fr = fr;
    }

    @Override
    public int getCount() {
        return fr.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fr.get(position);
    }

}
