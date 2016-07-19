package com.example.administrator.clownfish.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.clownfish.Activity.ContactsActivity;
import com.example.administrator.clownfish.Activity.DisplayPictureActivity;
import com.example.administrator.clownfish.Activity.ImageViewActivity;
import com.example.administrator.clownfish.Activity.PhotoBrowseActivity;
import com.example.administrator.clownfish.Activity.TextActivity;
import com.example.administrator.clownfish.BaseFragment;
import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.view.AllShowedGridView;
import com.example.administrator.clownfish.view.Shimmer;
import com.example.administrator.clownfish.view.ShimmerTextView;
import com.squareup.picasso.Picasso;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/17 16:08
 * 修改人：WangQing
 * 修改时间：2016/5/17 16:08
 * 修改备注：
 */
public class MineFragment extends BaseFragment {
    private ShimmerTextView balanceTextView;
    private Shimmer shimmer;
    private AllShowedGridView convenience_services;
    private AllShowedGridView life_services;
    private ConvenienceServicesAdapter convenienceServicesAdapter;
    private LifeServicesAdapter lifeServicesAdapter;
    private String convenienceServicesString[] = {"联系人", "信用卡还款", "水费", "电费", "煤气"};
    private String lifeImageViewPath[] = {"http://images.ali213.net/picfile/pic/2013-01-22/927_p19.jpg",
            "http://img.taopic.com/uploads/allimg/121118/240505-12111Q9533274.jpg",
            "http://www.sucaitianxia.com/Photo/pic/200910/nbzbs32.jpg",
            "http://pic.6188.com/upload_6188s/flashAll/s800/20101119/1290150940LjRxxj.jpg",
            "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1401/06/c0/30337479_1388996545345.jpg",
            "http://img.article.pchome.net/00/31/62/09/pic_lib/wm/hr_05.jpg",
            "http://pic3.bbzhi.com/tiyubizhi/kaiwenjianeitekev/design_sport_502265_11.jpg",
            "http://www.afinance.cn/new/UploadFiles_2266/201312/20131231131156126.jpg",
            "http://a1.att.hudong.com/82/62/01300000239710122122620399472.jpg",
            "http://pic3.nipic.com/20090624/2888748_001522632_2.jpg",
            "http://pic5.nipic.com/20100226/3279936_091345006264_2.jpg",
            "http://news.qiyue.com/uploadfile/2014/1007/20141007091434164.jpg",
            "http://pic10.nipic.com/20100928/5878474_220011029659_2.jpg",
            "http://imgsrc.baidu.com/forum/pic/item/730e0cf3d7ca7bcbcbbe73aabe096b63f724a86a.jpg",
            "http://pic7.nipic.com/20100529/1866517_203719583362_2.jpg"
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        convenience_services = (AllShowedGridView) view.findViewById(R.id.convenience_services);
        life_services = (AllShowedGridView) view.findViewById(R.id.life_services);
        balanceTextView=(ShimmerTextView)view.findViewById(R.id.balanceTextView);
        setServiceAdapter();
        setLifeServiceAdapter();
        start();
    }

    private void start() {
        if (shimmer != null && shimmer.isAnimating()) {
            shimmer.cancel();
        } else {
            shimmer = new Shimmer();
            shimmer.start(balanceTextView);
        }
    }



    private void setLifeServiceAdapter() {
        life_services.post(new Runnable() {
            @Override
            public void run() {
                lifeServicesAdapter = new LifeServicesAdapter();
                life_services.setAdapter(lifeServicesAdapter);
            }
        });
        life_services.setOnItemClickListener(new AllShowedGridView.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent();
               /* intent.setClass(getActivity(), DisplayPictureActivity.class);
                Bundle bundle=new Bundle();
                bundle.putStringArray("image", lifeImageViewPath);
                intent.putExtras(bundle);
                startActivity(intent);*/
                intent.setClass(getActivity(), ImageViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("imagePath", lifeImageViewPath[position]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    public void setServiceAdapter() {
        convenience_services.post(new Runnable() {
            @Override
            public void run() {
                convenienceServicesAdapter = new ConvenienceServicesAdapter();
                convenience_services.setAdapter(convenienceServicesAdapter);
            }
        });
        convenience_services.setOnItemClickListener(new AllShowedGridView.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if ("联系人".equals(convenienceServicesString[position])) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),ContactsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    class ConvenienceServicesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return convenienceServicesString.length;
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
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_index_service, null);
            }
            ImageView itemIndexServiceImageView = (ImageView) convertView.findViewById(R.id.itemIndexServiceImageView);
            TextView itemIndexServiceTextView = (TextView) convertView.findViewById(R.id.itemIndexServiceTextView);
            itemIndexServiceTextView.setText(convenienceServicesString[position]);
            return convertView;
        }
    }

    class LifeServicesAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return lifeImageViewPath.length;
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
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_life_services, null);
            }
            ImageView life_imageView = (ImageView) convertView.findViewById(R.id.life_imageView);
            Picasso.with(getActivity()).load(lifeImageViewPath[position]).error(R.mipmap.ic_launcher).into(life_imageView);
            return convertView;
        }
    }
}
