package app.meutreino;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

//import app.meutreino.adapters.HomeGridViewMenuAdapter;

public class HomeActivity extends MainActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    RecyclerView recyclerViewHome;
    Context context;
    ArrayList arrayList;

    public static String[] gridViewStrings = {
            //"Início",
            "Categorias",
            "Exercícios",
            "Treino",
            "Meu peso",
            "Minhas medidas",
            //"Início",
            "Categorias",
            "Exercícios",
            "Treino",
            "Meu peso",
            "Minhas medidas"
    };
    public static int[] gridViewImages = {
            //R.mipmap.settings,
            //R.mipmap.ic_launcher,
            R.mipmap.settings,
            R.mipmap.ic_launcher,
            R.mipmap.settings,
            R.mipmap.ic_launcher,
            R.mipmap.settings,
            R.mipmap.ic_launcher,
            R.mipmap.settings,
            R.mipmap.ic_launcher,
            R.mipmap.settings,
            R.mipmap.ic_launcher
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /**
         *  We will not use setContentView in this activty
         *  Rather than we will use layout inflater to add view in FrameLayout of our base activity layout*/

        /**
         * Adding our layout to parent class frame layout.
         */
        getLayoutInflater().inflate(R.layout.activity_home, frameLayout);

        /**
         * Setting title and itemChecked
         */
       /* mDrawerList.setItemChecked(position, true);
        setTitle(Html.fromHtml("<font color='#ffffff'>" + listArray[position] + "</font>"));

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        gridView = (GridView) findViewById(R.id.grid);
        gridView.setNumColumns(2);
        gridView.setAdapter(new HomeGridViewMenuAdapter(this, gridViewStrings, gridViewImages));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the GridView selected/clicked item text
                //String selectedItem = parent.getItemAtPosition(position).toString();

                // Display the selected/clicked item text and position on TextView
                Snackbar.make(view, "GridView item clicked : " + gridViewStrings[position]
                        + "\nAt index position : " + position, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                openActivity(position + 1);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the GridView selected/clicked item text
                //String selectedItem = parent.getItemAtPosition(position).toString();

                // Display the selected/clicked item text and position on TextView
                Snackbar.make(view, "GridView item clicked : " + gridViewStrings[position]
                        + "\nAt index position : " + position, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                openActivity(position + 1);
            }
        });

        initInstances();*/
    }

    private void initInstances() {
        /*rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_android_layout);
        collapsingToolbarLayoutAndroid.setTitle("MEUtreino");*/
    }
}
