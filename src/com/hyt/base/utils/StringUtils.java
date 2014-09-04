package com.hyt.base.utils;

import java.text.DecimalFormat;

/**
 * Title: StringUtils 字串相關Utils
 * Description: 
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/29
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
    public static final String SEPARATE_LINE = "\r\n";
    public static final String FULL_WIDTH_SPACE = "　";
    
    /**
     * Test
     * @param args
     */
    public static void main(String args[]) {
    	System.out.println(getMoneyStr("1"));
    	System.out.println(getMoneyStr("13333123"));
    	System.out.println(getMoneyStr("122223.33"));
    	System.out.println(getMoneyStr("177331.2331"));
    	System.out.println(getMoneyStr("-199999.2"));
    	System.out.println(getMoneyStr("2000000"));
    	System.out.println(getMoneyStr("3000"));
    	
    	System.out.println(trimAllSpace("　　3000　　　"));
    	System.out.println(trimAllSpace("   3000   "));
    	System.out.println(trimAllSpace("　  　3000"));
    	System.out.println(trimAllSpace("3000　　　    　 "));
    	System.out.println(trimAllSpace("　　　    　 "));
    	System.out.println(trimAllSpace("　　　　"));
    	System.out.println(trimAllSpace("          "));
    	
    }
    
	/**
	 * 將字串轉換為數字，如果轉換失敗則回傳預設值
	 * @param sValue
	 * @param iDefaultValue
	 * @return
	 */
	public static int str2Int(String str, int defValue) {
		int rsl = defValue;
		try {
			rsl = Integer.parseInt(str);
		} catch (Exception e) {
			rsl = defValue;
		}

		return rsl;
	}

	/**
	 * 將字串轉換為數字，如果轉換失敗則回傳0
	 * @param sValue 待轉換之字串
	 * @param iDefaultValue	轉換失敗時之預設值
	 * @return 轉換後之數字，失敗則傳回0
	 */
	public static int str2Int(String str) {
		return str2Int(str, 0);
	}
	
	/**
	 * 將字串轉換為金額的表示方式
	 * StringUtils.getMoneyStr(null) = ""
	 * StringUtils.getMoneyStr("1234567.89") = "1,234,567.89"
	 * StringUtils.getMoneyStr("1234567.00") = "1,234,567"
	 * StringUtils.getMoneyStr("1234567.0100") = "1,234,567.01"
	 * @param sMoney
	 * @return
	 */
	public static String getMoneyStr(String money) {
		if (money == null) {
			return "";
		}

		money = money.trim();
		Double d = 0d;
		try {
			d = Double.parseDouble(money);
		} catch (Exception e) {
			return money;
		}
		String decimal = "";
		if (money.indexOf(".") != -1) {
			decimal = money.substring(money.lastIndexOf("."));	
		}
		
		DecimalFormat df = new DecimalFormat("##,###,###,###,##0");
		try {
			money = df.format(d);
		} catch (Exception e) {
			return money;
		}
		
		return money + decimal;
	}
	
	/**
	 * 移除字串左右邊之半形、全形空白
	 * @param sValue
	 * @return
	 */
	public static String trimAllSpace(String value) {
		if (value == null) {
			return value;
		}
		
        int len = value.length();
        int st = 0;
        char[] val = value.toCharArray();

        while ((st < len) && (val[st] == ' ' || val[st] == '　')) {
            st++;
        }
        while ((st < len) && (val[len - 1] == ' ' || val[len - 1] == '　')) {
            len--;
        }
        return ((st > 0) || (len < value.length())) ? value.substring(st, len) : value;
	}
	
    /**
     * 字串是否都為全型
     * @param str
     * @return
     */
    public static boolean isAllFullWidth(String str) {
    	if (str == null) {
    		return true;
    	}
    	
    	return str.matches("^[^\\x00-\\xff]*$");
    }
    
	/**
	 * 字串右補空白到指定位數
	 * @param str 原始字串
	 * @param maxLen 字串長度
	 * @return 補完後字串
	 */
	public static String fillBlank(String str, int maxLen) {
		return fillString(str, maxLen, " ");
	}    
    
	/**
	 * 字串右補特定字元到指定位數
	 * @param str 原始字串
	 * @param maxLen 字串長度
	 * @param fillChar 右補的字元
	 * @return 補完後字串
	 */
	public static String fillString(String str, int maxLen, String fillChar) {
		if (str == null) {
	      return str;
	    }
	    
		byte[] str_byte = str.getBytes();
	    int str_len = str_byte.length;
	
	    if (str_len >= maxLen) {
	        return str;
	    }
	    StringBuffer sbf = new StringBuffer();
	    sbf.append(str);
	    for (; str_len < maxLen; ++str_len) {
	        sbf.append(fillChar);
	    }
	    return sbf.toString();
	}
}
