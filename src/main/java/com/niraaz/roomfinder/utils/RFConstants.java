package com.niraaz.roomfinder.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class RFConstants {

	public static final String ROOM_TYPE_SINGLE = "Single";
	public static final String ROOM_TYPE_DOUBLE = "Double";
	public static final String ROOM_TYPE_FLAT = "Flat";
	public static final String ROOM_TYPE_APARTMENT = "Apartment";

	public static final String UPLOAD_DIRECTORY = "/home/csangharsha/Documents/projects/projects/roomfinder_uploads/";
	public static final String IMAGE_NOT_AVAILABLE = "image_not_avaiable.gif";

	public static Map<String, String> roomTypeMap = new LinkedHashMap<>( );
	static {
		roomTypeMap.put( "Single", "Single" );
		roomTypeMap.put( "Double", "Double" );
		roomTypeMap.put( "Flat", "Flat");
		roomTypeMap.put( "Apartment", "Apartment" );
	}

}
