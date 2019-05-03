package com.example.a3;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyFragment extends ListFragment {

    private ListSelectionListener listener = null;
    private String[] phoneNamesArray;

    public interface ListSelectionListener {
        public void onListSelection(int index);
        public String[] getTitleArray();
    }
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            listener = (ListSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + e);
        }
        if(phoneNamesArray == null) {
            phoneNamesArray = listener.getTitleArray();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.activity_my_fragment, phoneNamesArray));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        getListView().setItemChecked(pos, true);
        listener.onListSelection(pos);
    }

}
