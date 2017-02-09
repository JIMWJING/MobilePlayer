package yewooo.com.mobileplayer.activity;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

import yewooo.com.mobileplayer.R;
import yewooo.com.mobileplayer.base.BasePager;
import yewooo.com.mobileplayer.pager.AudioPager;
import yewooo.com.mobileplayer.pager.NetAudioPager;
import yewooo.com.mobileplayer.pager.NetVideoPager;
import yewooo.com.mobileplayer.pager.VideoPager;

public class MainActivity extends FragmentActivity {

    private FrameLayout fl_main_content;
    private RadioGroup rg_bottom_tag;

    private ArrayList<BasePager> basePagers;

    private  int postition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_main_content =(FrameLayout) findViewById(R.id.fl_main_content);
        rg_bottom_tag=(RadioGroup)findViewById(R.id.rg_bottom_tag);

        basePagers=new ArrayList<>();
        basePagers.add(new AudioPager(this));
        basePagers.add(new NetAudioPager(this));
        basePagers.add(new VideoPager(this));
        basePagers.add(new NetVideoPager(this));

        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        rg_bottom_tag.check(R.id.rb_video);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_video:{
                    postition=0;
                    break;
                }
                case R.id.rb_audio:{
                    postition=1;
                    break;
                }
                case R.id.rb_net_video:{
                    postition=2;
                    break;
                }
                case R.id.rb_net_audio:{
                    postition=3;
                    break;
                }
                default:{
                    postition=0;
                    break;
                }
            }
            SetFragement();
        }
    }

    public void SetFragement(){
        FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction ft=manager.beginTransaction();

        ft.replace(R.id.fl_main_content,new Fragment(){
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                BasePager pager=getBasePager();
                if(pager!=null){
                    return  pager.rootView;
                }
                return null;
            }
        });

        ft.commit();
    }

    private BasePager getBasePager() {
        BasePager basePager=basePagers.get(postition);

        if(basePager!=null&&!basePager.isinitData){
            basePager.initData();
            basePager.isinitData=true;
        }
        return  basePager;
    }
}
