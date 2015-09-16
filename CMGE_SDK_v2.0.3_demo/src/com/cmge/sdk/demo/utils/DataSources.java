package com.cmge.sdk.demo.utils;

import java.util.ArrayList;
import java.util.List;

import android.util.SparseArray;


/**
 * <li>File Name: DataSources.java</li>
 * <li>File Description: </li>
 * <li>Company: </li>
 * <li>Create Time: 2015-8-19 上午10:55:38</li>
 * @version 1.0.0
 * @author  HooRang
 */
public class DataSources {

	
	public static SparseArray<String> sParentObj = new SparseArray<String>();
	public static List<List<String>> sChildrenObj = new ArrayList<List<String>>();
	
	
	public static final int FUNCTION_CODE_BUSSINES = 0;
	public static final int FUNCTION_CODE_DATAS    = 1;
	public static final int FUNCTION_CODE_SHARE    = 2;
	public static final int FUNCTION_CODE_PUSH     = 3;
	
	static{
		
		sParentObj.put(FUNCTION_CODE_BUSSINES	,"业务功能");
		sParentObj.put(FUNCTION_CODE_DATAS		,"数据采集");
		sParentObj.put(FUNCTION_CODE_SHARE		,"分　　享");
		sParentObj.put(FUNCTION_CODE_PUSH		,"推　　送");
		
		
		List<String> sBussinesListView = new ArrayList<String>();
		sBussinesListView.add("登　　录");
		sBussinesListView.add("支　　付");
		sBussinesListView.add("注销账户");
		sBussinesListView.add("用户中心");
		
		
		List<String> sDatasListView = new ArrayList<String>();
		sDatasListView.add("进入游戏");
		sDatasListView.add("统计支付");
		sDatasListView.add("创建角色");
		sDatasListView.add("角色升级");
		
		List<String> sPushListView = new ArrayList<String>();
		sPushListView.add("开始推送");
		sPushListView.add("设置标签");
		
		List<String> sShareListView = new ArrayList<String>();
		sShareListView.add("打开分享");
		
		
		sChildrenObj.add(sBussinesListView);
		sChildrenObj.add(sDatasListView);
		sChildrenObj.add(sShareListView);
		sChildrenObj.add(sPushListView);
	}
 	
}


