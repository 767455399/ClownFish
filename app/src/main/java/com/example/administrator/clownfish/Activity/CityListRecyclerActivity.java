package com.example.administrator.clownfish.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.modle.SupportCityListModel;
import com.example.administrator.clownfish.tool.CharacterParser;
import com.example.administrator.clownfish.view.ListSideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/*recyclerview实现关键字搜索，拼音索引功能*/

public class CityListRecyclerActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }
   /* private CityListPresenter presenter;
    private List<SupportCityListModel.Info> infos = new ArrayList<>();
    List<SupportCityListModel.Info> allCityList = new ArrayList<>();
    private RecyclerView sortRecyclerView;
    private ListSideBar sideBar;
    private TextView popupTextView;
    private SortAdapter adapter;
    private EditText mClearEditText;
    private View noMoneyRecord;
    private TextView noInformationTextView;
    private boolean move = false;

    *//**
     * 汉字转换成拼音的类
     *//*
    private CharacterParser characterParser;

    *//**
     * 根据拼音来排列RecyclerView里面的数据类
     *//*
    private PinyinComparator pinyinComparator;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_city_list);
        noMoneyRecord = findViewById(R.id.nullMoneyRecord);
        if (noMoneyRecord != null) {
            noMoneyRecord.setVisibility(View.GONE);
        }
        noInformationTextView = (TextView) findViewById(R.id.noInformationTextView);
        if (noInformationTextView != null) {
            noInformationTextView.setText("暂无城市列表信息");
        }
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sortRecyclerView = (RecyclerView) findViewById(R.id.cityListRecyclerView);
        sideBar = (ListSideBar) findViewById(R.id.sidrbar);
        popupTextView = (TextView) findViewById(R.id.popupTextView);
        sideBar.setTextView(popupTextView);
        sortRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SortAdapter();
        sortRecyclerView.setAdapter(adapter);
        // 根据a-z进行排序源数据
        Collections.sort(infos, pinyinComparator);
        mClearEditText = (EditText) findViewById(R.id.filter_edit);

    }


    @Override
    protected void setListeners() {
        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new ListSideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                final int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    final LinearLayoutManager m = (LinearLayoutManager) sortRecyclerView.getLayoutManager();
                    int firstPosition = m.findFirstVisibleItemPosition();
                    int lastPosition = m.findLastVisibleItemPosition();

                    if (position <= firstPosition) {
                        sortRecyclerView.scrollToPosition(position);
                    } else if (position <= lastPosition) {
                        sortRecyclerView.scrollBy(0, sortRecyclerView.getChildAt(position - firstPosition).getTop());
                    } else {
                        sortRecyclerView.scrollToPosition(position);
                        move = true;
                    }

                    sortRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if (move) {
                                move = false;
                                int offset = position - m.findFirstVisibleItemPosition();
                                if (offset >= 0 && offset <= recyclerView.getChildCount()) {
                                    sortRecyclerView.scrollBy(0, sortRecyclerView.getChildAt(offset).getTop());
                                }
                            }
                        }
                    });
                }
            }
        });

        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    protected void bind() {
        presenter = new CityListPresenter();
        presenter.bind(this);
        presenter.getSDMSupportCityList();
    }

    public void setSDMSupportCityList(List<SupportCityListModel.Info> data) {
        if (data.size() > 0) {
            infos.clear();
            infos.addAll(data);
            allCityList = infos;
            adapter.notifyDataSetChanged();
        } else {
            noMoneyRecord.setVisibility(View.VISIBLE);
        }

    }

    public void DataLoadingError() {
        noMoneyRecord.setVisibility(View.VISIBLE);
        noInformationTextView.setText("获取城市列表失败");
    }


    @Override
    protected void pageTrackStart() {
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }

    @Override
    protected void pageTrackEnd() {
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }


    *//**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     *//*
    private void filterData(String filterStr) {
        List<SupportCityListModel.Info> filterDateList = new ArrayList<SupportCityListModel.Info>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = allCityList;
        } else {
            filterDateList.clear();
            infos = allCityList;
            for (SupportCityListModel.Info supportCityListModel : infos) {
                String name = supportCityListModel.getName();
                if (name.indexOf(filterStr) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr)) {
                    filterDateList.add(supportCityListModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {




    }

    @Override
    protected void loadData() {

    }

    class SortAdapter extends RecyclerView.Adapter<ViewHolder> implements SectionIndexer {

        public void updateListView(List<SupportCityListModel.Info> searchResultsList) {
            infos = searchResultsList;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CityListActivity.this).inflate(R.layout.item_city_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            // 根据position获取分类的首字母的Char ascii值
            int section = getSectionForPosition(position);
            // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if (position == getPositionForSection(section)) {
                holder.titleTextView.setVisibility(View.VISIBLE);
                holder.titleTextView.setText(infos.get(position).getLetter());
                holder.topLineImageView.setVisibility(View.VISIBLE);
            } else {
                holder.titleTextView.setVisibility(View.GONE);
                holder.topLineImageView.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(infos.get(position).getName())) {
                holder.cityNameTextView.setText(infos.get(position).getName());
            }
            holder.cityNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("cityName", infos.get(position).getName());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });
        }

        @Override
        public int getItemCount() {
            return infos.size();
        }

        @Override
        public Object[] getSections() {
            return new Object[0];
        }

        *//**
         * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
         *//*
        @Override
        public int getPositionForSection(int section) {
            for (int i = 0; i < infos.size(); i++) {
                String sortStr = infos.get(i).getLetter();
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }

            return -1;
        }

        *//**
         * 根据RecyclerView的当前位置获取分类的首字母的Char ascii值
         *//*
        @Override
        public int getSectionForPosition(int position) {
            return infos.get(position).getLetter().charAt(0);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView cityNameTextView;
        ImageView topLineImageView;
        ImageView middlelineImageView;
        ImageView splitLineImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            cityNameTextView = (TextView) itemView.findViewById(R.id.cityNameTextView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            topLineImageView = (ImageView) itemView.findViewById(R.id.topLineImageView);
            middlelineImageView = (ImageView) itemView.findViewById(R.id.middlelineImageView);
            splitLineImageView = (ImageView) itemView.findViewById(R.id.splitLineImageView);
        }
    }*/
}
