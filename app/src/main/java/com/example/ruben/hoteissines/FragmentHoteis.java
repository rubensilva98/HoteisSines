package com.example.ruben.hoteissines;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ruben.hoteissines.model.HoteisData;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHoteis extends ListFragment {



    public FragmentHoteis() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_expandable_list_item_1, HoteisData.Hoteis);
        HoteisAdapter adapter = new HoteisAdapter(getActivity(), HoteisData.getHotels());
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if (getActivity().findViewById(R.id.fragment_container) !=null){

            FragmentDescricao newFragment = new FragmentDescricao();
            Bundle args = new Bundle();
            args.putInt("position", position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else {
            TextView descricaoTextView = (TextView) getActivity().findViewById(R.id.descricao_textview);
          //  descricaoTextView.setText(HoteisData.Hoteis[position]);
        }
    }
}
