package com.greywanchuang.rackmonitor.domain;

import com.alibaba.fastjson.JSONObject;
import com.greywanchuang.rackmonitor.entity.Property;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private String status;
    private String health;
    private int entemp;
    private int extemp;
    private int power;
    private int cpuamount;
    private int memoryamount;
    private int memorycapacity;
    private int diskamount;
    private int diskcapacity;
    private String bmcreset;
    private String sn;
    private String id;
    private String productname;
    private String cpu;
    private List<Net> nets = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public int getEntemp() {
        return entemp;
    }

    public void setEntemp(int entemp) {
        this.entemp = entemp;
    }

    public int getExtemp() {
        return extemp;
    }

    public void setExtemp(int extemp) {
        this.extemp = extemp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCpuamount() {
        return cpuamount;
    }

    public void setCpuamount(int cpuamount) {
        this.cpuamount = cpuamount;
    }

    public int getMemoryamount() {
        return memoryamount;
    }

    public void setMemoryamount(int memoryamount) {
        this.memoryamount = memoryamount;
    }

    public int getMemorycapacity() {
        return memorycapacity;
    }

    public void setMemorycapacity(int memorycapacity) {
        this.memorycapacity = memorycapacity;
    }

    public int getDiskamount() {
        return diskamount;
    }

    public void setDiskamount(int diskamount) {
        this.diskamount = diskamount;
    }

    public int getDiskcapacity() {
        return diskcapacity;
    }

    public void setDiskcapacity(int diskcapacity) {
        this.diskcapacity = diskcapacity;
    }

    public String getBmcreset() {
        return bmcreset;
    }

    public void setBmcreset(String bmcreset) {
        this.bmcreset = bmcreset;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public List<Net> getNets() {
        return nets;
    }

    public void setNets(List<Net> nets) {
        this.nets = nets;
    }

    public void addNet(Net net) {
        this.nets.add(net);
    }

    /**
     * 组合server对象
     *
     * @param properties
     */
    public void compose(List<Property> properties) {
        Net net = new Net();
        properties.forEach(property -> {
            if ("ID".equals(property.getName())) {
                this.setId(property.getValue());
            } else if ("ProductName".equals(property.getName())) {
                this.setProductname(property.getValue());
            } else if ("SN".equals(property.getName())) {
                this.setSn(property.getValue());
            } else if ("Status".equals(property.getName())) {
                this.setStatus(property.getValue());
            } else if ("Health".equals(property.getName())) {
                this.setHealth(property.getValue());
            } else if ("Power".equals(property.getName())) {
                this.setPower(Integer.parseInt(property.getValue()));
            } else if ("BMCReset".equals(property.getName())) {
                this.setBmcreset(property.getValue());
            } else if ("EnTemp".equals(property.getName())) {
                this.setEntemp(Integer.parseInt(property.getValue()));
            } else if ("ExTemp".equals(property.getName())) {
                this.setExtemp(Integer.parseInt(property.getValue()));
            } else if ("CPU".equals(property.getName())) {
                this.setCpu(property.getValue());
            } else if ("CPUAmount".equals(property.getName())) {
                this.setCpuamount(Integer.parseInt(property.getValue()));
            } else if ("MemoryCapacity".equals(property.getName())) {
                this.setMemorycapacity(Integer.parseInt(property.getValue()));
            } else if ("MemoryAmount".equals(property.getName())) {
                this.setMemoryamount(Integer.parseInt(property.getValue()));
            } else if ("DiskCapacity".equals(property.getName())) {
                this.setDiskcapacity(Integer.parseInt(property.getValue()));
            } else if ("DiskAmount".equals(property.getName())) {
                this.setDiskamount(Integer.parseInt(property.getValue()));
            } else if ("Ethernet".equals(property.getName())) {
                net.setName(property.getValue());
            } else if ("IPMode".equals(property.getName())) {
                net.setIpmode(property.getValue());
            } else if ("IP".equals(property.getName())) {
                net.setIp(property.getValue());
            } else if ("Netmask".equals(property.getName())) {
                net.setNetmask(property.getValue());
            } else if ("Gateway".equals(property.getName())) {
                net.setGateway(property.getValue());
            } else if ("MAC1".equals(property.getName())) {
                net.setMac(property.getValue());
            }

            this.addNet(net);
        });
    }

    /**
     * 组装指定属性
     * @param properties
     * @return
     */
    public static JSONObject conposeJson(List<Property> properties,String targetname)
    {
        JSONObject jsonObject=new JSONObject();
        properties.forEach(property -> {
            if("Status".equals(property.getName()))
            {
                jsonObject.put("status",property.getValue());
            }else if("Health".equals(property.getName()))
            {
                jsonObject.put("health",property.getValue());
            }else if("EnTemp".equals(property.getName()))
            {
                jsonObject.put("entemp",property.getValue());
            }else if("ExTemp".equals(property.getName()))
            {
                jsonObject.put("extemp",property.getValue());
            }else if("Power".equals(property.getName()))
            {
                jsonObject.put("power",property.getValue());
            }
        });
        String[] names=targetname.split("/");
        jsonObject.put("name",names[names.length-1]);
        return jsonObject;
    }
}
