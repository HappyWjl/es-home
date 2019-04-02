package com.es.stone.enums;

public enum EsStatus {

	SUCESS("SUCESS",0),
	PARAM_ERROR("PARAM_ERROR",1);
	
	private String desc;
    private int type;
    
	private EsStatus(String desc,int type){
		this.desc = desc;
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public int getType() {
		return type;
	}

	public static EsStatus getByType(int type) {
		for(EsStatus userType : values()) {
			if(userType.getType() == type) {
				return userType;
			}
		}
		return null;
	}
	
}
