package com.example.jovi.whoisit.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jovi.whoisit.R;
import com.example.jovi.whoisit.domain.Person;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonDetails extends Fragment
{
    @Bind(R.id.person)
    TextView textPerson;
    @Bind(R.id.card_image)
    ImageView img;

    public static PersonDetails newInstance(Person p) {
        PersonDetails details = new PersonDetails();
        Bundle args = new Bundle();
        args.putString("name", p.name);
        args.putInt("age", p.age);
        args.putString("course", p.hateCourse);
        args.putString("hobby", p.hobby);
        args.putString("language", p.favoLanguage);
        args.putInt("picture", p.picture);
        details.setArguments(args);
        return details;
    }

    public PersonDetails(){}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_details, container, false);
        ButterKnife.bind(this, rootView);
        if (getArguments()!= null)
        {
            initView();
        }
        return rootView;
    }

    public void initView()
    {
        textPerson.setText("");
        String hobby = getArguments().getString("hobby");
        String name = getArguments().getString("name");
        String language = getArguments().getString("language");
        String course = getArguments().getString("course");
        int age = getArguments().getInt("age");
        String s = String.format(getString(R.string.person), name, age, hobby, language, course);
        textPerson.setText(s);
        img.setImageResource(getArguments().getInt("picture"));
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}
