package com.grsu.tourism.controller;

import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.Transport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transport")
public class TransportController extends AbstractServiceController<Transport> {
    public TransportController(ServiceFactory<Transport> serviceFactory) {
        super(serviceFactory);
    }
}
