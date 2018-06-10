package com.example.madaim.doron;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Madaim on 10/06/2018.
 */

public class MyDialog extends DialogFragment {
     public final static int ADDING_DIALOG = 1;
     private int reqCode;
     private static MyDialog dlg = null;



    public static MyDialog newInstance(int requestCode) {
        if (dlg == null)
            dlg = new MyDialog();
        Bundle args = new Bundle();
        args.putInt("rc", requestCode);
        dlg.setArguments(args);
        return dlg;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
         LayoutInflater inflater = getActivity().getLayoutInflater();

         builder.setView(inflater.inflate(R.layout.adding, null))
                 .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                             dismiss();
                    }
                });
        return builder.create();
    }


}