package com.example.wda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wda.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    ListView    listView;
    TextView[]  portNoTextView  =   new TextView[10];
    Button[]    minusButton     =   new Button[10];
    Button[]    plusButton      =   new Button[10];


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(),"start",Toast.LENGTH_LONG).show();
        loadAttenuationView();
    }

    public void loadAttenuationView(){
        listView    =   getView().findViewById(R.id.listView);
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for(int i=0; i<10; i++){
            HashMap<String, Object> map = new HashMap<>();
            map.put("SeekBar", seekBar[i]);

            map.put("PortNoTextView", portNoTextView[i]);
            map.put("MinusButton", minusButton[i]);
            map.put("PlusButton", plusButton[i]);
            list.add(map);
        }

        String[]    from    =   {"SeekBar","PortNoTextView","MinusButton","PlusButton"};
        int         to[]    =   {   R.id.attanuationEditText,
                R.id.portNoTextView,
                R.id.minusButton,
                R.id.plusButton  };
        SimpleAdapter simpleAdapter = new SimpleAdapter(getView().getContext(), list, R.layout.attenuationtemplate, from, to);
        listView.setAdapter(simpleAdapter);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}