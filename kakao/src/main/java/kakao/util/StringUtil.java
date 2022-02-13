package kakao.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtil {
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	public static String getDateFormat(LocalDateTime localDateTime, String format) {
		
		String date = "";
		
		if(isEmpty(format)) date = "YYYY-MM-dd";
		
		if(localDateTime != null) {
			date = localDateTime.format(DateTimeFormatter.ofPattern(format));
		}

		return date;
	}
}
