package edu.purdue.app.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

/**
 * Created by mike on 1/21/15.
 */
public class NoInternetDialog {

    private Context context;

    public NoInternetDialog(Context context) {
        this.context = context;
    }

    public void show() {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle("Internet Access Required");
        alertDialog.setMessage(
                "Purdue App does not currently support offline access. " +
                "Please connect to the internet and try again."
        );

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch(which)
                {
                    case AlertDialog.BUTTON_POSITIVE:
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;
                    case AlertDialog.BUTTON_NEUTRAL:
                        intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        context.startActivity(intent);

                }
            }
        };

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Exit", listener);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Network settings", listener);

        alertDialog.show();

    }

}
