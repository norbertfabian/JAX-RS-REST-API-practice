package cz.fabian.practice.jaxrs.server.result;

/**
 * Created by nfabian on 24.6.16.
 */
public class ServiceResult {

    public static final String DATA_NOT_FOUND = "Service.result.entity.notFound";
    public static final String OUT_OF_RANGE = "Service.result.outOfRange";

    private String errorMsg;

    public ServiceResult(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    protected ServiceResult() {
    }

    public boolean isError() {
        return errorMsg != null;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
