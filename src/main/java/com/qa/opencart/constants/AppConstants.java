package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {

	public final static String LOGIN_PAGE_TITLE = "Account Page";
	public final static String ACCOUNT_PAGE_TITLE = "My Account";
	public final static String LOGIN_PAGE_URL = "route=account/login";

	public final static int DEFAULT_SHORT_TIMEOUT = 5;
	public final static int DEFAULT_MEDUM_TIMEOUT = 10;
	public final static int DEFAULT_LONG_TIMEOUT = 30;
	public final static int ACC_PAGE_HEADER_COUNT = 4;
	public final static int SEARCH_RESULT = 3;

	public final static List<String> HEADERS_LIST = List.of("My Account", "My Orders", "My Affiliate Account",
			"Newsletter");
	
	public final static String  MACBOOK_PRODUCT = "macbook";
	public final static String MACBOOK_PRO = "MacBook Pro";
	
	public final static String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	public final static String REGISTER_EXCEL_SHEET_NAME = "registration_page";
}
