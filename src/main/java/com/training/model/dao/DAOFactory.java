package com.training.model.dao;

import com.training.model.dao.impl.*;
import com.training.util.constants.DAOKey;

import java.util.HashMap;
import java.util.Map;

import static com.training.util.constants.DAOKey.*;

public class DAOFactory {

    private static Map<DAOKey, AbstractDAO> daoMap;

    static {
        daoMap = new HashMap<>();
        daoMap.put(USER_DAO, new UserDAO());
        daoMap.put(USER_ROLE_DAO, new UserRoleDAO());
        daoMap.put(PRODUCT_DAO, new ProductDAO());
        daoMap.put(PAYMENTS_DAO, new PaymentDAO());
        daoMap.put(BILL_DAO, new BillDAO());
    }

    /**
     * defines dao
     *
     * @param key dao identifier
     * @return dao entity
     */
    public static AbstractDAO getDAO(DAOKey key) {
        return daoMap.get(key);
    }
}
