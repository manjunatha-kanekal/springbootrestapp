package com.postgresql.springbootrestapp.id;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCodeGenerator implements IdentifierGenerator {
    private final String DEFAULT_SEQUENCE_NAME = "emp_seq";
    private static final Logger logger = LoggerFactory.getLogger(EmployeeCodeGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String prefix = "CF";
        try {
            Connection connection = sharedSessionContractImplementor.connection();
            PreparedStatement ps = connection.prepareStatement("SELECT nextval ('"+DEFAULT_SEQUENCE_NAME+"') as nextval");

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("nextval");
                String suffix = String.format("%03d", id);
                String code = prefix.concat(suffix);
                logger.debug("Generated Employee Code: " + code);
                return code;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new HibernateException("Unable to generate Employee Code Sequence");
        }
        return null;
    }
}
