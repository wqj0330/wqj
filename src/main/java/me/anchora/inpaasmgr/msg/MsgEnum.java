package me.anchora.inpaasmgr.msg;

public enum MsgEnum {
    SUCCESS("000", "Success."),
    LOGIN_10001("LOGIN_10001", "Username is required!"),
    LOGIN_10002("LOGIN_10002", "Password is required!"),
    LOGIN_10003("LOGIN_10003", "ValidateCode is required!"),
    LOGIN_10004("LOGIN_10004", "ValidateCode is error!"),
    LOGIN_10005("LOGIN_10005", "User dosn't exists or password is error!"),
    LOGIN_10006("LOGIN_10006", "Could not delete yourself!"),
    LOGIN_10007("LOGIN_10007", "The old password is wrong!"),
    USER_ALL("USER_ALL", "All users"),
    USER_ACTIVE("USER_ACTIVE", "All active Users"),
    APP_NUM("APP_NUM", "App num"),

  //添加
    BALANCE_ALL("BALANCE_ALL","All balances"),
    BASE_10001("BASE_10001", "paas_admin_pwd has not been configured in system config!"),
    BASE_10002("BASE_10002", "PaaS admin Login error!"),
    
    OVERVIEW_10001("OVERVIEW_10001", "mem_all has not been setted in system config!"),
    
    APPS_10001("APPS_10001", "App {0} dos not exists!"),
    SPACES_10001("SPACES_10001", "Space {0} dos not exists!"),
    SPACES_10002("SPACES_10002", "Space's guid is required!"),
    ORGANIZATIONS_10001("ORGANIZATIONS_10001", "Organization {0} dos not exists!"),
    ORGANIZATIONS_10002("ORGANIZATIONS_10002", "Organization's guid is required!"),
    ORGANIZATIONS_10003("ORGANIZATIONS_10003", "Organization's id is required!"),
    ACCESSTOKENS_10001("ACCESSTOKENS_10001", "User {0}'s accesstoken dos not exists!"),
    QUOTADEFINITIONS_10001("QUOTADEFINITIONS_10001", "QuotaDefinition {0} dos not exists!"),
    QUOTADEFINITIONS_10002("QUOTADEFINITIONS_10002", "QuotaDefinition's id is required!"),

    USERS_10001("USERS_10001", "User {0} has already exists!"),
    USERS_10002("USERS_10002", "User's email is required!"),
    USERS_10003("USERS_10003", "User's password is required!"),
    USERS_10004("USERS_10004", "User's guid is required!"),
    USERS_10005("USERS_10005", "Can not delete super admin!"),
    USERS_10006("USERS_10006", "User's id is required!"),
    
    MONIT_HOST_10001("MONIT_HOST_10001", "Host IP is required!"),
    MONIT_HOST_10002("MONIT_HOST_10002", "host_user has not been configured in system config!"),
    MONIT_HOST_10003("MONIT_HOST_10003", "host_pwd has not been configured in system config!"),
    MONIT_HOST_10004("MONIT_HOST_10004", "Failed to authenticate. IP: {0}"),
    MONIT_HOST_10005("MONIT_HOST_10005", "host_log_pair has not been configured in system config!"),
    MONIT_HOST_10006("MONIT_HOST_10006", "host_log_pair format error! Format is host desc1:log name1;desc2:log name2"),
    MONIT_HOST_10007("MONIT_HOST_10007", "Host log path has not been configured! Host desc: {0}"),
    MONIT_HOST_10008("MONIT_HOST_10008", "Host desc is required!"),

    INPAAS_10001("INPAAS_10001", "App's guid is required!"),
    
    APPCONFIGS_10001("APPCONFIGS_10001", "Can not find app configs by guid {0}!"),
   
    LOGIN("0002", "Please Login!"),
   
    SYS_ERROR("0001", "System Error!");

    private final String code;
    private final String description;

    private MsgEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
    
    public static MsgEnum getByCode(String code) {
        for (MsgEnum cacheCode : values()) {
            if (cacheCode.getCode().equals(code)) {
                return cacheCode;
            }
        }
        return null;
    }
}
