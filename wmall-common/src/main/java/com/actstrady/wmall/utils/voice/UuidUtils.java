package com.actstrady.wmall.utils.voice;

import java.util.UUID;

public class UuidUtils {
	public static String getUuid32() {
		return UUID.randomUUID().toString().replace("-", "");        
	}
}
