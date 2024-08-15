package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {

	private static EmailUtils instance;

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	public boolean validaEmail(String emailAddress) {
		if (emailAddress == null || emailAddress.isEmpty()) {
			return false;
		}
		Matcher matcher = EMAIL_PATTERN.matcher(emailAddress);
		return matcher.matches();
	}

	public static EmailUtils getInstance() {
		if (instance == null)
			instance = new EmailUtils();
		return instance;
	}

}
