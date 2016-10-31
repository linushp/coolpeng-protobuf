package com.coolpeng.grpc.register.api.model;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class GrpcServiceModel {
    private String serviceName;
    private String ip;
    private int port;

    /**
     * 服务状态
     */
    private GrpcServiceStatus status;// 0 ok, 999 unknown, 1 shutdown

    public GrpcServiceModel() {
    }

    public GrpcServiceModel(String serviceName, String ip, int port) {
        this.serviceName = serviceName;
        this.ip = ip;
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public GrpcServiceStatus getStatus() {
        return status;
    }

    public void setStatus(GrpcServiceStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrpcServiceModel that = (GrpcServiceModel) o;

        if (port != that.port) return false;
        if (serviceName != null ? !serviceName.equals(that.serviceName) : that.serviceName != null) return false;
        return !(ip != null ? !ip.equals(that.ip) : that.ip != null);

    }

    @Override
    public int hashCode() {
        int result = serviceName != null ? serviceName.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return "GrpcServiceModel{" +
                "serviceName='" + serviceName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
