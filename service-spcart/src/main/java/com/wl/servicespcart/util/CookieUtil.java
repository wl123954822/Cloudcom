package com.wl.servicespcart.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie util
 * @author wanglei
 */
public class CookieUtil {

	/**
	 * 设置cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void set(HttpServletResponse response,
						   String name,
						   String value,
						   int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 取得cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie get(HttpServletRequest request,
                             String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie: cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}

		return null;
	}

	/**
	 * 删除cookie
	 * @param name
	 * @param request
	 * @param response
	 */
	public static void deleteCookie(String name,HttpServletRequest request,HttpServletResponse response) {
		Cookie cookie = get(request,name);
		if (cookie != null) {
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			return;
		}
	}
}
