package com.seven.lottery.doamin.award.model.vo;

/**
 * @ClassName: ShippingAddress
 * @Description: 实物商品送货四级地址
 * @Author: seven
 * @CreateTime: 2023-03-17 15:35
 * @Version: 1.0
 **/

public class ShippingAddress {
    //收货人
    private String name;

    //一级地址ID
    private String provinceId;
    //一级地址名称
    private String provinceName;

    //二级地址ID
    private String cityId;
    //二级地址名称
    private String cityName;

    //三级地址ID
    private String countyId;
    //三级地址名称
    private String countryName;

    //四级地址ID
    private String townId;
    //四级地址名称
    private String townName;

    //详细地址
    private String address;

    //手机号
    private String phone;
    //邮箱
    private String email;
    //备注
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
