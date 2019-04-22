package com.foodmanager.ui.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.foodmanager.R;
import com.foodmanager.bean.Template;
import com.foodmanager.ui.BaseActivity;
import com.foodmanager.utils.StaticClass;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class AnswerActivity extends BaseActivity {
    private ListView mCreateList;
    private QMUITopBar mCreateTittle;

    @Override
    protected void initLayout() {

        mCreateList = (ListView) findViewById(R.id.create_list);
        mCreateTittle = (QMUITopBar) findViewById(R.id.create_tittle);
        mCreateTittle.setTitle("选择要回答的模板");
        //返回按钮
        QMUIAlphaImageButton qmuiAlphaImageButton = mCreateTittle.addLeftBackImageButton();
        qmuiAlphaImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.createactivity;
    }

    @Override
    protected void init() {
        iniData();
    }

    private void iniData() {
        //查询云数据库数据
        equal();
    }

    private void equal() {
        BmobQuery<Template> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("fabu", true);
        categoryBmobQuery.findObjects(new FindListener<Template>() {

            @Override
            public void done(List<Template> list, BmobException e) {
                if (e == null) {
                    MyAdapter1 myAdapter = new MyAdapter1(list);
                    mCreateList.setAdapter(myAdapter);
                    mCreateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(AnswerActivity.this,TwoActivity.class);
                            Template template = list.get(position);
                            StaticClass.template=template;
                            startActivity(intent);
                        }
                    });
                } else {

                }
            }
        });
    }

    @Override
    protected int getContextViewId() {
        return 0;
    }

    private class MyAdapter1 extends BaseAdapter {
        List<Template> list;

        public MyAdapter1(List<Template> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(AnswerActivity.this, R.layout.create_list_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.creeate_item_text.setText(list.get(position).getName());
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView creeate_item_text;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.creeate_item_text = (TextView) rootView.findViewById(R.id.creeate_item_text);
            }

        }
    }
}
