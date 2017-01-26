package com.example.jovi.whoisit;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jovi.whoisit.views.PersonDetails;
import com.example.jovi.whoisit.views.PersonOverview;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PersonOverview frag = new PersonOverview();
        if(getSupportFragmentManager().findFragmentByTag("overview") == null)
            getSupportFragmentManager().beginTransaction().add(R.id.list, frag, "overview").commit();
        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT)
        {
            if(getSupportFragmentManager().findFragmentByTag("details") != null)
                getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("details")).commit();
        }else{
            PersonDetails details = new PersonDetails();
            getSupportFragmentManager().beginTransaction().add(R.id.details, details, "details").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                return true;
            case R.id.action_restart:
                PersonOverview fragment;
                if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT) {
                    fragment = (PersonOverview) getSupportFragmentManager().findFragmentById(R.id.list);
                }else{
                    fragment = (PersonOverview) getSupportFragmentManager().findFragmentById(R.id.list);
                }
                fragment.restart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
        {
            getFragmentManager().popBackStack();
            setTitle("Overview");
        }else{
            super.onBackPressed();
        }
    }
}
