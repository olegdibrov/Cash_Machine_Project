package com.training.service;

import com.training.service.impl.BillService;
import com.training.service.impl.PaymentService;
import com.training.service.impl.ProductService;
import com.training.service.impl.UserService;
import com.training.util.constants.ServiceKey;

import java.util.HashMap;
import java.util.Map;

import static com.training.util.constants.ServiceKey.*;

/**
 * Class contains instances of service classes
 *
 * @author Oleg Dibrov
 */
public class ServiceFactory {
    /**
     * map for instances
     */
    private static Map<ServiceKey, Service> services;

    static {
        services = new HashMap<>();
        services.put(USER_SERVICE, new UserService());
        services.put(PRODUCT_SERVICE, new ProductService());
        services.put(BILL_SERVICE, new BillService());
        services.put(PAYMENT_SERVICE, new PaymentService());
    }

    /**
     * defines service
     *
     * @param name service identifier
     * @return service entity
     */
    public static Service getService(ServiceKey name) {
        return services.get(name);
    }
}
