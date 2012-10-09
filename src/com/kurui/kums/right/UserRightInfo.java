package com.kurui.kums.right;

import com.kurui.kums.user.SysUser;
import com.kurui.kums.base.*;


public class UserRightInfo extends BaseRightInfo {
 	private static final long serialVersionUID = 1L;

	private SysUser user;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
	
	public long getUserId() {
		long uId=0;
		if(user!=null){
			uId=user.getUserId();
		}
		return uId;
	}
}
