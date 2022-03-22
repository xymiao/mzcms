package com.xymiao.common.utils;

import com.xymiao.common.ids.SnowFlakeShortUrl;

public class IdsUtils {
	private static SnowFlakeShortUrl snowFlake = new SnowFlakeShortUrl(2, 3);
	/**
	 * 根据雪花算法，得到一个 10 位的 64 进制的字符串
	 * @return 10 位的字符串
	 */
	public static String getId() {
		return Encode64.CompressNumber(snowFlake.nextId());
	}
}
