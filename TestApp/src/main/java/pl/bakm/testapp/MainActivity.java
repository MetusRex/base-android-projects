package pl.bakm.testapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import pl.bakm.testapp.cache.ImageCacheManager;
import pl.bakm.testapp.http.OkHttpStack;
import pl.bakm.testapp.impl.content.ContentFragment;
import pl.bakm.testapp.impl.content.HomeFragment;
import pl.bakm.testapp.utils.AppLog;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends FragmentActivity {

    private static int IMAGECACHE_SIZE = 1024 * 1024 * 10;

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mList;
    private ArrayAdapter<String> mAdapter;

    private TextView mTitleBar;

    private static final List<String> mMenuItems = new ArrayList<String>();

    private CharSequence mTitle;

    static {
        mMenuItems.add("Home");
        mMenuItems.add("Json Feed");
    }

    private RequestQueue mRequestQueue;
    private Integer mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        ImageCacheManager.getInstance().init(this.mRequestQueue, IMAGECACHE_SIZE);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();
        selectItem(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.mRequestQueue.start();
    }

    @Override
    protected void onStop() {
        this.mRequestQueue.stop();
        super.onStop();
    }

    private void initViews() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mList = (ListView) findViewById(R.id.left_drawer);
        mTitleBar = (TextView) findViewById(R.id.title_bar_text);
        mAdapter = new ArrayAdapter<String>(this, R.layout.menu_item, mMenuItems);

        mList.setAdapter(mAdapter);

        mList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawer,         /* DrawerLayout object */
                R.drawable.favicon,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                mTitleBar.setText("Menu");
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawer.setDrawerListener(mDrawerToggle);

        ImageView iv = (ImageView) findViewById(R.id.app_icon);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean open = mDrawer.isDrawerOpen(Gravity.START);
                if (open) {
                    mDrawer.closeDrawer(Gravity.START);
                } else {
                    mDrawer.openDrawer(Gravity.START);
                }
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        AppLog.w("onItemLCick: " + position);
        if (mPosition == null || mPosition != position) {
            mPosition = position;

            Fragment fragment;
            if (position == 0) { //Home screen
                fragment = new HomeFragment();
            } else if (position == 1) { //Json feed screen
                fragment = new ContentFragment(mRequestQueue, this);
            } else {
                return;
            }

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();

            // Highlight the selected item, update the title, and close the drawer
            mList.setItemChecked(position, true);
        }
        setTitle(mMenuItems.get(position));
        mDrawer.closeDrawer(mList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        mTitleBar.setText(mTitle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}