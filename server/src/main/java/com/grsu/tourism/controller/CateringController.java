package com.grsu.tourism.controller;

import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.Catering;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catering")
public class CateringController extends AbstractServiceController<Catering> {
    public CateringController(ServiceFactory<Catering> serviceFactory) {
        super(serviceFactory);
    }
}
