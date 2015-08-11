package com.pluu.androidview.data;

import android.content.res.Resources;

/**
 * Created by PLUUSYSTEM-NEW on 2015-08-11.
 */
public enum RES_CODE {
	INFORMATIONAL(100),
	SUCCESS(200),
	REDIRECTION(300),
	CLIENT_ERROR(400),
	SERVER_ERROR(500),
	;

	private final int code;

	RES_CODE(int code) {this.code = code;}

	public int getCode() {
		return code;
	}

	/**
	 * Get Response Code
	 * @param code Server Result Code
	 * @return Client Side Code;
	 */
	public static RES_CODE getCodeGenerator(int code) {
		for (RES_CODE item : RES_CODE.values()) {
			if (code == item.getCode()) {
				return item;
			}
		}
		throw new Resources.NotFoundException("Not Found Response Code");
	}

	@Override
	public String toString() {
		return "RES_CODE{" +
			"name=" + name() +
			", code=" + code +
			'}';
	}
}
