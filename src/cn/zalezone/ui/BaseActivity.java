package cn.zalezone.ui;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity{
	
	//初始化UI组件
	public abstract void  initUI();
	
	//初始化数据
	public abstract void initData();

	
}
