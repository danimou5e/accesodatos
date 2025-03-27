package mx.gob.imss.cit.sci.mssci.accesodatos.enums;

public enum SCIResponseCode {
	OK(1),
	ERROR(2),
	NO_EXIST(3);
	
	private int code;
	
	
	SCIResponseCode(int code){
		this.code = code;

	}

	public int getCode() {
		return code;
		
	}
	
	
	

}
