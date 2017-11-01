package com.team.killskills.nukvoy_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Sacha on 01/11/2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview_android_actionbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu_item, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) searchViewItem.getActionView();
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    boolean toggle;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //toggle = true;
        switch (item.getItemId()) {
            case R.id.toggle_map_list:

                if(toggle){
                    //change your view and sort it by Alphabet
                    item.setIcon(R.drawable.ic_map_black_24dp);
                    item.setTitle("Map");
                    toggle=false;
                }else{
                    //change your view and sort it by Date of Birth
                    item.setIcon(R.drawable.ic_playlist_add_black_24dp);
                    item.setTitle("List");
                    toggle=true;
                }
                return true;



        }
        return super.onOptionsItemSelected(item);


    }

}
