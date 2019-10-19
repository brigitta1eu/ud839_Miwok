package com.example.android.miwok;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MiwokPagerAdapter extends FragmentPagerAdapter {
    public MiwokPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ColorsFragment();
        }
        if (position == 1) {
            return new FamilyFragment();
        }
        if (position == 2) {
            return new NumbersFragment();
        }
        if (position == 3) {
            return new PhrasesFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Colors";
        }
        if (position == 1) {
            return "Family";
        }
        if (position == 2) {
            return "Numbers";
        }
        if (position == 3) {
            return "Phrases";
        }
        return "";
    }
}
