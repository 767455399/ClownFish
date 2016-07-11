package com.example.administrator.clownfish.Activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.modle.StoreInformationModle;
import com.example.administrator.clownfish.myCallBack;
import com.example.administrator.clownfish.network.NetworkRequest;
import com.example.administrator.clownfish.present.StoreInformationPresent;
import com.example.administrator.clownfish.tool.ImagePath;
import com.example.administrator.clownfish.tool.SwipeRefreshLayoutUtil;
import com.example.administrator.clownfish.tool.ToastUtil;
import com.example.administrator.clownfish.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StoreInformationActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private String path = "http://www.jutongbao.com/jtb/phone/newshop_list.action?companyCode=05710001&userId=131D5455-C4DD-410E-8EDE-8BD1A4E371E4&pageIndex=1";
    private RecyclerView storeInformationRecyclerView;
    private StoreInformationAdapter storeInformationAdapter;
    private StoreInformationPresent present;
    public  Boolean nextPageIsLoading = false;
    public static Boolean refresh = false;
    private List<StoreInformationModle.ListBean> storeInformationList = new ArrayList();
    public SwipeRefreshLayout storeInformationSwipeRefreshLayout;
    public int pageIndex = 1;
    private int pageSize=10;
    private Boolean hasNextPage;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_store_information);
        storeInformationSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.storeInformationSwipeRefreshLayout);
        SwipeRefreshLayoutUtil.initStyle(storeInformationSwipeRefreshLayout);
        storeInformationSwipeRefreshLayout.setOnRefreshListener(this);
        storeInformationRecyclerView = (RecyclerView) findViewById(R.id.storeInformationRecyclerView);
        storeInformationRecyclerView.setLayoutManager(new LinearLayoutManager(StoreInformationActivity.this));
        storeInformationAdapter = new StoreInformationAdapter();
        storeInformationRecyclerView.setAdapter(storeInformationAdapter);
        storeInformationRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) storeInformationRecyclerView.getLayoutManager();
                if (newState == recyclerView.SCROLL_STATE_IDLE) {
                    int lastVisableItem =manager.findLastVisibleItemPosition();
                    int totalItem=manager.getItemCount();
                    if(lastVisableItem==(totalItem-1)){
                        if(!nextPageIsLoading||hasNextPage){
                            refresh=false;
                            nextPageIsLoading=true;
                            present.getStoreInformation(refresh);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void loadData() {
        present = new StoreInformationPresent();
        present.bind(this);
        refresh = false;
        present.getStoreInformation(refresh);
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        refresh = true;
        nextPageIsLoading=true;
        present.getStoreInformation(refresh);
    }

    public void setStoreInformation(StoreInformationModle storeInformationModle) {
        if(refresh){
            storeInformationList.clear();
        }
        if(storeInformationModle.getList().size()<pageSize){
            hasNextPage=true;
        }else{
            hasNextPage=false;
        }
        if(pageIndex==1){
            if(storeInformationModle.getList().size()>0){

            }else{

            }
        }
        storeInformationList.addAll(storeInformationModle.getList());
        storeInformationAdapter.notifyDataSetChanged();
    }

    class StoreInformationAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(StoreInformationActivity.this).inflate(R.layout.item_store_information, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final StoreInformationModle.ListBean listBean = storeInformationList.get(position);
            Picasso.with(StoreInformationActivity.this).load(ImagePath.imagePath[position]).error(R.mipmap.ic_launcher).into(holder.shopImageView);
            holder.storeInformationNameTextView.setText(listBean.getSalesMan());
            holder.storeInformationShopTextView.setText(listBean.getSn());
            holder.storeInformationToExamineTextView.setText(listBean.getStn());
            holder.storeInformationTimeTextView.setText(listBean.getT());

        }

        @Override
        public int getItemCount() {
            return storeInformationList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView shopImageView;
        private TextView storeInformationNameTextView;
        private TextView storeInformationShopTextView;
        private TextView storeInformationToExamineTextView;
        private TextView storeInformationTimeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            shopImageView = (CircleImageView) itemView.findViewById(R.id.shopImageView);
            storeInformationNameTextView = (TextView) itemView.findViewById(R.id.storeInformationNameTextView);
            storeInformationShopTextView = (TextView) itemView.findViewById(R.id.storeInformationShopTextView);
            storeInformationToExamineTextView = (TextView) itemView.findViewById(R.id.storeInformationToExamineTextView);
            storeInformationTimeTextView = (TextView) itemView.findViewById(R.id.storeInformationTimeTextView);
        }
    }
}
