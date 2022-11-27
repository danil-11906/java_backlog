package service;


import dao.BlockDao;
import dao.jdbc.BlockDaoImpl;
import model.BlockModel;

import java.sql.Connection;

public class BlockService {
    private BlockDao blockDao;

    public BlockService() {
        Connection connection = ConnectionManager.getConnection();
        blockDao = new BlockDaoImpl(connection);
    }

    public BlockModel get(String hash) {
        blockDao.getBlock(hash);
        return null;
    }

    public void create(BlockModel blockModel) {
        blockDao.create(blockModel);
    }

    public void delete(String hash) {
        blockDao.delete(hash);
    }
}
