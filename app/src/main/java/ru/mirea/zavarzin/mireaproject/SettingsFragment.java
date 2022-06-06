package ru.mirea.zavarzin.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    final String FIRST_PARAMETER = "par_1";
    final String SECOND_PARAMETER = "parr_2";
    final String THIRD_PARAMETER = "par_3";

    private EditText nameTextView;
    private EditText surnameTextView;
    private EditText growthTextView;
    private Button btnSave;
    private Button btnLoad;

    private SharedPreferences preferences;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        nameTextView = v.findViewById(R.id.nameParTextView);
        surnameTextView = v.findViewById(R.id.surnameParTextView);
        growthTextView = v.findViewById(R.id.growthParTextView);
        btnSave = (Button) v.findViewById(R.id.saveButton);
        btnSave.setOnClickListener(this::onClickSave);
        btnLoad = (Button) v.findViewById(R.id.loadButton);
        btnLoad.setOnClickListener(this::onClickLoad);
        preferences = getActivity().getPreferences(MODE_PRIVATE);
        return v;
    }
    public void onClickSave(View view) {
        String par1 = nameTextView.getText().toString();
        String par2 = surnameTextView.getText().toString();
 //       int par3 = Integer.parseInt(growthTextView.getText().toString());
        String par3 = growthTextView.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(FIRST_PARAMETER, par1);
        editor.putString(SECOND_PARAMETER, par2);
//        editor.putInt(THIRD_PARAMETER, par3);
        editor.putString(THIRD_PARAMETER, par3);

        editor.apply();
        Toast.makeText(getActivity(), "Parameters have been saved", Toast.LENGTH_SHORT).show();

    }
    public void onClickLoad(View view) {
        String par1 = preferences.getString(FIRST_PARAMETER, "Empty");
        nameTextView.setText(par1);
        String par2 = preferences.getString(SECOND_PARAMETER, "Empty");
        surnameTextView.setText(par2);
        //int par3 = Integer.parseInt(preferences.getString(THIRD_PARAMETER, "0"));
        String par3 = preferences.getString(THIRD_PARAMETER, "0");
        growthTextView.setText(par3);
    }
}