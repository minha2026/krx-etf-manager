package com.krx.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.krx.config.MyBatisConfig;
import com.krx.dto.EtfDTO;

public class EtfDAOImpl implements EtfDAO {
    private static final String NS = "com.krx.mapper.EtfMapper.";

    public List<EtfDTO> selectAll() {
        try (SqlSession s = MyBatisConfig.getFactory().openSession()) {
            return s.selectList(NS + "selectAll");
        }
    }

    public EtfDTO selectById(long id) {
        try (SqlSession s = MyBatisConfig.getFactory().openSession()) {
            return s.selectOne(NS + "selectById", id);
        }
    }

    public int insert(EtfDTO d) {
        try (SqlSession s = MyBatisConfig.getFactory().openSession()) {
            int n = s.insert(NS + "insert", d);
            s.commit();
            return n;
        }
    }

    public int update(EtfDTO d) {
        try (SqlSession s = MyBatisConfig.getFactory().openSession()) {
            int n = s.update(NS + "update", d);
            s.commit();
            return n;
        }
    }
}