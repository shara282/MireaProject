package ru.mirea.zavarzin.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorsFragment extends Fragment implements SensorEventListener {
    private TextView tempTextView;
    private TextView pressureTextView;
    private TextView lightTextView;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Sensor pressureSensor;
    private Sensor lightSensor;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SensorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorsFragment newInstance(String param1, String param2) {
        SensorsFragment fragment = new SensorsFragment();
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
        View v = inflater.inflate(R.layout.fragment_sensors, container, false);
        tempTextView = v.findViewById(R.id.tempTextView);
        pressureTextView = v.findViewById(R.id.pressureTextView);
        lightTextView = v.findViewById(R.id.lightTextView);
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        return v;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){
            String newValue = sensorEvent.values[0] + " Расстояние до цели";
            tempTextView.setText(newValue);
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE){
            String newValue = sensorEvent.values[0] + " мбар";
            pressureTextView.setText(newValue);
        }
        else if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            String newValue = sensorEvent.values[0] + " лк";
            lightTextView.setText(newValue);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, tempSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pressureSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}