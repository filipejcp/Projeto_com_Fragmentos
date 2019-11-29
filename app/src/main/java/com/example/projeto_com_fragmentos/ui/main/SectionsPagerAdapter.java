package com.example.projeto_com_fragmentos.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projeto_com_fragmentos.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            //Seleciona o tab escolhido, para apresentar o respetivo fragmento
            case 0: return PlaceholderFragment.newInstance();
            case 1: return Frag_Acerca.newInstance();
            default: return null;
        }


    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Seleciona o tab escolhido, para apresentar o respetivo fragmento
        switch (position) {
            case 0: return "Contactos";
            case 1: return "Acerca";
            default: return null;
        }



    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}