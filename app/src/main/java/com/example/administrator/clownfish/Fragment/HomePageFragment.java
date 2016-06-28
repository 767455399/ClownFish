package com.example.administrator.clownfish.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.clownfish.Activity.ImageViewActivity;
import com.example.administrator.clownfish.Activity.NBAActivity;
import com.example.administrator.clownfish.BaseFragment;
import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.view.AllShowedGridView;
import com.example.administrator.clownfish.view.PagerIndicator;
import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/17 14:47
 * 修改人：WangQing
 * 修改时间：2016/5/17 14:47
 * 修改备注：
 */
public class HomePageFragment extends BaseFragment {
    private String[] path = {"http://www.wallcoo.com/sport/NBA_Lakers_0910_Finals_Champions/wallpapers/1280x800/10_finals_wallpaper_mvp.jpg",
            "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1212/05/c1/16366597_1354692861082.jpg",
            "http://f1.diyitui.com/53/3d/db/27/ec/48/0f/0b/30/6d/3a/88/a3/d5/7e/aa.jpg",
            "http://uploads.xuexila.com/allimg/1511/659-1511010159561L.jpg"};
    private String[] image = {
            "http://h.hiphotos.baidu.com/image/pic/item/7a899e510fb30f24fd5cfc29cb95d143ac4b03d1.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/f31fbe096b63f62475632a298444ebf81a4ca3d9.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/7a899e510fb30f24fd5cfc29cb95d143ac4b03d1.jpg"
    };
    private String[] convenience={"NBA","天气","名言","网点","语音验证","二维码","大数据"};


    private RecyclerView homePageRecyclerView;
    private HomePageAdapter homePageAdapter;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Handler handler = new Handler();
    private boolean isDragging = false;
    private boolean bannerAdapterSet = false;
    private PagerIndicator pagerIndicator;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    private AllShowedGridView homePagerServiceGriView;
    private AllShowedGridViewAdapter allShowedGridViewAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        homePagerServiceGriView=(AllShowedGridView)view.findViewById(R.id.homePagerServiceGriView);
        homePageRecyclerView = (RecyclerView) view.findViewById(R.id.homePageRecyclerView);
        pagerIndicator = (PagerIndicator) view.findViewById(R.id.pagerIndicator);
        homePageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homePageRecyclerView.setLayoutManager(linearLayoutManager);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        pagerIndicator.setViewPager(viewPager);
        bannerAdapterSet = true;
        homePageAdapter=new HomePageAdapter();
        homePageRecyclerView.setAdapter(homePageAdapter);
        setListener();
        showAllGridView();
    }

    private void showAllGridView() {
        homePagerServiceGriView.post(new Runnable() {
            @Override
            public void run() {
                allShowedGridViewAdapter=new AllShowedGridViewAdapter();
                homePagerServiceGriView.setAdapter(allShowedGridViewAdapter);
            }
        });
      homePagerServiceGriView.setOnItemClickListener(new AllShowedGridView.OnItemClickListener() {
          @Override
          public void onClick(int position) {
            if("NBA".equals(convenience[position])){
                Intent intent=new Intent();
                intent.setClass(getActivity(), NBAActivity.class);
                startActivity(intent);
            }
          }
      });


    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        isDragging = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        isDragging = false;
                        break;
                }
            }
        });
    }


    @Override
    protected void loadData() {

    }

    class HomePageAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_page, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Picasso.with(getActivity()).load(image[position]).error(R.mipmap.ic_launcher).into(holder.itemImageView);
        }

        @Override
        public int getItemCount() {
            return image.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImageView = (ImageView) itemView.findViewById(R.id.itemImageView);
        }
    }

    class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return path.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
         /*   final AdListModel.Info info = adList.get(position);*/
            View imageLayout = LayoutInflater.from(getBaseActivity()).inflate(R.layout.item_index_ad, container, false);
            final ImageButton itemIndexAdImageView = (ImageButton) imageLayout.findViewById(R.id.itemIndexAdImageView);
            Picasso.with(getActivity()).load(path[position]).error(R.mipmap.ic_launcher).into(itemIndexAdImageView);
            itemIndexAdImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), ImageViewActivity.class);
                    Bundle bundle = new Bundle();                      //创建Bundle对象
                    bundle.putString("imagePath", path[position]);     //装入数据
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            container.addView(imageLayout);
            return imageLayout;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (bannerAdapterSet && !isDragging && viewPager.getCurrentItem() < 4) {
                if (viewPager.getCurrentItem() == 3) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
            handler.postDelayed(runnable, 4000);
        }
    };
    class AllShowedGridViewAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return convenience.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(null==convertView){
                convertView=LayoutInflater.from(getActivity()).inflate(R.layout.item_home_pager_service,null);
            }
            TextView itemIndexServiceTextView=(TextView)convertView.findViewById(R.id.itemIndexServiceTextView);
            ImageView itemIndexServiceImageView=(ImageView)convertView.findViewById(R.id.itemIndexServiceImageView);
            itemIndexServiceTextView.setText(convenience[position]);
            return convertView;
        }
    }

}
