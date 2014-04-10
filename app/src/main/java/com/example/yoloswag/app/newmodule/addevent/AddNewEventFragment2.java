package com.example.yoloswag.app.newmodule.addevent;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yoloswag.app.R;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by dayouxia on 4/1/14.
 */
public class AddNewEventFragment2 extends Fragment {

    private Button mChooseDateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_event2,container,false);


        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView calendar = (CalendarPickerView) v.findViewById(R.id.calendar_view);
        mChooseDateButton = (Button) v.findViewById(R.id.choose_date_button);


        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater2 = getActivity().getLayoutInflater();
        CalendarPickerView calendar_button = (CalendarPickerView) inflater2.inflate(R.layout.fragment_date_picker, null);
        calendar_button.init(today, nextYear.getTime())
                .withSelectedDate(today);


// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Choose a date")
                .setTitle("Choose a date")
                .setView(calendar_button);



// 3. Get the AlertDialog from create()
        final AlertDialog dialog = builder.create();







        mChooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. Instantiate an AlertDialog.Builder with its constructor

                dialog.show();
            }
        });




        return v;
    }
}
