package com.foodmanager.ui.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.foodmanager.R;
import com.foodmanager.bean.Template;
import com.foodmanager.bean.TextBean;
import com.foodmanager.ui.BaseActivity;
import com.foodmanager.utils.ListViewHeightUtils;
import com.foodmanager.utils.StaticClass;
import com.foodmanager.utils.T;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class TwoActivity extends BaseActivity {
    private QMUITopBar mOnecreateTittle;
    private ListView mOncreatelIst;
    public Template template;

    @Override
    protected void initLayout() {
        mOncreatelIst = (ListView) findViewById(R.id.oncreatel_ist);
        mOnecreateTittle = (QMUITopBar) findViewById(R.id.onecreate_tittle);
        QMUIAlphaImageButton qmuiAlphaImageButton = mOnecreateTittle.addLeftBackImageButton();
        qmuiAlphaImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button textButton = mOnecreateTittle.addRightTextButton("提交", R.id.layout_for_test3);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objectId = template.getObjectId();
                int i1=0;
                for (int i = 0; i < template.getTextBeanList().size(); i++) {

                    if (String.valueOf(template.getTextBeanList().get(i).getTexthao()).equals("")||String.valueOf(template.getTextBeanList().get(i).getTexthao())==null){
                        T.showShort("提交失败，请检查是否填写完全");
                        return;
                    }
                    i1=+ template.getTextBeanList().get(i).getTexthao() * 2;
                }
                Template template1=new Template();
                template1.setFenshu(i1+"");
                template1.setTijiao(true);
                template1.setTextBeanList(template.getTextBeanList());
                template1.update(objectId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                        if (e==null){
                            T.showShort("提交成功");
                            finish();
                        }else {
                            T.showShort("提交失败");
                        }
                    }
                });

            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.oneactivity;
    }

    @Override
    protected void init() {
        iniData();
    }

    private void iniData() {
        //获取数据
        template = StaticClass.template;
        mOnecreateTittle.setTitle(template.getName());
        MyAdapter myAdapter = new MyAdapter(template.getTextBeanList());
        mOncreatelIst.setAdapter(myAdapter);
        new ListViewHeightUtils().setListViewHeightBasedOnChildren(TwoActivity.this,mOncreatelIst );

    }

    @Override
    protected int getContextViewId() {
        return 0;
    }

    private class MyAdapter extends BaseAdapter {
        List<TextBean> textBeanList;

        public MyAdapter(List<TextBean> textBeanList) {
            this.textBeanList = textBeanList;

        }

        @Override
        public int getCount() {
            return textBeanList.size();
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
                convertView = View.inflate(TwoActivity.this, R.layout.oneactre_item, null);
                 viewHolder = new ViewHolder(convertView);
                 convertView.setTag(viewHolder);
            } else {
                  viewHolder = (ViewHolder) convertView.getTag();
            }
            String testselect = textBeanList.get(position).getTestselect();
            String[] split = testselect.split("，");
            viewHolder.mOnecreateItemText1.setText(textBeanList.get(position).getName());
            viewHolder.mOnecreateItemBtn1.setText(split[0]);
            viewHolder.mOnecreateItemBtn2.setText(split[1]);
            viewHolder.mOnecreateItemBtn3.setText(split[2]);
            viewHolder.mOnecreateItemBtn4.setText(split[3]);
           viewHolder.mOnecreateItemRad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup group, int checkedId) {
                  if (checkedId==2131230947){
                      textBeanList.get(position).setTexthao(0);
                  }else if (checkedId==2131230948){
                      textBeanList.get(position).setTexthao(1);
                  }else if (checkedId==2131230949){
                      textBeanList.get(position).setTexthao(2);
                  }else if (checkedId==2131230950){
                      textBeanList.get(position).setTexthao(3);
                  }
               }
           });
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView mOnecreateItemText1;
            public RadioButton mOnecreateItemBtn1;
            public RadioButton mOnecreateItemBtn2;
            public RadioButton mOnecreateItemBtn3;
            public RadioButton mOnecreateItemBtn4;
            public RadioGroup mOnecreateItemRad;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.mOnecreateItemText1 = (TextView) rootView.findViewById(R.id.onecreate_item_text1);
                this.mOnecreateItemBtn1 = (RadioButton) rootView.findViewById(R.id.onecreate_item_btn1);
                this.mOnecreateItemBtn2 = (RadioButton) rootView.findViewById(R.id.onecreate_item_btn2);
                this.mOnecreateItemBtn3 = (RadioButton) rootView.findViewById(R.id.onecreate_item_btn3);
                this.mOnecreateItemBtn4 = (RadioButton) rootView.findViewById(R.id.onecreate_item_btn4);
                this.mOnecreateItemRad = (RadioGroup) rootView.findViewById(R.id.onecreate_item_rad);
            }

        }
    }
}
