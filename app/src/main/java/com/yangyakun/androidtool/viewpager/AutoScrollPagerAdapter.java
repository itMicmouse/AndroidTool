package com.yangyakun.androidtool.viewpager;

/**
 * Created by GuiYanBing on 2018/3/24 17:33
 * E-Mail Address：guiyanbing@zhiyihealth.com.cn
 */


import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class AutoScrollPagerAdapter extends PagerAdapter {


    private static final String TAG = "AutoScrollPagerAdapter";

    private PagerAdapter wrappedAdapter;

    private boolean isSaveed = false;

    public AutoScrollPagerAdapter(PagerAdapter wrappedAdapter) {
        this.wrappedAdapter = wrappedAdapter;
    }

    @Override
    public int getCount() {
        if (wrappedAdapter == null) {
            Log.e(TAG, "getCount: 0");
            return 0;
        } else if (wrappedAdapter.getCount() > 1) {
            Log.e(TAG, "getCount: "+(wrappedAdapter.getCount() + 2));
            return wrappedAdapter.getCount() + 2;
        } else {
            Log.e(TAG, "getCount: "+wrappedAdapter.getCount());
            return wrappedAdapter.getCount();
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.e(TAG, "instantiateItem: "+position);
        if (!isSaveed) {
            if (position == 0) {
                return wrappedAdapter.instantiateItem(container, wrappedAdapter.getCount() - 1);
            } else if (position == wrappedAdapter.getCount() + 1) {
                return wrappedAdapter.instantiateItem(container, 0);
            } else {
                return wrappedAdapter.instantiateItem(container, position - 1);
            }
        } else {
            isSaveed = false;
            return wrappedAdapter.instantiateItem(container, 0);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.e(TAG, "destroyItem: "+position);
        wrappedAdapter.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.e(TAG, "isViewFromObject: ");
        return wrappedAdapter.isViewFromObject(view, object);
    }


    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        isSaveed = true;
        Log.e(TAG, "restoreState: ");
        super.restoreState(state, loader);
    }

    @Override
    public Parcelable saveState() {
        Log.e(TAG, "saveState: ");
        return super.saveState();
    }

}
