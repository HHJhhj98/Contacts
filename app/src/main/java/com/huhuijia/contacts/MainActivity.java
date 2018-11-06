package com.huhuijia.contacts;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.huhuijia.contacts.Bean.ContactsBean;
import com.huhuijia.contacts.Sql.SQLiteOpenHelpers;
import com.huhuijia.contacts.Sql.SQLiteOperator;
import com.huhuijia.contacts.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mSavenameET;
    private EditText mSavephoneET;
    private Button mSaveTV;
    private Button mDeleteTV;
    private Button mUpdateTV;
    private String name = "";
    private String phone = "";
    private SQLiteOperator sqLiteOperator;
    private boolean b;
    private ArrayList<String> mList;
    private ListAdapter myAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        handler.sendEmptyMessageDelayed(1001, 0);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1001:
                    mList = new ArrayList<>();
                    name = mSavenameET.getText().toString();
                    if (name == null || name.equals("")) {
                        List<ContactsBean> contactsBeans = new ArrayList<ContactsBean>();
                        try {
                            contactsBeans = sqLiteOperator.queryMany();
                            for (int i = 0; i < contactsBeans.size(); i++) {
                                switch (contactsBeans.get(i).getName().length()){
                                    case 1:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "                    Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                    case 2:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "                Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                    case 3:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "            Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                    case 4:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "        Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                    case 5:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "    Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                }

                            }
                            myAdapter = new ListAdapter(MainActivity.this, mList);
                            listView.setAdapter(myAdapter);

                        } catch (Exception E) {

                        }
                    } else {
                        try {
                            List<ContactsBean> contactsBeans = new ArrayList<ContactsBean>();
                            contactsBeans = sqLiteOperator.queryAll(name);
                            Log.e("name", name);
                            for (int i = 0; i < contactsBeans.size(); i++) {
                                switch (contactsBeans.get(i).getName().length()){
                                    case 1:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "                    Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                    case 2:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "                Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                    case 3:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "            Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                    case 4:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "        Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                    case 5:
                                        mList.add("姓名：" + contactsBeans.get(i).getName() + "    Tel：" + contactsBeans.get(i).getPhone());
                                        break;
                                }
                            }
                            myAdapter = new ListAdapter(MainActivity.this, mList);
                            listView.setAdapter(myAdapter);

                        } catch (Exception e) {
                            Log.e("e", e + "");
                        }
                    }
                    break;
            }
            //mList.clear();
            handler.sendEmptyMessageDelayed(1001, 500);
        }
    };

    private void initView() {
        mSavenameET = (EditText) findViewById(R.id.savenameET);
        mSavephoneET = (EditText) findViewById(R.id.savephoneET);
        mSaveTV = (Button) findViewById(R.id.saveTV);
        mDeleteTV = (Button) findViewById(R.id.deleteTV);
        mUpdateTV = (Button) findViewById(R.id.updateTV);
        listView = (ListView) findViewById(R.id.mlistView);
        mList = new ArrayList<>();
        sqLiteOperator = new SQLiteOperator(MainActivity.this);
        mSaveTV.setOnClickListener(this);
        mDeleteTV.setOnClickListener(this);
        mUpdateTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveTV:
                name = mSavenameET.getText().toString().trim();
                phone = mSavephoneET.getText().toString().trim();
                try {
                    ContactsBean contactsBean = new ContactsBean();
                    contactsBean.setName(name);
                    contactsBean.setPhone(phone);
                    b = sqLiteOperator.insert(contactsBean);
                } catch (Exception e) {
                    Log.e("djf", e + "");
                }
                if (b) {
                    mSavephoneET.setText("");
                    mSavenameET.setText("");
                    Toast.makeText(MainActivity.this, "增加联系人成功！", Toast.LENGTH_LONG).show();
                } else {
                    mSavephoneET.setText("");
                    mSavenameET.setText("");
                    Toast.makeText(MainActivity.this, "该联系人已存在！", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.deleteTV:
                name = mSavenameET.getText().toString().trim();
                b = sqLiteOperator.delete(name);
                if (b) {
                    mSavenameET.setText("");
                    mSavephoneET.setText("");
                    Toast.makeText(MainActivity.this, "删除联系人成功！", Toast.LENGTH_LONG).show();
                } else {
                    mSavenameET.setText("");
                    mSavephoneET.setText("");
                    Toast.makeText(MainActivity.this, "该联系人不存在！", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.updateTV:
                name = mSavenameET.getText().toString().trim();
                phone = mSavephoneET.getText().toString().trim();
                ContactsBean contactsBeans = new ContactsBean();
                contactsBeans.setName(name);
                contactsBeans.setPhone(phone);
                b = sqLiteOperator.update(contactsBeans);
                if (b) {
                    mSavenameET.setText("");
                    mSavephoneET.setText("");
                    Toast.makeText(MainActivity.this, "修改联系人成功！", Toast.LENGTH_LONG).show();
                } else {
                    mSavenameET.setText("");
                    mSavephoneET.setText("");
                    Toast.makeText(MainActivity.this, "该联系人不存在或修改联系人失败！", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
