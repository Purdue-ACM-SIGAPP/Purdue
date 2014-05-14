package edu.purdue.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.askerov.dynamicgid.DynamicGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static android.widget.AdapterView.OnItemClickListener;


public class MainMenuActivity extends Activity implements OnItemClickListener {

    private DraggableGridView dgv;
    private HashMap<Integer, MainMenuItem> gridItems; //Key is the view ID. Access with view.getID()

    private DynamicGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);
        gridView.setAdapter(new CustomGridAdapter(this,
                new ArrayList<String>(Arrays.asList(new String[] { "Hello", "This", "is", "the", "New", "grid", "view"})),
                3));
//        add callback to stop edit mode if needed
//        gridView.setOnDropListener(new DynamicGridView.OnDropListener()
//        {
//            @Override
//            public void onActionDrop()
//            {
//                gridView.stopEditMode();
//            }
//        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                gridView.startEditMode();
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainMenuActivity.this, parent.getAdapter().getItem(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        // TODO: Below is a sample test of the web view. Should be removed later.
        Button testButton = ((Button) findViewById(R.id.webViewButton));
        final TextView testTextView = ((TextView) findViewById(R.id.urlTextField));
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = testTextView.getText().toString();
                Intent webViewIntent = new Intent(getBaseContext(), WebViewActivity.class);
                webViewIntent.putExtra("URL_ENDPOINT", url);
                startActivity(webViewIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (gridView.isEditMode()) {
            gridView.stopEditMode();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(MainMenuActivity.this, adapterView.getAdapter().getItem(i).toString(),
                Toast.LENGTH_SHORT).show();

        /*String url = gridItems.get(view.getId()).url;
        Intent webViewIntent = new Intent(getBaseContext(), WebViewActivity.class);
        webViewIntent.putExtra("URL_ENDPOINT", url);
        startActivity(webViewIntent);*/
    }
}
