package com.foodmanager.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.foodmanager.R;
import com.foodmanager.bean.Template;
import com.foodmanager.bean.TextBean;
import com.foodmanager.ui.BaseActivity;
import com.foodmanager.utils.StaticClass;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends BaseActivity {
    private ListView mCreateList;
    private QMUITopBar mCreateTittle;
    public List<Activity> activityList;

    //布局控件声明
    @Override
    protected void initLayout() {
        mCreateList = (ListView) findViewById(R.id.create_list);
        mCreateTittle = (QMUITopBar) findViewById(R.id.create_tittle);
        mCreateTittle.setTitle("选择模板");
        //返回按钮
        QMUIAlphaImageButton qmuiAlphaImageButton = mCreateTittle.addLeftBackImageButton();
        qmuiAlphaImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //xml布局文件
    @Override
    protected int getLayout() {
        return R.layout.createactivity;
    }

    //初始化数据或方法
    @Override
    protected void init() {
        iniData();
    }

    @Override
    protected int getContextViewId() {
        return R.id.qmuidemo;
    }

    //数据初始化
    private void iniData() {

        List<Template> templateList=new ArrayList<>();
        Template template1=new Template();
        template1.setName("大学生兼职意愿和兼职类型倾向调查");
        List<TextBean> beanList1=new ArrayList<>();
        TextBean textBean11=new TextBean();
        textBean11.setName("：你更希望选择兼职的类型？");
        textBean11.setTestselect(" 家教，派传单，餐厅服务员，超市和便利店销售员");
        beanList1.add(textBean11);
        TextBean textBean12=new TextBean();
        textBean12.setName("：如果你的兼职与上课冲突，你会选择哪一个？");
        textBean12.setTestselect("上课，兼职，看情况，其他");
        beanList1.add(textBean12);
        TextBean textBean13=new TextBean();
        textBean13.setName("：你一般会选择那些时间段做兼职？");
        textBean13.setTestselect("周六周日，寒暑假，随便哪天都可以，节假日");
        beanList1.add(textBean13);
        TextBean textBean14=new TextBean();
        textBean14.setName("：如果你做兼职，希望每月大约收入是多少？");
        textBean14.setTestselect("500以下，500-1000，1000-1500，1500以上");
        beanList1.add(textBean14);
        TextBean textBean15=new TextBean();
        textBean15.setName("：你兼职收入主要用途是？");
        textBean15.setTestselect("作为生活费，交往朋友，寄给父母，旅游");
        beanList1.add(textBean15);
        template1.setTextBeanList(beanList1);
        templateList.add(template1);


        Template template2=new Template();
        template2.setName("气质类型测试量表");
        List<TextBean> beanList2=new ArrayList<>();
        TextBean textBean21=new TextBean();
        textBean21.setName("：做事力求稳妥，不做无把握的事");
        textBean21.setTestselect("很符合，较符合，一般，不符合");
        beanList2.add(textBean21);
        TextBean textBean22=new TextBean();
        textBean22.setName("：遇到可气的事就怒不可竭，想把心里的话全说出来才痛快");
        textBean22.setTestselect("很符合，较符合，一般，不符合");
        beanList2.add(textBean22);
        TextBean textBean23=new TextBean();
        textBean23.setName("：宁肯一个人干事，不愿很多人在一起");
        textBean23.setTestselect("很符合，较符合，一般，不符合");
        beanList2.add(textBean23);
        TextBean textBean24=new TextBean();
        textBean24.setName("：到一个新环境里很快能适应");
        textBean24.setTestselect("很符合，较符合，一般，不符合");
        beanList2.add(textBean24);
        TextBean textBean25=new TextBean();
        textBean25.setName("：和人争吵时，总是先发制人，喜欢挑衅");
        textBean25.setTestselect("很符合，较符合，一般，不符合");
        beanList2.add(textBean25);
        template2.setTextBeanList(beanList2);
        templateList.add(template2);


        Template template3=new Template();
        template3.setName("大学生所学专业满意度调查");
        List<TextBean> beanList3=new ArrayList<>();
        TextBean textBean31=new TextBean();
        textBean31.setName("：您选择此专业时考虑的是？");
        textBean31.setTestselect(" 父母的意愿和他人的建议，兴趣爱好，就业率高，热门专业");
        beanList3.add(textBean31);
        TextBean textBean32=new TextBean();
        textBean32.setName("：如果您对所学专业不满意，您会通过什么方式改变？");
        textBean32.setTestselect("考研，出国留学，学校转专业考试，将就着学");
        beanList3.add(textBean32);
        TextBean textBean33=new TextBean();
        textBean33.setName("：毕业后您会不会从事您所学专业的工作？");
        textBean33.setTestselect("会，不会，持保留态度，不知道");
        beanList3.add(textBean33);
        TextBean textBean34=new TextBean();
        textBean34.setName("：影响您对所学专业的学习态度的是？");
        textBean34.setTestselect("专业学习难度，专业在学校的受重视程度，专业就业率，爱好");
        beanList3.add(textBean34);
        TextBean textBean35=new TextBean();
        textBean35.setName("：您觉得您学校对自己的专业的政策如何？");
        textBean35.setTestselect("非常关心，比较关心，一般，比较不关心");
        beanList3.add(textBean35);
        template3.setTextBeanList(beanList3);
        templateList.add(template3);

        Template template4=new Template();
        template4.setName("生活品质调查问卷");
        List<TextBean> beanList4=new ArrayList<>();
        TextBean textBean41=new TextBean();
        textBean41.setName("：您目前的职业");
        textBean41.setTestselect(" 在校学生，政府/机关干部/公务员，企业管理者（包括基层及中高层管理者），普通职员（办公室/写字楼工作人员）");
        beanList4.add(textBean41);
        TextBean textBean42=new TextBean();
        textBean42.setName("：您对自己的健康状况满意吗？");
        textBean42.setTestselect(" 非常不满意，不满意，一般，满意");
        beanList4.add(textBean42);
        TextBean textBean43=new TextBean();
        textBean43.setName("：您需要依靠医院或仪器的帮助进行日常生活吗？");
        textBean43.setTestselect("根本不需要，比较不需要，一般，比较需要");
        beanList4.add(textBean43);
        TextBean textBean44=new TextBean();
        textBean44.setName("：您觉得生活有乐趣吗？");
        textBean44.setTestselect(" 根本没乐趣，很少有乐趣，一般，比较有乐趣");
        beanList4.add(textBean44);
        TextBean textBean45=new TextBean();
        textBean45.setName("：日常生活中您感觉安全吗？");
        textBean45.setTestselect("根本不安全，很少安全，一般，比较安全");
        beanList4.add(textBean45);
        template4.setTextBeanList(beanList4);
        templateList.add(template4);

        Template template5=new Template();
        template5.setName("网购用户的自我保护意识调查");
        List<TextBean> beanList5=new ArrayList<>();
        TextBean textBean51=new TextBean();
        textBean51.setName("：你在网购时出现过哪些问题？");
        textBean51.setTestselect("没问题，产品质量问题，退换货问题，实际产品与图文不符");
        beanList5.add(textBean51);
        TextBean textBean52=new TextBean();
        textBean52.setName("：如果出现以上的问题时，你会选择?");
        textBean52.setTestselect("忍气吞声，直接给差评，与卖家协商，要求退款或换货，向淘宝客服投诉解决，其他");
        beanList5.add(textBean52);
        TextBean textBean53=new TextBean();
        textBean53.setName("：鉴收包裹时，您有先验货后鉴收的习惯吗？");
        textBean53.setTestselect("一直都有，寒暑假，随便哪天都可以，节假日");
        beanList5.add(textBean53);
        TextBean textBean54=new TextBean();
        textBean54.setName("：如果你做兼职，希望每月大约收入是多少？");
        textBean54.setTestselect("500以下，500-1000，1000-1500，1500以上");
        beanList5.add(textBean54);
        TextBean textBean55=new TextBean();
        textBean55.setName("：你兼职收入主要用途是？");
        textBean55.setTestselect("作为生活费，交往朋友，寄给父母，旅游");
        beanList5.add(textBean55);
        template5.setTextBeanList(beanList5);
        templateList.add(template5);

        Template template6=new Template();
        template6.setName("大学生兼职意愿和兼职类型倾向调查");
        List<TextBean> beanList6=new ArrayList<>();
        TextBean textBean61=new TextBean();
        textBean61.setName("：你更希望选择兼职的类型？");
        textBean61.setTestselect("根本不安全，很少安全，一般，比较安全");
        beanList6.add(textBean61);
        TextBean textBean62=new TextBean();
        textBean62.setName("：如果你的兼职与上课冲突，你会选择哪一个？");
        textBean62.setTestselect("上课，兼职，看情况，其他");
        beanList6.add(textBean62);
        TextBean textBean63=new TextBean();
        textBean63.setName("：你一般会选择那些时间段做兼职？");
        textBean63.setTestselect("周六周日，寒暑假，随便哪天都可以，节假日");
        beanList6.add(textBean63);
        TextBean textBean64=new TextBean();
        textBean64.setName("：如果你做兼职，希望每月大约收入是多少？");
        textBean64.setTestselect("500以下，500-1000，1000-1500，1500以上");
        beanList6.add(textBean64);
        TextBean textBean65=new TextBean();
        textBean65.setName("：你兼职收入主要用途是？");
        textBean65.setTestselect("作为生活费，交往朋友，寄给父母，旅游");
        beanList6.add(textBean65);
        template6.setTextBeanList(beanList6);
        templateList.add(template6);
        MyAdapter myAdapter = new MyAdapter(templateList);
        mCreateList.setAdapter(myAdapter);
        mCreateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CreateActivity.this,OneActivity.class);
                Template template = templateList.get(position);
                StaticClass.template=template;
                startActivity(intent);
            }
        });
    }

    private class MyAdapter extends BaseAdapter {
        List<Template> templateList;

        public MyAdapter(List<Template> templateList) {
            this.templateList = templateList;
        }

        @Override
        public int getCount() {
            return templateList.size();
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
                convertView = View.inflate(CreateActivity.this, R.layout.create_list_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mCreeateItemText.setText(templateList.get(position).getName());
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView mCreeateItemText;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.mCreeateItemText = (TextView) rootView.findViewById(R.id.creeate_item_text);
            }

        }
    }
}
