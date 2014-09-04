package com.hyt.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: NumberUtils 數字相關Utils
 * Description: 
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/29
 */
public class NumberUtils {
	private static final Pattern AMOUNT_PATTERN = Pattern.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d)$");
	private static final char[] TWD_NUMS = "零壹貳參肆伍陸柒捌玖".toCharArray();
	private static final String[] UNITS = {"元", "角", "分", "整"};
	private static final String[] U1 = {"", "拾", "佰", "仟"};
	private static final String[] U2 = {"", "萬", "億"};

	/**  
	 * 將金額（整數部分等於或少於12位，小數部分2位）轉換為中文大寫形式.  
	 * @param amount 金額數字  
	 * @return 中文大寫  
	 */  
	public static String convert2twd(String amount) {
		// 去掉分隔符
		amount = amount.replace(",", "");

		// 驗證金額正確性
		Matcher matcher = AMOUNT_PATTERN.matcher(amount);
		if (!matcher.find()) {
			return amount;
		}

		String integer  = matcher.group(1); // 整數部分
		String fraction = matcher.group(2); // 小數部分

		String result = "";
		if (!integer.equals("0")) {
			result += integer2twd(integer) + UNITS[0]; // 整數部分
		}
		if (fraction.equals("00")) {
			result += UNITS[3]; // 添加[整]
		} else if (fraction.startsWith("0") && integer.equals("0")) {
			result += fraction2twd(fraction).substring(1); // 去掉分前面的[零]
		} else {
			result += fraction2twd(fraction); // 小數部分
		}

		return result;
	}

	/**
	 * 將金額小數部分轉換為中文大寫
	 * @param fraction
	 * @return
	 */
	private static String fraction2twd(String fraction) {
		char jiao = fraction.charAt(0); // 角
		char fen  = fraction.charAt(1); // 分
		return (TWD_NUMS[jiao - '0'] + (jiao > '0' ? UNITS[1] : "")) + (fen > '0' ? TWD_NUMS[fen - '0'] + UNITS[2] : "");
	}

	/**
	 * 將金額整數部分轉換為中文大寫
	 * @param integer
	 * @return
	 */
	private static String integer2twd(String integer) {
		StringBuilder buffer = new StringBuilder();
		// 從個位數開始轉換
		int i, j;
		for (i = integer.length() - 1, j = 0; i >= 0; i--, j++) {
			char n = integer.charAt(i);
			if (n == '0') {
				// 當n是0且n的右邊一位不是0時，插入[零]
				if (i < integer.length() - 1 && integer.charAt(i + 1) != '0') {
					buffer.append(TWD_NUMS[0]);
				}
				// 插入[萬]或者[億]
				if (j % 4 == 0) {
					if (i > 0 && integer.charAt(i - 1) != '0'  
							|| i > 1 && integer.charAt(i - 2) != '0'  
							|| i > 2 && integer.charAt(i - 3) != '0') {
						buffer.append(U2[j / 4]);
					}
				}
			} else {
				if (j % 4 == 0) {
					buffer.append(U2[j / 4]);  // 插入[萬]或者[億]
				}
				buffer.append(U1[j % 4]);// 插入[拾]、[佰]或[仟]
				buffer.append(TWD_NUMS[n - '0']); // 插入數字
			}
		}
		return buffer.reverse().toString();
	}
}
