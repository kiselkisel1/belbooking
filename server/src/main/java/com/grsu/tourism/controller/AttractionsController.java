package com.grsu.tourism.controller;

import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.AbstractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attractions")
public class AttractionsController extends AbstractServiceController<AbstractService> {
    public AttractionsController(ServiceFactory<AbstractService> serviceFactory) {
        super(serviceFactory);
    }
}
