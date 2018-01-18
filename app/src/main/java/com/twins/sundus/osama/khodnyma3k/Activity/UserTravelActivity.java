package com.twins.sundus.osama.khodnyma3k.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.twins.sundus.osama.khodnyma3k.Adapter.MenuAdapter;
import com.twins.sundus.osama.khodnyma3k.Classes.DrawerItem;
import com.twins.sundus.osama.khodnyma3k.Fragment.HomeFragment;
import com.twins.sundus.osama.khodnyma3k.Fragment.OrderFragment;
import com.twins.sundus.osama.khodnyma3k.Fragment.TravelsFragment;
import com.twins.sundus.osama.khodnyma3k.Fragment.myTripFragment;
import com.twins.sundus.osama.khodnyma3k.Interface.OnDrawerItemClickListener;
import com.twins.sundus.osama.khodnyma3k.R;
import com.twins.sundus.osama.khodnyma3k.Util.FragmentsUtil;

import java.util.ArrayList;
import java.util.Arrays;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.twins.sundus.osama.khodnyma3k.Interface.AppConstant.KEY;

public class UserTravelActivity extends AppCompatActivity {
    private static ArrayList<DrawerItem> items;
    private ArrayList<String> itemsTitles;
    private static int nav_back = 0;
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout Drawer;
    private Fragment fragment = null;
    private ActionBarDrawerToggle mDrawerToggle;
    private ImageView menu;
    private TypedArray navMenuIcons;
    private TypedArray navMenuIcons_on;
    private TextView tv_title;
    private int goDialog;
    private ScrollView linearLayout;
    private Button go_to_live;
    private ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_travel);

        goDialog = 0;
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        go_to_live = findViewById(R.id.go_to_live);
        setSupportActionBar(toolbar);
        itemsTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.drawerItems)));
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navMenuIcons_on = getResources().obtainTypedArray(R.array.nav_drawer_icons_on);
        items = new ArrayList<>();
        for (int i = 0; i < itemsTitles.size(); i++)
            items.add(new DrawerItem(itemsTitles.get(i), navMenuIcons.getResourceId(i, -1),
                    navMenuIcons_on.getResourceId(i, -1), i == 0 ? true : false));
        navMenuIcons.recycle();
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        linearLayout = findViewById(R.id.LinearLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MenuAdapter(this, items, new OnDrawerItemClickListener() {
            @Override
            public void onClick(int position) {

                Drawer.closeDrawer(linearLayout);
                startEvent(position);
                updateDrawer(position);
            }

            @Override
            public void onPhotoClick(View view) {
            }


        });
        mRecyclerView.setAdapter(mAdapter);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }


        }; // Drawer Toggle Object Made
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        menu = (ImageView) findViewById(R.id.menu);
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent requestActivity = new Intent(UserTravelActivity.this, SearchActivity.class);
                startActivity(requestActivity);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });

        Drawer.setDrawerListener(mDrawerToggle);


        /****************check intent**********************/
        Intent intent = getIntent();
        if (intent.hasExtra(KEY)) {
            if (intent.getIntExtra(KEY, 1) == 2) {
                FragmentsUtil.replaceFragment(this, R.id.frame_layout, new myTripFragment());
                tv_title.setText(getResources().getText(R.string.myTravel));
                updateDrawer(2);
            } else if (intent.getIntExtra(KEY, 1) == 3) {
                tv_title.setText(getResources().getText(R.string.tvOrder));
                FragmentsUtil.replaceFragment(this, R.id.frame_layout, new OrderFragment());
                updateDrawer(3);
//            } else if (intent.getIntExtra(KEY, 0) == 1) {
//                FragmentsUtil.addFragment(this, R.id.frame_layout, new EnterDataDeviceFragment());
//                tv_title.setText(getResources().getText(R.string.enter_data_device));
//                updateDrawer(3);
            } else if(intent.getIntExtra(KEY, 1) == 0) {
                FragmentsUtil.replaceFragment(this, R.id.frame_layout, new TravelsFragment());
                tv_title.setText(getResources().getText(R.string.tvtravel));
                updateDrawer(1);
            }
        }

        /*************************/
        go_to_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Drawer.isDrawerOpen(linearLayout)) {
                    Drawer.closeDrawer(linearLayout);
                    mAdapter.notifyDataSetChanged();
                }
                //dialog
                final Dialog dialog = new Dialog(UserTravelActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.setContentView(R.layout.go_live_dialog);
                final Button driver = dialog.findViewById(R.id.driver);
                final Button user = dialog.findViewById(R.id.user);
                final EditText tvGo = dialog.findViewById(R.id.tvGo);
                TextView tvOk = dialog.findViewById(R.id.tvOk);
                TextView tvCancel = dialog.findViewById(R.id.tvCancel);
                tvGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /********* go to search************/
                    }
                });
                driver.setBackgroundColor(Color.parseColor("#000000"));
                user.setBackground(getResources().getDrawable(R.drawable.edit_text));

                driver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        driver.setBackgroundColor(Color.parseColor("#000000"));
                        driver.setTextColor(Color.parseColor("#ffffff"));
                        user.setTextColor(Color.parseColor("#000000"));
                        user.setBackground(getResources().getDrawable(R.drawable.edit_text));
                        tvGo.setVisibility(View.VISIBLE);
                        tvGo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(UserTravelActivity.this,SearchActivity.class);
                                startActivity(intent);
                            }
                        });
                        goDialog = 0;
                    }
                });
                user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tvGo.setVisibility(View.GONE);
                        user.setBackgroundColor(Color.parseColor("#000000"));
                        driver.setBackground(getResources().getDrawable(R.drawable.edit_text));
                        driver.setTextColor(Color.parseColor("#000000"));
                        user.setTextColor(Color.parseColor("#ffffff"));
                        goDialog = 1;
                    }
                });
                tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (goDialog == 0) {
                            /**** Driver Activity****/
                            Intent requestActivity = new Intent(UserTravelActivity.this, RequestActivity.class);
                            startActivity(requestActivity);
                            finish();
                            dialog.cancel();
                            goDialog = 0;
                        } else {
                            /**** User Activity****/
                            Intent intent = new Intent(UserTravelActivity.this, UserTravelActivity.class);
                            intent.putExtra(KEY,0);
                            startActivity(intent);
                            finish();
                            dialog.cancel();
                            goDialog = 0;
                        }
                    }
                });

                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        goDialog = 0;
                    }
                });
                dialog.show();
            }
        });

    }

    public void updateDrawer(int position) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setIsSelected(i == position ? true : false);
            Log.i("xcxc", position + "");
        }
        mAdapter.notifyDataSetChanged();
    }

    public void startEvent(int position) {

        switch (position) {
            case 0:
                nav_back = 0;
                fragment = new HomeFragment();
                tv_title.setText(getResources().getText(R.string.tvtravel));
                menu.setVisibility(View.VISIBLE);
                Intent requestActivity = new Intent(UserTravelActivity.this, MainActivity.class);
                startActivity(requestActivity);
                finish();
                break;
            case 1:
                nav_back = 1;
                menu.setVisibility(View.VISIBLE);
                break;
            case 2:
                tv_title.setText(getResources().getText(R.string.myTravel));
                FragmentsUtil.replaceFragment(this, R.id.frame_layout, new myTripFragment());

                menu.setVisibility(View.VISIBLE);
                break;
            case 3:
                tv_title.setText(getResources().getText(R.string.tvOrder));
                FragmentsUtil.replaceFragment(this, R.id.frame_layout, new OrderFragment());
                menu.setVisibility(View.VISIBLE);
                break;
            case 4:
                nav_back = 1;

                menu.setVisibility(View.VISIBLE);
                break;
            case 5:
                nav_back = 1;

                menu.setVisibility(View.VISIBLE);
                break;
        }
        updateDrawer(position);
    }

    private void toggleSlidingMenu() {
        if (Drawer.isDrawerOpen(linearLayout)) {
            Drawer.closeDrawer(linearLayout);
            mAdapter.notifyDataSetChanged();
        } else {
            Drawer.openDrawer(linearLayout);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
//            FragmentsUtil.replaceFragment(this, R.id.frame_layout, new OrderFragment());
//            updateDrawer(3);
//        } else
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return;
        } else {
            Intent requestActivity = new Intent(UserTravelActivity.this, MainActivity.class);
            startActivity(requestActivity);
            finish();
        }
    }
}
