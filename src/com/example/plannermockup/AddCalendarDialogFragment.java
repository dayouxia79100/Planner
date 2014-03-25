package com.example.plannermockup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

/**
 * Created by dayouxia on 2/14/14.
 */
public class AddCalendarDialogFragment extends DialogFragment {

    private static final String TAG = "com.example.app.AddCalendarDialogFragment";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Log.v(AddCalendarDialogFragment.class.toString(),"onCreateDialog is called");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.are_you_going)
                .setPositiveButton(R.string.yes_going, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
