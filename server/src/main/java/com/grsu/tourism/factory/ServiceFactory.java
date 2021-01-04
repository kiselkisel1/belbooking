package com.grsu.tourism.factory;

import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.model.AbstractService;
import com.grsu.tourism.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ServiceFactory<S extends AbstractService> {

    @Autowired
    private List<GenericService<S>> services;

    private final Map<ServiceType, GenericService<S>> myServiceCache = new HashMap<>();

    @PostConstruct
    public void initMyServiceCache() {
        for (GenericService<S> service : services) {
            myServiceCache.put(service.getType(), service);
        }
    }

    public GenericService<S> getService(String type) {
        ServiceType serviceType = ServiceType.getByNameIgnoreCaseOrElseThrow(type);
        GenericService<S> service = myServiceCache.get(serviceType);
        if (service == null) throw new RuntimeException("Unknown service type: " + type);
        return service;
    }

    public GenericService<S> getServiceByType(ServiceType type) {
        GenericService<S> service = myServiceCache.get(type);
        if (service == null) throw new RuntimeException("Unknown service type: " + type);
        return service;
    }
}