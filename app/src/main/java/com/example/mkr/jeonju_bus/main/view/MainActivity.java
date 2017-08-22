package com.example.mkr.jeonju_bus.main.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mkr.jeonju_bus.R;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mkr on 2017-08-21.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ib_menu)
    ImageButton ib_menu;

    @BindView(R.id.activity_main)
    LinearLayout container;

    FragmentTabHost mTabHost;
    SlidingRootNav sliding_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
        setLisenter();
    }

    private void init() {
        sliding_menu = new SlidingRootNavBuilder(this)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();


        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        FragmentTabHost.TabSpec spec;
        spec = mTabHost.newTabSpec("recent_use");
        spec.setContent(tag -> {
            return findViewById(android.R.id.tabcontent);
        });
        spec.setIndicator(createTabView(R.drawable.selector_clock, "최근이용"));
        mTabHost.addTab(spec, MainRecentUseFrag.class,null);

        spec = mTabHost.newTabSpec("bookmark");
        spec.setContent(tag -> {
            return findViewById(android.R.id.tabcontent);
        });
        spec.setIndicator(createTabView(R.drawable.selector_bookmark, "즐겨찾기"));
        mTabHost.addTab(spec, MainBookmarkFrag.class, null);
    }

    private View createTabView(final int id, final String text) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabs_icon, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageDrawable(getResources().getDrawable(id));
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        textView.setText(text);
        return view;
    }

    private void setLisenter() {

        ib_menu.setOnClickListener(v->{
            if(sliding_menu.isMenuHidden()){
                sliding_menu.openMenu();
            } else{
                sliding_menu.closeMenu();
            }
        });

        container.setOnClickListener(v->{
            if(!sliding_menu.isMenuHidden()){
                sliding_menu.closeMenu();
            }
        });
    }
}