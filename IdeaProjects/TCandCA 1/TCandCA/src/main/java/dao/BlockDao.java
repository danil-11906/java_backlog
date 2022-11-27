package dao;

import model.BlockModel;

public interface BlockDao {
    BlockModel getBlock(String hash);

    void create(BlockModel block);

    void delete(String hash);
}
