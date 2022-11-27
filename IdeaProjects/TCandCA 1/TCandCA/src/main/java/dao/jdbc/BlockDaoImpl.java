package dao.jdbc;

import dao.BlockDao;
import dao.RowMapper;
import model.BlockModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlockDaoImpl implements BlockDao {
    private Connection connection;

    public BlockDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<BlockModel> blockModelRowMapper = resultSet -> new BlockModel(
            resultSet.getInt("block_number"),
            resultSet.getString("prev_hash"),
            resultSet.getString("hash")
    );

    @Override
    public BlockModel getBlock(String prevHash) {
        String SQL = "SELECT * FROM block_table WHERE prev_hash =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, prevHash);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                BlockModel blockModel = null;
                if(resultSet.next()){
                    blockModel = blockModelRowMapper.mapRow(resultSet);
                }
                return blockModel;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void create(BlockModel block) {
        String SQL = "INSERT INTO block_table(block_number, prev_hash, hash) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setInt(1, block.getBlockNumber());
            preparedStatement.setString(2, block.getPrevHash());
            preparedStatement.setString(3, block.getHash());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(String hash) {

    }
}
