package hoshikoo.c4q.nyc.itunessearch;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                Bundle bundle = new Bundle();

                switch (menuItem.getItemId()){

                    case R.id.music:
                        bundle.putString("media_type", "music");
                        Toast.makeText(getApplicationContext(), "Music Selected", Toast.LENGTH_SHORT).show();
                        setFragment(bundle);
                        return true;

                    case R.id.software:
                        bundle.putString("media_type","software");
                        Toast.makeText(getApplicationContext(),"Software Selected",Toast.LENGTH_SHORT).show();
                        setFragment(bundle);
                        return true;

                    case R.id.movies:
                        bundle.putString("media_type", "movie");
                        Toast.makeText(getApplicationContext(),"Movies Selected",Toast.LENGTH_SHORT).show();
                        setFragment(bundle);
                        return true;

                    case R.id.podcasts:
                        bundle.putString("media_type","podcast");
                        Toast.makeText(getApplicationContext(),"Podcasts Selected",Toast.LENGTH_SHORT).show();
                        setFragment(bundle);
                        return true;

                    case R.id.musicVideos:
                        bundle.putString("media_type","musicVideo");
                        Toast.makeText(getApplicationContext(),"Videos Selected",Toast.LENGTH_SHORT).show();
                        setFragment(bundle);
                        return true;

                    case R.id.tvShow:
                        bundle.putString("media_type","tvShow");
                        Toast.makeText(getApplicationContext(),"TV Shows Selected",Toast.LENGTH_SHORT).show();
                        setFragment(bundle);
                        return true;

                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setFragment(Bundle bundle){
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

}
