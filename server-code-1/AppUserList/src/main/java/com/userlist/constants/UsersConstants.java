package com.userlist.constants;

import java.util.ArrayList;

public class UsersConstants {

	private static final ArrayList<String> userTypes=new ArrayList<String>();
	private static int MAX_SUBGROUP;
	public  static ArrayList<String> getUserTypes() {
		return UsersConstants.userTypes;
	}

	public void setUserTypes(ArrayList<String> userTypes) {
		System.out.println("User Types to Set"+userTypes.toString());
		UsersConstants.userTypes.addAll(userTypes);
		
	}

	public static int getMAX_SUBGROUP() {
		return MAX_SUBGROUP;
	}

	public  void setMAX_SUBGROUP(int mAX_SUBGROUP) {
		MAX_SUBGROUP = mAX_SUBGROUP;
	}
}
