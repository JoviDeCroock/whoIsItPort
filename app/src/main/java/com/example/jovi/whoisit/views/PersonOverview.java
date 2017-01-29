package com.example.jovi.whoisit.views;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jovi.whoisit.R;
import com.example.jovi.whoisit.domain.Person;
import com.example.jovi.whoisit.persistence.PersonReaderHelper;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonOverview extends Fragment {

    @Bind(R.id.recyclerView)
    public RecyclerView mRcyclerView;

    public PersonAdapter adapter;
    public PersonReaderHelper mDbHelper;
    protected RecyclerView.LayoutManager mLayoutManager;
    public ItemTouchHelper itemTouchHelper;
    private ArrayList<Person> persons;
    private int mCurCheckPosition = 0;
    boolean mDualPane = false;
    public static MainOnclickListener mainOnClickListener;

    public PersonOverview() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mainOnClickListener = new MainOnclickListener(getContext());
        itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_person_overview, container, false);
        ButterKnife.bind(this, rootView);
        if(persons==null)
        {
            initDataSet();
        }else{
            adapter = new PersonAdapter(persons);
            mRcyclerView.setAdapter(adapter);
        }
        if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE)
        {
            mDualPane = true;
        }else{
            mDualPane = false;
        }
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRcyclerView.setLayoutManager(mLayoutManager);
        itemTouchHelper.attachToRecyclerView(mRcyclerView);
        return rootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    public void initDataSet() {
        if (mDbHelper == null)
        {
            mDbHelper = new PersonReaderHelper(getContext());
        }
        asyncDB a = new asyncDB();
        a.execute();
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
    {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){return true;}

        @Override
        public void onSwiped(RecyclerView.ViewHolder v, int direction)
        {
            adapter.deletePerson(v.getAdapterPosition());
            mRcyclerView.getAdapter().notifyDataSetChanged();
        }
    };

    private class MainOnclickListener implements View.OnClickListener {

        private final Context context;

        public MainOnclickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v)
        {
            mCurCheckPosition = mRcyclerView.getChildAdapterPosition(v);
            showDetails(mRcyclerView.getChildAdapterPosition(v));
        }
    }

    public void restart()
    {
        initDataSet();
    }

    private void showDetails(int index) {
        PersonDetails details = PersonDetails.newInstance(persons.get(index));
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (mDualPane) {
            ft.replace(R.id.details, details);
        } else {
            ft.replace(R.id.list, details);
            ft.addToBackStack(null);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ft.commit();
    }

    private class asyncDB extends AsyncTask<String, Void, ArrayList<Person>>
    {
        @Override
        protected ArrayList<Person> doInBackground(String... strings) {
            ArrayList<Person> list = mDbHelper.getAllPersons();
            return list;
        }
        @Override
        protected void onPostExecute(ArrayList<Person> list)
        {
            PersonOverview.this.persons = list;
            adapter = new PersonAdapter(persons);
            mRcyclerView.setAdapter(adapter);
        }
    }
}
