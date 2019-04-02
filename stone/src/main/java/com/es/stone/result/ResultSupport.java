package com.es.stone.result;

import java.io.Serializable;

/**
 * Created by 海浩 on 2015/3/29.
 */
public class ResultSupport implements Serializable {

    private static final long serialVersionUID = -2235152751651905167L;

    private boolean success = true;
    private String errorMsg;
    private int errorCode;

    public ResultSupport() {

    }

    public ResultSupport(String errorMsg, int errorCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setError(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.success = false;
    }

    public void setError(BaseErrorCode baseErrorCode) {
        if (baseErrorCode == null) {
            return;
        }
        this.errorCode = baseErrorCode.getErrorCode();
        this.errorMsg = baseErrorCode.getErrorMsg();
        this.success = false;
    }

}
