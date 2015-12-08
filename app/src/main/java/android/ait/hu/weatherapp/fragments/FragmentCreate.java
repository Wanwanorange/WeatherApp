package android.ait.hu.weatherapp.fragments;

import android.ait.hu.weatherapp.IMainFragmentHandler;
import android.ait.hu.weatherapp.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Wanchen on 11/28/2015.
 */
public class FragmentCreate extends DialogFragment {

    public static final String TAG = "FragmentCreate";
    public EditText et_city;
    private IMainFragmentHandler iMainFragmentHandler;

    public interface FragmentCreateInterface{

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            iMainFragmentHandler = (IMainFragmentHandler) activity;
        }catch (ClassCastException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alertDialogBuilder=  new  AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_create, null);
        et_city = (EditText) view.findViewById(R.id.et_city);
        setCancelable(false);
        alertDialogBuilder.setView(view);

        alertDialogBuilder.setTitle("Enter City")

                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                iMainFragmentHandler.addCity(et_city.getText().toString());
                                Toast.makeText(getActivity(), "Added: " + et_city.getText().toString(), Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );

        return alertDialogBuilder.create();
    }



}

