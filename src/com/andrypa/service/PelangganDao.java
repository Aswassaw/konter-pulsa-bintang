package com.andrypa.service;

import com.andrypa.entity.Pelanggan;
import com.andrypa.error.PelangganException;
import java.util.List;

public interface PelangganDao {

    public void insertPelanggan(Pelanggan pelanggan) throws PelangganException;

    public void updatePelanggan(Pelanggan pelanggan) throws PelangganException;

    public void deletePelanggan(Integer id) throws PelangganException;

    public Pelanggan getPelanggan(Integer id) throws PelangganException;

    public List<Pelanggan> selectAllPelanggan() throws PelangganException;
}
