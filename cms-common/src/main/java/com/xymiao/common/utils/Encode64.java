package com.xymiao.common.utils;

import com.xymiao.common.ids.SnowFlakeShortUrl;

public class Encode64 {
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '_', '-', };

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println(UnCompressNumber("wls5yt"));
		SnowFlakeShortUrl snowFlake = new SnowFlakeShortUrl(1, 1);
		for (int i = 0; i < (1 << 4); i++) {

			long timeMillins = snowFlake.nextId();
			System.out.println("初始值:" + timeMillins);
			String tmp = CompressNumber(timeMillins);
			System.out.println("加密后:" + tmp);
			System.out.println("解密后:" + UnCompressNumber(tmp));
		}
	}

	/**
	 * 把10进制的数字转换成64进制
	 * 
	 * @param number
	 * @param shift
	 * @return
	 */
	public static String CompressNumber(long number) {
		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << 6;
		long mask = radix - 1;
		do {
			buf[--charPos] = digits[(int) (number & mask)];
			number >>>= 6;
		} while (number != 0);
		return new String(buf, charPos, (64 - charPos));
	}

	/**
	 * 把64进制的字符串转换成10进制
	 * 
	 * @param decompStr
	 * @return
	 */
	private static long UnCompressNumber(String decompStr) {
		long result = 0;
		for (int i = decompStr.length() - 1; i >= 0; i--) {
			for (int j = 0; j < digits.length; j++) {
				if (decompStr.charAt(i) == digits[j]) {
					result += ((long) j) << 6 * (decompStr.length() - 1 - i);
				}
			}
		}
		return result;
	}
}
