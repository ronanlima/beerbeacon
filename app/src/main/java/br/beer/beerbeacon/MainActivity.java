package br.beer.beerbeacon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.estimote.sdk.SystemRequirementsChecker;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import br.beer.beerbeacon.bean.Tonel;

/**
 * Example of using Folding Cell with ListView and ListAdapter
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get our list view
//        ListView theListView = (ListView) findViewById(R.id.mainListView);
        RecyclerView theListView = (RecyclerView) findViewById(R.id.mainListView);

        // prepare elements to display
//        final ArrayList<Item> items = Item.getTestingList();
        final ArrayList<Tonel> items = Tonel.getTonelList();

        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set elements to adapter
        theListView.setLayoutManager(new LinearLayoutManager(this));
        theListView.setAdapter(adapter);

        // set on click event listener to list view
//        theListView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // toggle clicked cell state
//                ((FoldingCell) view).toggle(false);
//                // register in adapter that state for selected cell is toggled
//                adapter.registerToggle(0);
//            }
//        });
//        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                // toggle clicked cell state
//                ((FoldingCell) view).toggle(false);
//                // register in adapter that state for selected cell is toggled
//                adapter.registerToggle(pos);
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }
}
