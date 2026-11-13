package com.krx.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.krx.config.MyBatisConfig;
import com.krx.dto.EtfDTO;

public class EtfDAOImpl implements EtfDAO {
    private final SqlSessionFactory factory = MyBatisConfig.getSqlSessionFactory();

    @Override
    public List<EtfDTO> selectEtfList() {
        try (SqlSession session = factory.openSession()) {
            return session.selectList("EtfMapper.selectEtfList");
        }
    }

    @Override
    public EtfDTO selectEtfById(long id) {
        try (SqlSession session = factory.openSession()) {
            return session.selectOne("EtfMapper.selectEtfById", id);
        }
    }

    @Override
    public int insertEtf(EtfDTO etf) {
        try (SqlSession session = factory.openSession()) {
            int result = session.insert("EtfMapper.insertEtf", etf);
            session.commit();
            return result;
        }
    }

    @Override
    public int updateEtf(EtfDTO etf) {
        try (SqlSession session = factory.openSession()) {
            int result = session.update("EtfMapper.updateEtf", etf);
            session.commit();
            return result;
        }
    }

    @Override
    public int deleteEtf(long id) {
        try (SqlSession session = factory.openSession()) {
            int result = session.delete("EtfMapper.deleteEtf", id);
            session.commit();
            return result;
        }
    }
}
