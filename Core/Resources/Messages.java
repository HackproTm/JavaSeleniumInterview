package com.hackpro.TestCore.Resources;

public final class Messages {

	private String _ErrorParameterIsNull = "Parameter '{0}' can't be null."
	
	private Messages() {}

	public static String ErrorParameterIsNull(String parameterName) {
		return String.format(_ErrorParameterIsNull, parameterName) ;
    }
}
