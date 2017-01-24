package com.example.jovi.whoisit.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jovi.whoisit.R;
import com.example.jovi.whoisit.domain.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jovi on 1/24/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MainViewHolder>
{

    private ArrayList<Person> persons;

    public PersonAdapter(ArrayList<Person> p)
    {
        this.persons = p;
    }

    public void deletePerson(int id)
    {
        persons.remove(id);
    }

    @Override
    public PersonAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main, parent, false);
        v.setOnClickListener(PersonOverview.mainOnClickListener);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonAdapter.MainViewHolder holder, int position)
    {
        ImageView image = holder.personImage;
        TextView name = holder.personName;
        Context context = holder.personImage.getContext();
        Picasso.with(context).load(persons.get(position).picture).into(image);
        image.setImageResource(persons.get(position).picture);
        name.setText(persons.get(position).name);
    }

    @Override
    public int getItemCount()
    {
        return persons.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.card_image)
        public ImageView personImage;

        @Bind(R.id.card_name)
        public TextView personName;

        public MainViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
