package com.cg.ems.exception;

public class EmployeeException extends Exception {

	private static final long serialVersionUID = -8477522578847413221L;

	public EmployeeException() {
		super();
	}

	public EmployeeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public EmployeeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EmployeeException(String msg) {
		super(msg);
	}

	public EmployeeException(Throwable arg0) {
		super(arg0);
	}
	
}
