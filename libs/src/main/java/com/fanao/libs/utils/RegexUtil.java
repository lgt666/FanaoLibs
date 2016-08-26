package com.fanao.libs.utils;

import java.util.regex.Pattern;

public class RegexUtil {

	/**
	 * 验证用户名必须是英文、中文、数字以及下划线，并且长度为6-12位
	 */
	public static final String USERNAME_PATTERN = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]{6,12}+$";

	/**
	 * 验证用户名必须是汉字,并且长度在2-15个汉字
	 */
	public static final String RealName_PATTERN = "^[\\u4e00-\\u9fa5]{2,15}+$";	
    public static final String RealName_Message = "真实姓名必须是2到15个汉字!";
	
	/**
	 * 验证密保问题必须是汉字,并且长度在1-15个汉字
	 */
	public static final String RealName_PATTERN1 = "^[\\u4e00-\\u9fa5]{1,15}+$";
	
	/**
	 * 验证密保答案必须是汉字,并且长度在1-30个汉字
	 */
	public static final String RealName_PATTERN2 = "^[\\u4e00-\\u9fa5]{1,30}+$";
	
	/**
	 * 固定电话规则
	 */
	public static final String phone_PATTERN = "^0(10|2[0-5789]|\\d{3})\\d{7,8}$";
	public static final String Phone_Message = "正确固话格式：020-4008866956";
	
	/**
	 * ֻ只能是a-z,A-Z以及数字_
	 */
	public static final String Pecial_PATTERN = "^[a-zA-Z0-9_]+$";
	
	/**
	 * ֻ只能是8-18位a-z,A-Z以及数字_   密码
	 */
	public static final String Psw_PATTERN1 = "^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{8,18}$"; // 8,30

	/**
	 * 手机号码规则,13*,15*,18*
	 */
	// public static final String
	// Mobile_PATTERN="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	public static final String Mobile_PATTERN = "^((13[0-9])|(14[5,7])|(15[0-9])|(17[6-8])|(18[0-9]))\\d{8}$";
	public static final String Mobile_Message = "请输入正确的手机号码!";
	
	/**
	 * 固定电话验证
	 */
	//  ^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$
	public static final String Home_Phone = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";
	public static final String Home_Phone_Message = "请输入正确的固定电话，如“0774-8881234”!";
	
	/**
	 * 电子邮件
	 */
	public static final String Emial_PATTERN = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	public static final String Emial_Message = "请输入正确的电子邮箱!";

	/**
	 * 身份证号码，15位，或18位
	 */
	public static final String IDENTITY_CAR = "(^\\d{18}$)|(^\\d{17}(\\d|x|X)$)|(^\\d{15}$)";
	public static final String IDENTITY_CAR_Message = "请输入正确的身份证号码！";
	
	/**
	 * 密码长度
	 */
	public static final String PASSWORD_PATTERN = ".{6,32}";
	public static final String PASSWORD_Message = "请输入6-32位的密码!";

	/**
	 * 车牌号码
	 */
	public static final String LICENSE_PATTERN = "^([\\u4e00-\\u9fa5]{1,2}[A-Za-z]{1}[.]{0,1})[0-9A-Za-z]{5}$";
	public static final String LICENSE_Message = "请输入正确的车牌号码!";
	
	
	/**
	 * 得到比对对象
	 */
	public static Pattern getValidator(String Pattern_String) {
		return Pattern.compile(Pattern_String);
	}
	

	/**
	 * 对比是否正确
	 */
	public static boolean Validate(String Pattern_String, String validateString) {
		try {
			// 如果规则和验证内容为null或为空就返回false;
			if (Pattern_String == null || Pattern_String.length() <= 0
					|| validateString == null) {
				return false;
			}
			return getValidator(Pattern_String).matcher(validateString)
					.matches();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
