package com.example.administrator.clownfish.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.modle.ProductInformationModle;
import com.example.administrator.clownfish.myCallBack;
import com.example.administrator.clownfish.network.NetworkRequest;
import com.example.administrator.clownfish.present.ProductInformationPresent;
import com.example.administrator.clownfish.tool.ImagePath;
import com.example.administrator.clownfish.tool.ToastUtil;
import com.example.administrator.clownfish.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductInformationActivity extends BaseActivity {
    private ProductInformationPresent present;
    private String path = "http://www.jutongbao.com/jtb/phone/score_mainXsdcSjmc.action?companyCode=05710001&typeId=57E8963F-43D5-469A-B1AA-FEF6F0BCFBCA&scoreType=9&userId=131D5455-C4DD-410E-8EDE-8BD1A4E371E4&yearMonthInfo=2015-01-06";
    private RecyclerView productInformationRecyclerView;
    private ProductInformationAdapter productInformationAdapter;
    private List<List<String>> productInformationList = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_product_information);
        productInformationRecyclerView = (RecyclerView) findViewById(R.id.productInformationRecyclerView);
        productInformationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productInformationAdapter = new ProductInformationAdapter();
        productInformationRecyclerView.setAdapter(productInformationAdapter);

    }

    @Override
    protected void loadData() {
        present = new ProductInformationPresent();
        present.bind(this);
        present.getProductInformation(path);
    }

    public void setProductInformation(ProductInformationModle productInformationModle) {
        productInformationList.addAll(productInformationModle.getScoreList());
        productInformationAdapter.notifyDataSetChanged();
    }

    class ProductInformationAdapter extends RecyclerView.Adapter {
        public static final int ITEM_HEADER = 0;
        public static final int ITEM_RECORD = 1;
        public static final int ITEM_NO_RECORD = 2;
        public static final int ITEM_FOOTER = 3;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case ITEM_HEADER:
                    HeadViewHolder headViewHolder = new HeadViewHolder(LayoutInflater.from(ProductInformationActivity.this).inflate(R.layout.item_head_view_product_information, parent, false));
                    return headViewHolder;
                case ITEM_RECORD:
                    RecordViewHolder recordViewHolder = new RecordViewHolder(LayoutInflater.from(ProductInformationActivity.this).inflate(R.layout.item_record_product_information, parent, false));
                    return recordViewHolder;
                case ITEM_NO_RECORD:
                  /*  NoRecordViewHolder noRecordViewHolder = new NoRecordViewHolder(LayoutInflater.from(ProductInformationActivity.this).inflate(R.layout.item_norecord_product_information, parent, false));*/
                    View noRecordViewHolder=LayoutInflater.from(ProductInformationActivity.this).inflate(R.layout.item_norecord_product_information,parent,false);
                    noRecordViewHolder.getLayoutParams().height=parent.getHeight()-parent.getChildAt(0).getHeight();
                    return new NoRecordViewHolder(noRecordViewHolder);
                case ITEM_FOOTER:
                    FooterViewHolder footerViewHolder = new FooterViewHolder(LayoutInflater.from(ProductInformationActivity.this).inflate(R.layout.item_footer_product_information, parent, false));
                    return footerViewHolder;
                default:
                    return null;
            }
        }

        ;

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeadViewHolder) {
                HeadViewHolder hvh = (HeadViewHolder) holder;
                hvh.priceButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showSuccessToast("点击成功", ProductInformationActivity.this);
                    }
                });
            }

            if (holder instanceof RecordViewHolder) {
                RecordViewHolder rvh = (RecordViewHolder) holder;
                rvh.productName.setText(productInformationList.get(position-1).get(1).toString());
                rvh.priceTextView.setText(productInformationList.get(position-1).get(2).toString());
                Picasso.with(ProductInformationActivity.this).load(ImagePath.productImagePath[position]).error(R.mipmap.ic_launcher).into(rvh.productInformationCircleImageView);
                rvh.purchaseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showSuccessToast("添加到购物车成功", ProductInformationActivity.this);
                    }
                });

            }
        /*    if (holder instanceof NoRecordViewHolder) {
                NoRecordViewHolder nrvh = (NoRecordViewHolder) holder;
                if(productInformationList.size()>0){
                    nrvh.emptyImageView.setVisibility(View.GONE);
                    nrvh.emptyTextView.setVisibility(View.GONE);
                }else{
                    nrvh.emptyTextView.setText("暂无商品信息记录");
                }

            }*/
            if(holder instanceof FooterViewHolder){
                FooterViewHolder fvh=(FooterViewHolder)holder;
                fvh.loadingTextView.setText("加载中。。。。。");
            }

        }

        @Override
        public int getItemCount() {
            return productInformationList.size() == 0 ? 2 : productInformationList.size() + 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return ITEM_HEADER;
            } else if (productInformationList.size() == 0 && position == 1) {
                return ITEM_NO_RECORD;
            } else if (position == productInformationList.size() + 1) {
                return ITEM_FOOTER;
            } else {
                return ITEM_RECORD;
            }
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        private TextView price;
        private ImageView priceImageView;
        private Button priceButton;

        public HeadViewHolder(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.price);
            priceImageView = (ImageView) itemView.findViewById(R.id.priceImageView);
            priceButton = (Button) itemView.findViewById(R.id.priceButton);
        }
    }

    class RecordViewHolder extends RecyclerView.ViewHolder {
        private Button purchaseButton;
        private TextView priceTextView;
        private TextView productName;
        private CircleImageView productInformationCircleImageView;

        public RecordViewHolder(View itemView) {
            super(itemView);
            purchaseButton = (Button) itemView.findViewById(R.id.purchaseButton);
            priceTextView = (TextView) itemView.findViewById(R.id.priceTextView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productInformationCircleImageView = (CircleImageView) itemView.findViewById(R.id.productInformationCircleImageView);
        }
    }

    class NoRecordViewHolder extends RecyclerView.ViewHolder {
      /*  private ImageView emptyImageView;
        private TextView emptyTextView;*/

        public NoRecordViewHolder(View itemView) {
            super(itemView);
          /*  emptyImageView = (ImageView) itemView.findViewById(R.id.emptyImageView);
            emptyTextView = (TextView) itemView.findViewById(R.id.emptyTextView);*/
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView loadingTextView;
        public FooterViewHolder(View itemView) {
            super(itemView);
            loadingTextView = (TextView) itemView.findViewById(R.id.loadingTextView);
        }
    }
}
