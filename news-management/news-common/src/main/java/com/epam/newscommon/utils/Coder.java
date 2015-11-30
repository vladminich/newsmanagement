package com.epam.newscommon.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Class Coder. Class uses for hashing user password.
 * 
 * @author Uladzislau_Minich
 *
 */
public class Coder {
	/**
	 * Hash md5.
	 *
	 * @param text
	 *            the text
	 * @return the string
	 */
	public static String hashMD5(String text) {
		return DigestUtils.md5Hex(text);
	}
}
