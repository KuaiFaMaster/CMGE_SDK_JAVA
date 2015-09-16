package com.cmge.sdk.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.cmge.sdkkit.framework.mw.openapi.callback.SDKKitPlatformCallBack;


/**
 * <li>文件名称: CmgeSDKCallBack.java</li>
 * <li>文件描述: 回调接口示例代码</li>
 * <li>公    司: </li>
 * <li>内容摘要: 无</li>
 * <li>新建日期: 2015-8-12 下午4:27:38</li>
 * <li>修改记录: 无</li>
 * @version 产品版本: 2.0
 * @author  作者姓名: HooRang
 */
public class CmgeSDKCallBack implements SDKKitPlatformCallBack {
	
	private static final String TAG = "CmgeSDKCallBack";
	/**
	 * **说明： 
	 * 每个接口的回调状态分为两种
	 * 
	 * 1.SDKKitPlatformCallBack.STATUS_SUCCESS ： 成功
	 * 2.SDKKitPlatformCallBack.STATUS_FAIL    ： 失败
	 * 
	 * 以下代码仅仅为回调处理的一个简单示例 ，具体每个接口的回调需要游戏方自行处理成功或失败的逻辑
	 * 
	 * 必接接口务必全部接入，愿你接入顺利！
	 * 
	 * 
	 */
	
	private Context mCtx ;
	
	
	
	public CmgeSDKCallBack(Context context){
		mCtx = context;
	}
	
	/**
	 * @param retStatus    回调状态
	 * @param retMessage   回调信息
	 * 
	 */
	@Override
	public  void initCallBack(int retStatus, final String retMessage) {
		Log.i(TAG, "initCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage);
		Toast.makeText(mCtx, retMessage, Toast.LENGTH_SHORT).show();
		if (retStatus == SDKKitPlatformCallBack.STATUS_SUCCESS) {
			//成功， 只有在sdk初始化成功之后才能调用sdk的登录
		}else {
			//失败
		}
	}
	
	

	@Override
	public void exitGameCallBack(int retStatus, String retMessage) {
		Toast.makeText(mCtx, retMessage, Toast.LENGTH_SHORT).show();
		Log.i(TAG, "exitGameCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage);
		if (retStatus == SDKKitPlatformCallBack.STATUS_SUCCESS) {
			// 退出提示框点击了确定，做资源回收，退出应用
			System.exit(0);
			Process.killProcess(android.os.Process.myPid());
		}else {
			//点击了取消
		}
	}

	
	
	/**
	 * 
	 * @param loginUserId    	某个渠道的用户唯一标识(非空)
	 * @param loginUserName    	某个渠道的用户名(特别注意，可能为“”)
	 * @param loginAuthToken    用于登录验证的token(非空)
	 * @param loginOpenId    	全平台用户唯一标识(非空)
	 * @param retStatus    		回调状态
	 * @param retMessage   		回调信息
	 */
	@Override
	public void loginCallBack(String loginUserId,
			String loginUserName,
			String loginAuthToken,
			String loginOpenId,
			int retStatus,
			String retMessage) {

		Log.i(TAG, "loginCallBack-->retStatus#" + retStatus + ",retMessage#" 
					+ retMessage+",loginUserId#"+loginUserId+",loginUserName#"
					+loginUserName+",loginAuthToken#"+loginAuthToken+",loginOpenId#"+loginOpenId);
		final String msg = "loginCallBack-->retStatus#" + retStatus + ",retMessage#" 
				+ retMessage+",loginUserId#"+loginUserId+",loginUserName#"
				+loginUserName+",loginAuthToken#"+loginAuthToken+",loginOpenId#"+loginOpenId;
		Toast.makeText(mCtx, msg, Toast.LENGTH_SHORT).show();
		
		if (retStatus == SDKKitPlatformCallBack.STATUS_SUCCESS) {
			//登录成功 
			CmgeBaseActivity.hasLogined = true ;
		}else {
			//登录失败：游戏这里必须做处理，可以做失败提示或者再次激活登录按钮让玩家可以再次发起登录操作
		}
	}

	@Override
	public void logoutCallBack(int retStatus, String retMessage) {
		Toast.makeText(mCtx, retMessage, Toast.LENGTH_SHORT).show();
		Log.i(TAG, "logoutCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage);
		if (retStatus == SDKKitPlatformCallBack.STATUS_SUCCESS) {
			//注销成功， 调用sdk的登录
		}
	}

	
	
	/**
	 * 
	 * @param payKitOrderId    	平台订单号
	 * @param retStatus    		回调状态
	 * @param retMessage   		回调信息
	 */
	@Override
	public void payCallBack(String payKitOrderId, int retStatus,String retMessage) {
		Toast.makeText(mCtx, retMessage, Toast.LENGTH_SHORT).show();
		Log.i(TAG, "payCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage+",payKitOrderId#" + payKitOrderId);
		if (retStatus == SDKKitPlatformCallBack.STATUS_SUCCESS) {
			//支付成功 ，确认到账后就可以调用sdk的支付统计接口：sdkObj.Collections.onDatas(DataTypes.DATA_PAY, pc);
			
		}
	}

	
	/**
	 * 
	 * @param orderStatus    	订单当前状态(0：整个充值流程已经成功走完;4：充值成功但是添加道具失败；)
	 * @param retStatus    		回调状态
	 * @param retMessage   		回调信息
	 */
	@Override
	public void getOrderResultCallBack(String orderStatus,int retStatus, String retMessage) {
		Toast.makeText(mCtx, retMessage, Toast.LENGTH_SHORT).show();
		Log.i(TAG, "getOrderResultCallBack-->retStatus#" + retStatus + ",retMessage#" + retMessage);
		if (retStatus == SDKKitPlatformCallBack.STATUS_SUCCESS) {
			
		}
	}


	/**
	 * 推送消息接收接口
	 */
	@Override
	public void pushReceiveCallBack(int retStatus, String retMessage) {
		Toast.makeText(mCtx, retMessage, Toast.LENGTH_SHORT).show();
		Toast.makeText(mCtx, "游戏内收到了消息推送", Toast.LENGTH_LONG).show();
	}

	


}


