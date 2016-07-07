package com.langdunzx.www.zanbei.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * 业务名:
 * 功能说明:  正则 工具类
 * 编写日期:	2015年11月27日10:37:11
 * 作者:	DC
 *   
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class CheckUtil {
	 /** 手机号正则 */
    private static String mobPhoneNumRE = "^((13[0-9])|(17[0-9])|(15[^4])|(18[0,2-9]))\\d{8}$";

    /** 邮箱正则 */
    private static String emailRE = "^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /** 姓名正则 */
    private static String nameRE = "^([\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*)|[a-zA-Z]+$";

    /** 座机号正则 */
    private static String phoneNumRE = "^([0-9]{3,4}(-)?)[0-9]{7,8}$";
    
    /** 密码正则*/
   // private static String passWord = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    /** 密码正则*/
//    private static String passWord = "(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[A-Z]).{6,16}";
//    private static String passWord = "(?=.*?[a-zA-Z]+.*?)(?=.*?[1-9]+.*?)(?=.*?[\\p{Punct}]+.*?).*{6,16}";
//   private static String passWord   = "(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,16}$";
   // private static String   passWord = "(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&*])(?=.*[A-Z]).{6,16}";
    /** 身份证正则*/
    private static String isIDCard1="^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"; 
    
    private static String isIDCard2="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
    
    private static String isIDCard="^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
    //充值输入正整数
     private static String  intVal ="^[1-9]\\d*$";
     //>=0
     private static String  intBuyVal ="^[0-9]\\d*$";
    /**Edingtou 姓名正则**/
    //private static String IsName="[\u4e00-\u9fa5]{1,10}";
    private static String IsName="^[\u4e00-\u9fa5]{1,10}$";
    public static boolean isPhoneOrMob(String phoneNum)
    {
        return (isModleStr(phoneNum, mobPhoneNumRE) || isModleStr(phoneNum,
                phoneNumRE));
    }
    
    /**
     * @Title: isIdentyCard1OrCard2
     * @Description: 验证身份证
     * @param @param 身份证
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isIdentyCard1OrCard2(String card)
    {
        return isModleStr(card, isIDCard1) || isModleStr(card, isIDCard2);
    }

    /**
     * @Title: isEmail
     * @Description: 验证邮箱
     * @param @param email
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isEmail(String email)
    {
        return isModleStr(email, emailRE);
    }

    /**
     * @Title: isPhoneNum
     * @Description: 验证手机号
     * @param @param num
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isPhoneNum(String num)
    {
        return isModleStr(num, mobPhoneNumRE);
    }

    /**
     * @Title: isPhoneNum
     * @Description: 验证姓名
     * @param @param num
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isName(String name)
    {
        return isModleStr(name, nameRE);
    }
    public static boolean isEdingtouName(String name)
    {
    	return isModleStr(name, IsName);
    }
//    public static boolean isPassword (String password){
//    	return isModleStr(password,passWord);
//    }
    public static boolean isIntval (String intval){
    	return isModleStr(intval,intVal);
    }
    public static boolean isIntBuyval (String intval){
    	return isModleStr(intval,intBuyVal);
    }
    /**
     * @Title: isModleStr
     * @Description: 传入字符串和正则表达式，验证是否符合此正则
     * @param @param paramString1
     * @param @param paramString2
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isModleStr(String str, String regex)
    {
        if (str == null)
        {
            return false;
        }
        else
        {
            return Pattern.compile(regex).matcher(str).matches();
        }
    }

    /**
     * @Title: isNotNull
     * @Description: 非空校验
     * @param @param paramString1
     * @param @param paramString2
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean isNotNull(String str)
    {
        if (str != null && !str.equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    public static boolean isPassword(String password){
		if(password.matches("^[a-zA-Z0-9,!,！,@,#,$,￥,%,&,^,*,(,),_,+,-,=,`,~,.,。,，,<,>,《,》,?,？,、,\\,/,||,{,},[,],【,】,——,;,；,:,：,',\",\",“,”]{6,16}$")){
			if(password.matches("^[a-zA-Z]{6,16}$"))return false;
			if(password.matches("^[0-9]{6,16}$"))return false;
			if(password.matches("^[!,！,@,#,$,￥,%,&,^,*,(,),_,+,-,=,`,~,.,。,，,<,>,《,》,?,？,、,\\,/,||,{,},[,],【,】,——,;,；,:,：,',\",\",“,”]{6,16}$"))return false;
			return true;
		}else{
			return false;
		}
	}
    //省份证x 转大写
    public static   String extest(String strval) {
		  String strname = null;
	        String reg = "[a-zA-Z]";//正则表达是，只取一个英文字符
	        Pattern p = Pattern.compile(reg);//获取正则对象
	        Matcher m = p.matcher(strval);//获取匹配器
	    String val= strval.substring(strval.length()-1,strval.length());
	    System.out.println(val);
	        if(val.equals("x")){
	           return   strval.substring(0,strval.length()-1)+"X";
	            }else{
	            	return strval;
	            }
	              
	            }
}
