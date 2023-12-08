package com.example.loginvsf.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginvsf.Feed;
import com.example.loginvsf.R;
import com.example.loginvsf.RecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Feed> feedArrayList;
    private String[] feedTitle;
    private int[] imageResourse;
    private String[] feedMensagem;
    private int[] imagePost;
    private RecyclerView recyclerview;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();

        recyclerview = view.findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(),feedArrayList);
        recyclerview.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        feedArrayList = new ArrayList<>();

        feedTitle = new String[]{
                getString(R.string.head_1),
                getString(R.string.head_2),
                getString(R.string.head_3),
                getString(R.string.head_4),
                getString(R.string.head_5),
                getString(R.string.head_6),
                getString(R.string.head_7),
                getString(R.string.head_8),
                getString(R.string.head_9),
                getString(R.string.head_10),
                getString(R.string.head_11),
                getString(R.string.head_12),
                getString(R.string.head_13),
                getString(R.string.head_14),
        };
        imageResourse = new int[]{
                R.drawable.imgadvogado12,
                R.drawable.imgadvogado11,
                R.drawable.imgadvogado,
                R.drawable.imgadvogado2,
                R.drawable.imgadvogado3,
                R.drawable.imgadvogado4,
                R.drawable.imgadvogado5,
                R.drawable.imgadvogado6,
                R.drawable.imgadvogado7,
                R.drawable.imgadvogado8,
                R.drawable.imgadvogado9,
                R.drawable.imgadvogado10,
                R.drawable.imgadvogado13,
                R.drawable.imgadvogado14,
        };
        /*feedMensagem = new String[]{
                getString(R.string.mensagem1),
                getString(R.string.mensagem1),
                getString(R.string.mensagem1),
                getString(R.string.mensagem1),
                getString(R.string.mensagem1),
                getString(R.string.mensagem1),
                getString(R.string.mensagem1),
                getString(R.string.mensagem1),
        };
        imagePost = new int[]{
              R.drawable.advogado_teste_feed,
              R.drawable.advogado_teste_feed,
              R.drawable.advogado_teste_feed,
              R.drawable.advogado_teste_feed,
              R.drawable.advogado_teste_feed,
              R.drawable.advogado_teste_feed,
              R.drawable.advogado_teste_feed,
              R.drawable.advogado_teste_feed,
        };*/

        for (int i = 0; i < feedTitle.length; i++){
            Feed feed = new Feed(feedTitle[i],imageResourse[i]);
            feedArrayList.add(feed);
        }
    }

}