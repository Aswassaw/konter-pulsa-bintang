package com.andrypa.impl;

import com.andrypa.entity.Pelanggan;
import com.andrypa.error.PelangganException;
import com.andrypa.service.PelangganDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PelangganDaoImp implements PelangganDao {

    private Connection connection;
    private final String insertPelanggan = "INSERT INTO pelanggan(nama, alamat, telepon, jumlah) VALUES (?,?,?,?)";
    private final String updatePelanggan = "UPDATE pelanggan SET nama=?, alamat=?, telepon=?, jumlah=? WHERE id=?";
    private final String deletePelanggan = "DELETE FROM pelanggan WHERE id=?";
    private final String getById = "SELECT * FROM pelanggan WHERE id = ?";
    private final String selectAll = "SELECT * FROM pelanggan";

    public PelangganDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertPelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(insertPelanggan, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setInt(4, pelanggan.getJumlah());
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                pelanggan.setId(result.getInt(1));
            }            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat contoh2 = new SimpleDateFormat("YYYY-MM-dd H:m:s");
            pelanggan.setTanggal(contoh2.format(cal.getTime()));

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                }
            }
        }
    }

    @Override
    public void updatePelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updatePelanggan);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setInt(4, pelanggan.getJumlah());
            statement.setInt(5, pelanggan.getId());
            statement.executeUpdate();
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat contoh2 = new SimpleDateFormat("YYYY-MM-dd H:m:s");
            pelanggan.setTanggal(contoh2.format(cal.getTime()));
            
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                }
            }
        }
    }

    @Override
    public void deletePelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(deletePelanggan);
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                }
            }
        }
    }

    @Override
    public Pelanggan getPelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getById);
            //indeks ke 1, isinya ID dari parameter
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            Pelanggan pelanggan = null;
            if (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("id"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamat(result.getString("alamat"));
                pelanggan.setTelepon(result.getString("telepon"));
                pelanggan.setJumlah(result.getInt("jumlah"));
                pelanggan.setTanggal(result.getString("tanggal"));
            } else {
                throw new PelangganException("Pelanggan dengan id "
                        + id + " tidak ditemukan");
            }
            connection.commit();
            return pelanggan;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                }
            }
        }
    }

    @Override
    public List<Pelanggan> selectAllPelanggan() throws PelangganException {
        Statement statement = null;
        List<Pelanggan> list = new ArrayList<Pelanggan>();
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(selectAll);
            while (result.next()) {
                Pelanggan pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("id"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamat(result.getString("alamat"));
                pelanggan.setTelepon(result.getString("telepon"));
                pelanggan.setJumlah(result.getInt("jumlah"));
                pelanggan.setTanggal(result.getString("tanggal").substring(0, result.getString("tanggal").length() - 2));

                list.add(pelanggan);
            }
            connection.commit();
            return list;
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(exception.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                }
            }
        }
    }
}
