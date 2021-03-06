package com.jd.bdp.hydra.dubbomonitor.provider.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jd.bdp.hydra.dubbomonitor.LeaderService;
import com.jd.bdp.hydra.mysql.persistent.entity.ServicePara;
import com.jd.bdp.hydra.mysql.persistent.service.AppService;
import com.jd.bdp.hydra.mysql.persistent.service.SeedService;
import com.jd.bdp.hydra.mysql.persistent.service.ServiceService;

/**
 * User: biandi
 * Date: 13-4-7
 * Time: 下午3:02
 */
public class LeaderServiceImpl implements LeaderService {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Map<String, String> registerClient(String name, List<String> services) {
        long startTime=System.currentTimeMillis();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("seed", seedService.getSeed().toString());
        map.put(name, appService.getAppId(name).toString());
        for (String serviceName : services) {
            map.put("serviceName", serviceService.getServiceId(serviceName, name).toString());
        }
        
        List<ServicePara> srvParmList = serviceService.get(appService.getAppId(name));
        
        if(srvParmList!=null && !srvParmList.isEmpty()){
        	for(ServicePara servicePara : srvParmList){
        		map.put(servicePara.getName(), servicePara.getId());
        	}
        }
        
        return map;
    }

    @Override
    public String registerClient(String name, String service) {
        return serviceService.getServiceId(service, name).toString();
    }


    private ServiceService serviceService;
    private SeedService seedService;
    private AppService appService;

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public void setSeedService(SeedService seedService) {
        this.seedService = seedService;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }
}
