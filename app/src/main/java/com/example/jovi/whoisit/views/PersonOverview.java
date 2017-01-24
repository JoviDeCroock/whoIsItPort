package com.example.jovi.whoisit.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jovi.whoisit.MainActivity;
import com.example.jovi.whoisit.R;
import com.example.jovi.whoisit.domain.Person;
import com.example.jovi.whoisit.persistence.PersonReaderHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonOverview extends Fragment {

    @Bind(R.id.recyclerView)
    public RecyclerView mRcyclerView;

    public PersonAdapter adapter;
    public PersonReaderHelper mDbHelper;
    protected RecyclerView.LayoutManager mLayoutManager;
    public ItemTouchHelper itemTouchHelper;
    private int currentIndex;
    private ArrayList<Person> persons;

    public static MainOnclickListener mainOnClickListener;

    public PersonOverview()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataSet();
        mainOnClickListener = new MainOnclickListener(getContext());
        itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_person_overview, container, false);
        ButterKnife.bind(this,rootView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRcyclerView.setLayoutManager(mLayoutManager);
        adapter = new PersonAdapter(persons);
        mRcyclerView.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(mRcyclerView);
        return rootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initDataSet()
    {
        mDbHelper = new PersonReaderHelper(getContext());
        persons = mDbHelper.getAllPersons();
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
    {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
        {
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder v, int direction)
        {
            currentIndex = v.getAdapterPosition();
            adapter.deletePerson(currentIndex);
            mRcyclerView.getAdapter().notifyDataSetChanged();
        }
    };

    private class MainOnclickListener implements View.OnClickListener{

        private final Context context;

        public MainOnclickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            currentIndex = mRcyclerView.getChildAdapterPosition(v);
        }
    }
}
