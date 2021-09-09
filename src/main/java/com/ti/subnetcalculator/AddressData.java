package com.ti.subnetcalculator;

public class AddressData {
    private String ipAddress;
    private String subnetMask;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    public String getAddressWithMask(){
        return getIpAddress() + "/" + getSubnetMask();
    }
}
