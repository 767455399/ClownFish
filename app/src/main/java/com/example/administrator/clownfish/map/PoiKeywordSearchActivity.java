package com.example.administrator.clownfish.map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.administrator.clownfish.Activity.BaseActivity;
import com.example.administrator.clownfish.Activity.LoginActivity;
import com.example.administrator.clownfish.R;
import com.example.administrator.clownfish.tool.AMapUtil;
import com.example.administrator.clownfish.tool.ToastUtil;
import com.example.administrator.clownfish.tool.permanent;

import java.util.ArrayList;
import java.util.List;

public class PoiKeywordSearchActivity extends AppCompatActivity implements
        AMap.OnMarkerClickListener, AMap.InfoWindowAdapter, TextWatcher,
        PoiSearch.OnPoiSearchListener, View.OnClickListener, Inputtips.InputtipsListener {
    private AutoCompleteTextView keywordEditText;
    private EditText cityEditText;
    private Button searchButton;
    private Button nextButton;
    private String keyWord = "";// 要输入的poi搜索关键字
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private PoiResult poiResult; // poi返回的结果
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_keyword_search);
        init();
    }

    private void init() {
        if (aMap == null) {
            aMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
        }
        aMap.setOnMarkerClickListener(this);// 添加点击marker监听事件
        aMap.setInfoWindowAdapter(this);// 添加显示infowindow监听事件
        keywordEditText = (AutoCompleteTextView) findViewById(R.id.keywordEditText);
        // 添加文本输入框监听事件
        keywordEditText.addTextChangedListener(this);
        cityEditText = (EditText) findViewById(R.id.cityEditText);
        searchButton = (Button) findViewById(R.id.searchButton);
        nextButton=(Button)findViewById(R.id.nextButton);
        searchButton.setOnClickListener(this);
        nextButton=(Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);

    }

    public void search() {
        keyWord = AMapUtil.checkEditText(keywordEditText);
        if ("".equals(keyWord)) {
            ToastUtil.showWarningToast("请输入要查询的内容", PoiKeywordSearchActivity.this);
        } else {
            doSearchQuery();
        }
    }

    private void doSearchQuery() {
        if (!TextUtils.isEmpty(cityEditText.getText().toString())) {
            query = new PoiSearch.Query(keyWord, cityEditText.getText().toString());
        } else {
            query = new PoiSearch.Query(keyWord, permanent.city);
        }
        query.setPageSize(1);
        query.setPageNum(0);
        poiSearch=new PoiSearch(this,query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();


    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.poikeywordsearch_uri,
                null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(marker.getTitle());
        TextView geoPointTextView=(TextView)view.findViewById(R.id.geoPointTextView);
        TextView positionTextView=(TextView)view.findViewById(R.id.positionTextView);
        TextView rotateAngleTextView=(TextView)view.findViewById(R.id.rotateAngleTextView);
        TextView snippet = (TextView) view.findViewById(R.id.snippet);
        snippet.setText(marker.getSnippet());
       /* geoPointTextView.setText(marker.getGeoPoint().toString());*/
        positionTextView.setText(marker.getPosition().toString());
      /*  rotateAngleTextView.setText(marker.getRotateAngle()+"");*/
        ImageButton button = (ImageButton) view
                .findViewById(R.id.start_amap_app);
        // 调起高德地图app
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  startAMapNavi(marker);*/
            }
        });
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onGetInputtips(List<Tip> list, int rCode) {
        if (rCode == 1000) {// 正确返回
            List<String> listString = new ArrayList<String>();
            for (int i = 0; i < list.size(); i++) {
                listString.add(list.get(i).getName());
            }
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(
                    getApplicationContext(),
                    R.layout.route_inputs, listString);
            keywordEditText.setAdapter(aAdapter);
            aAdapter.notifyDataSetChanged();
        } else {
      /*      ToastUtil.showerror(this, rCode);*/
            ToastUtil.showWarningToast("获取数据失败",this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchButton:
                search();
                break;
            case R.id.nextButton:
                Intent intent=new Intent();
                intent.setClass(PoiKeywordSearchActivity.this, LoginActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息

                    if (poiItems != null && poiItems.size() > 0) {
                      /*  aMap.clear();// 清理之前的图标*/
                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
                        poiOverlay.removeFromMap();
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();
                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                        showSuggestCity(suggestionCities);
                    } else {
                        ToastUtil.showWarningToast(getString(R.string.no_result),this);
                    }
                }
            } else {
                ToastUtil.showWarningToast(getString(R.string.no_result),this);
            }
        } else {
            ToastUtil.showWarningToast(rCode+" ",this);
        }
    }

    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtil.showWarningToast(infomation,this);

    }


    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String newText = s.toString().trim();
        if (!AMapUtil.IsEmptyOrNullString(newText)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, cityEditText.getText().toString());
            Inputtips inputTips = new Inputtips(PoiKeywordSearchActivity.this, inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
