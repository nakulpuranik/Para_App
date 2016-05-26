package com.app.dna.manager.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UTIL {

	public static boolean checkLoginFromSharedPref(Context applicationContext) 
	{
		try
		{
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
			String isLogin = preferences.getString("IsLogin",null);
			
			if(isLogin.equalsIgnoreCase("true"))
			{
				return true;
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	public static boolean setLoginToSharedPref(Context applicationContext) 
	{
		try
		{
			SharedPreferences isLoginSharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext);
			SharedPreferences.Editor editor = isLoginSharedPref.edit(); 
            editor.putString("IsLogin", "true");
            editor.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
}

