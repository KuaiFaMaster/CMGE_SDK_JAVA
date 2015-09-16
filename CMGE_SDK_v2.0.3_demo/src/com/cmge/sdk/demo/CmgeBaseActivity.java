package com.cmge.sdk.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.cmge.sdk.demo.utils.DataSources;
import com.cmge.sdk.demo.utils.DemoListViewAdapter;
import com.cmge.sdkkit.framework.mw.entity.DataTypes;
import com.cmge.sdkkit.framework.mw.entity.ParamsContainer;
import com.cmge.sdkkit.framework.mw.entity.ParamsKey;
import com.cmge.sdkkit.framework.mw.openapi.SDKKitPlatformCore;


/**
 * <li>File Name: CmgeBaseActivity.java</li>
 * <li>File Description: </li>
 * <li>Company: </li>
 * <li>Create Time: 2015-8-12 下午2:43:33</li>
 * @version 1.0.0
 * @author  HooRang
 */
public class CmgeBaseActivity extends Activity {

	
	protected SDKKitPlatformCore sdkObj;
	private DemoListViewAdapter adapter ; 
	private ExpandableListView epdListView ;
	
	public static boolean hasLogined = false ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getResources().getIdentifier("demo_expand_listview", "layout", getPackageName()));
		//传入回调接口的实现类
		sdkObj = SDKKitPlatformCore.initPlatformFramework(this,new CmgeSDKCallBack(this));
		sInitView();
	}
	
	
	private void sInitView(){
		epdListView = (ExpandableListView)findViewById(getResources().getIdentifier("demo_main_expandable_listview", "id", getPackageName()));
		
		adapter = new DemoListViewAdapter(this, DataSources.sParentObj, DataSources.sChildrenObj);
		epdListView.setAdapter(adapter);
		
		
		epdListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {
				
				switch (groupPosition) {
				case DataSources.FUNCTION_CODE_BUSSINES:
					if (childPosition == 0) {
						sdkLogin();
					}else if (childPosition == 1) {
						sdkPay();
					}else if (childPosition == 2) {
						sdkLogout();
					}else if (childPosition == 3) {
						sdkUserCenter();
					}
					
					break;
				case DataSources.FUNCTION_CODE_DATAS:
					
					if (!hasLogined) {
						Toast.makeText(CmgeBaseActivity.this, "请先登录再测试统计接口", Toast.LENGTH_SHORT).show();
						return false;
					}
					
					
					if (childPosition == 0) {
						sdkEnterGameData();
					}else if (childPosition == 1) {
						sdkPayData();
					}else if (childPosition == 2) {
						sdkCreateRoleData();
					}else if (childPosition == 3) {
						sdkUpgradeData();
					}
					break;
				case DataSources.FUNCTION_CODE_SHARE:
					if (childPosition == 0) {
						sdkShare();
					}
					break;
				case DataSources.FUNCTION_CODE_PUSH:
					if (childPosition == 0) {
						sdkPush();
					}else if (childPosition == 1) {
						sdkPushTags();
					}
					break;

				default:
					break;
				}
				
				
				return true;
			}
		});
	}
	
	
	
	// /
	// ------------------------------业务功能接口 开始-------------------------
	// /
	
	/**
	 * sdk login
	 * 
	 * @param v
	 */
	public void sdkLogin() {
		sdkObj.User.login(this);
	}

	/**
	 * sdk pay
	 * 
	 * @param v
	 */
	public void sdkPay() {
		
		ParamsContainer pc = new ParamsContainer();
		// 所购买商品金额, 以元为单位。
		pc.putInt(ParamsKey.KEY_PAY_AMOUNT, 10);
		// 购买数量 
		pc.putInt(ParamsKey.KEY_PAY_PRODUCT_NUM, 1);
		// 订单号， 游戏方自定义订单号，可为""
		pc.putString(ParamsKey.KEY_PAY_ORDER_ID, "");
		//商品ID,请注意必须是整型
		pc.putInt(ParamsKey.KEY_PAY_PRODUCT_ID, 100);
		// 所购买商品名称
		pc.putString(ParamsKey.KEY_PAY_PRODUCT_NAME, "60元宝");
		// 区服ID 
		pc.putString(ParamsKey.KEY_PAY_SERVER_ID, "1");
		// 区服名
		pc.putString(ParamsKey.KEY_PAY_SERVER_NAME, "水月洞天");
		// 角色ID
		pc.putString(ParamsKey.KEY_PAY_ROLE_ID, "10");
		// 角色名
		pc.putString(ParamsKey.KEY_PAY_ROLE_NAME, "至尊宝");
		// 角色等级
		pc.putString(ParamsKey.KEY_PAY_ROLE_LEVEL, "35");
		// 扩展参数,会直接透传给cp服务端,可为""
		pc.putString(ParamsKey.KEY_EXTINFO, "");
		
		sdkObj.Pay.pay(pc);

	}

	/**
	 * sdk 注销
	 * 
	 * @param v
	 */
	public void sdkLogout() {
		sdkObj.User.logout();
	}



	/**
	 * 进入用户中心
	 * 
	 * @param v
	 */
	public void sdkUserCenter() {
		sdkObj.User.userCenter();
	}

	// /
	// ------------------------------业务功能接口 结束-------------------------
	// /
	
	
	
	
	// /
			// ------------------------------统计部分接口 开始-------------------------
			// /

			/**
			 * 统计进入游戏
			 * 接入点：进入游戏时调用
			 * 
			 * @param view
			 */
			public void sdkEnterGameData() {
				ParamsContainer pc = new ParamsContainer();
				
				// 角色id
				pc.putString(ParamsKey.KEY_ROLE_ID, "1");
				// 角色昵称
				pc.putString(ParamsKey.KEY_ROLE_NAME, "角色昵称");
				// 角色等级
				pc.putInt(ParamsKey.KEY_ROLE_LEVEL, 15);
				// 服务器编号
				pc.putString(ParamsKey.KEY_SERVER_ID, "1");
				// 服务器名称
				pc.putString(ParamsKey.KEY_SERVER_NAME, "服务器名称");
			

				sdkObj.Collections.onDatas(DataTypes.DATA_ENTER_GAME, pc);

			}
			
			

			/**
			 * 统计支付
			 * 调用点：支付成功之后，切记是成功之后
			 * @param view
			 */
			public void sdkPayData() {
				ParamsContainer pc = new ParamsContainer();
				// 充值金额
				pc.putInt(ParamsKey.KEY_AMOUNT, 6);
				// 服务器ID
				pc.putString(ParamsKey.KEY_SERVER_ID, "3");
				// 服务器名称
				pc.putString(ParamsKey.KEY_SERVER_NAME, "西南一区");
				// 角色唯一标识
				pc.putString(ParamsKey.KEY_ROLE_ID, "2323"); // 取的时候要特别注意
				// 订单号
				pc.putString(ParamsKey.KEY_ORDERNUMBER, "123");
				// 玩家等级
				pc.putString(ParamsKey.KEY_ROLE_GRADE, "5");
				// 角色昵称
				pc.putString(ParamsKey.KEY_ROLE_NAME, "角色升级昵称");
				// 商品描述
				pc.putString(ParamsKey.KEY_PRODUCT_DESC, "这里是我的商品描述");

				sdkObj.Collections.onDatas(DataTypes.DATA_PAY, pc);

			}





			/**
			 * 统计用户升级
			 * 调用点：角色升级时，必须实时的调用，数据请填写真实准确的数据
			 * @param view
			 */
			public void sdkUpgradeData() {
				ParamsContainer pc = new ParamsContainer();
				// 服务器编号
				pc.putString(ParamsKey.KEY_SERVER_ID, "12");
				// 玩家等级
				pc.putString(ParamsKey.KEY_ROLE_LEVEL, "3");
				// 角色id
				pc.putString(ParamsKey.KEY_ROLE_ID, "1");
				// 角色昵称
				pc.putString(ParamsKey.KEY_ROLE_NAME, "角色昵称");
				// 服务器名称
				pc.putString(ParamsKey.KEY_SERVER_NAME, "西南一区");

				sdkObj.Collections.onDatas(DataTypes.DATA_ROLE_UPGRADE, pc);
			}

			/**
			 * 统计创建角色
			 * 调用点：创建角色时调用
			 * @param view
			 */
			public void sdkCreateRoleData() {

				ParamsContainer pc = new ParamsContainer();
				// 角色标识
				pc.putString(ParamsKey.KEY_ROLE_ID, "1");
				// 角色昵称
				pc.putString(ParamsKey.KEY_ROLE_NAME, "角色升级昵称");
				// 服务器编号
				pc.putString(ParamsKey.KEY_SERVER_ID, "2");
				// 服务器名称
				pc.putString(ParamsKey.KEY_SERVER_NAME, "服务器名称");

				sdkObj.Collections.onDatas(DataTypes.DATA_CREATE_ROLE, pc);

			}


			// /
			// ------------------------------统计部分接口 结束-------------------------
			// /
	
	
	
			// /
			// ------------------------------推送部分接口 开始-------------------------
			// /
			
			public void sdkPush(){
				
				sdkObj.Push.startWork(this);
				
			}
			
			public void sdkPushTags(){
				
				sdkObj.Push.setTags("深度玩家");
				
			}
	
			
			// /
			// ------------------------------推送部分接口 结束-------------------------
			// /
	
	
	
			// /
			// ------------------------------分享部分接口 结束-------------------------
			// /
			public void sdkShare(){
				
				ParamsContainer pc = new ParamsContainer();
			
				
				pc.putString(ParamsKey.KEY_SHARE_TITLE, "快发分享");
				pc.putString(ParamsKey.KEY_SHARE_DESCRIPTION, "开发者几行代码就可以为应用添加分享送积分功能，并提供详尽的后台统计数据，除了本身具备的分享功能外，开发者也可将积分功能单独集成在已有分享组件的app上，快来试试吧 ");
				pc.putString(ParamsKey.KEY_SHARE_TEXT, "分享分享分享分享分享");
				pc.putString(ParamsKey.KEY_SHARE_TARGETURL, "http://youtui.mobi/");
				pc.putString(ParamsKey.KEY_SHARE_IMAGEURL, "http://pic.nipic.com/2007-11-09/2007119121849495_2.jpg");
				sdkObj.Share.share(pc);
			}
	
	
	
	
	
	
	

	// /
	//以下接口，无需做任何修改，拷贝进游戏的主Activity即可
	// ------------------------------生命周期函数 开始-------------------------
	// /
	@Override
	protected void onResume() {
		super.onResume();
		if (sdkObj != null) {
			sdkObj.LifeCycle.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sdkObj != null) {
			sdkObj.LifeCycle.onPause();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (sdkObj != null) {
			sdkObj.LifeCycle.onStop();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (sdkObj != null) {
			sdkObj.LifeCycle.onDestroy();
		}
		
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (sdkObj != null) {
			sdkObj.LifeCycle.onConfigurationChanged(newConfig);
		}
	}

	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (sdkObj != null) {
			sdkObj.LifeCycle.onSaveInstanceState(outState);
		}
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (sdkObj != null) {
			sdkObj.LifeCycle.onNewIntent(intent);
		}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			sdkObj.Base.exitGame(this);
		}
		return super.onKeyDown(keyCode, event);
	}
	// /
	// ------------------------------生命周期函数 结束-------------------------
	// /
		
}


