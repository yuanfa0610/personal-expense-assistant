package com.eftech.pa.pea.impl.dao.hibernate;

import com.eftech.pa.pea.impl.dao.UserDao;
import com.eftech.pa.pea.impl.persistent.User;
import org.springframework.stereotype.Component;

@Component
public class HibernateUserDaoImpl extends HibernateGenericDao<User, String> implements UserDao {

    // This class is intentionally left blank
}
