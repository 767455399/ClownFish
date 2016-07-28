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
import com.example.administrator.clownfish.modle.SortModel;
import com.example.administrator.clownfish.modle.SupportCityListModel;
import com.example.administrator.clownfish.tool.CharacterParser;
import com.example.administrator.clownfish.tool.Comparator;
import com.example.administrator.clownfish.view.ListSideBar;
import com.example.administrator.clownfish.view.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityListActivity extends BaseActivity {
    private RecyclerView cityListRecyclerView;
    private CityListAdapter cityListAdapter;
    private TextView popupTextView;
    private ListSideBar sidrBar;
    private EditText cityEditText;
    private boolean move = false;
   /*private List<SupportCityListModel.Info> infos = new ArrayList<>();
    List<SupportCityListModel.Info> allCityList = new ArrayList<>();*/
    private List<SortModel> sourceDateList;
    private List<SortModel> allCityList;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private Comparator comparator;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_city_list);
        cityEditText = (EditText) findViewById(R.id.cityEditText);
        cityListRecyclerView = (RecyclerView) findViewById(R.id.cityListRecyclerView);
        cityListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cityListAdapter = new CityListAdapter();
        cityListRecyclerView.setAdapter(cityListAdapter);
        popupTextView = (TextView) findViewById(R.id.popupTextView);
        sidrBar = (ListSideBar) findViewById(R.id.sidrbar);
        sidrBar.setTextView(popupTextView);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        comparator = new Comparator();
        sourceDateList = filledData(getResources().getStringArray(R.array.date));
        allCityList=filledData(getResources().getStringArray(R.array.date));
    }

    @Override
    protected void loadData() {
// 设置右侧触摸监听
        sidrBar.setOnTouchingLetterChangedListener(new ListSideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                final int position = cityListAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    final LinearLayoutManager m = (LinearLayoutManager) cityListRecyclerView.getLayoutManager();
                    int firstPosition = m.findFirstVisibleItemPosition();
                    int lastPosition = m.findLastVisibleItemPosition();

                    if (position <= firstPosition) {
                        cityListRecyclerView.scrollToPosition(position);
                    } else if (position <= lastPosition) {
                        cityListRecyclerView.scrollBy(0, cityListRecyclerView.getChildAt(position - firstPosition).getTop());
                    } else {
                        cityListRecyclerView.scrollToPosition(position);
                        move = true;
                    }

                    cityListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if (move) {
                                move = false;
                                int offset = position - m.findFirstVisibleItemPosition();
                                if (offset >= 0 && offset <= recyclerView.getChildCount()) {
                                    cityListRecyclerView.scrollBy(0, cityListRecyclerView.getChildAt(offset).getTop());
                                }
                            }
                        }
                    });
                }
            }
        });


        // 根据输入框输入值的改变来过滤搜索
        cityEditText.addTextChangedListener(new TextWatcher() {
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

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();
        filterDateList.clear();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = filledData(getResources().getStringArray(R.array.date));
        } else {
            for (SortModel sortModel : allCityList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, comparator);
        cityListAdapter.updateNewListView(filterDateList);
        cityListAdapter.notifyDataSetChanged();

    }


    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    class CityListAdapter extends RecyclerView.Adapter<ViewHolder> implements SectionIndexer {

        public void updateNewListView(List<SortModel> searchResultsList) {
         /*   if(searchResultsList.size()>0){*/
                sourceDateList.clear();
                sourceDateList = searchResultsList;
                notifyDataSetChanged();
          /*  }*/
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
                holder.titleTextView.setText(sourceDateList.get(position).getSortLetters());
                holder.topLineImageView.setVisibility(View.VISIBLE);
            } else {
                holder.titleTextView.setVisibility(View.GONE);
                holder.topLineImageView.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(sourceDateList.get(position).getName())) {
                holder.cityNameTextView.setText(sourceDateList.get(position).getName());
            }
           /* holder.cityNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("cityName", sourceDateList.get(position).getName());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });*/
        }

        @Override
        public int getItemCount() {
            return sourceDateList.size();
        }

        @Override
        public Object[] getSections() {
            return new Object[0];
        }

        /**
         * 根据RecyclerView的当前位置获取分类的首字母的Char ascii值
         */
        @Override
        public int getPositionForSection(int section) {
            for (int i = 0; i < sourceDateList.size(); i++) {
                String sortStr = sourceDateList.get(i).getSortLetters();
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == section) {
                    return i;
                }
            }

            return -1;
        }

        /**
         * 根据RecyclerView的当前位置获取分类的首字母的Char ascii值
         */
        @Override
        public int getSectionForPosition(int position) {
            return sourceDateList.get(position).getSortLetters().charAt(0);
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
    }
}
