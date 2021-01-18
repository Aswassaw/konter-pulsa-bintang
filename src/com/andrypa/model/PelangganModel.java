package com.andrypa.model;

import com.andrypa.event.PelangganListener;
import java.sql.SQLException;
import com.andrypa.database.PulsaBintangDatabase;
import com.andrypa.entity.Pelanggan;
import com.andrypa.error.PelangganException;
import com.andrypa.event.PelangganListener;
import com.andrypa.service.PelangganDao;
import com.andrypa.view.PelangganView;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PelangganModel {

    private int id;
    private String nama;
    private String alamat;
    private String telepon;
    private String jumlah;
    private PelangganListener listener;

    public PelangganListener getListener() {
        return listener;
    }

    public void setListener(PelangganListener listener) {
        this.listener = listener;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        fireOnChange();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
        fireOnChange();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
        fireOnChange();
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
        fireOnChange();
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
        fireOnChange();
    }

    protected void fireOnChange() {
        if (listener != null) {
            listener.onChange(this);
        }
    }

    protected void fireOnInsert(Pelanggan pelanggan) {
        if (listener != null) {
            listener.onInsert(pelanggan);
        }
    }

    protected void fireOnUpdate(Pelanggan pelanggan) {
        if (listener != null) {
            listener.onUpdate(pelanggan);
        }
    }

    protected void fireOnDelete() {
        if (listener != null) {
            listener.onDelete();
        }
    }

    public void insertPelanggan() throws SQLException, PelangganException {
        PelangganDao dao = PulsaBintangDatabase.getPelangganDao();

        Pelanggan pelanggan = new Pelanggan();
        pelanggan.setNama(nama);
        pelanggan.setAlamat(alamat);
        pelanggan.setTelepon(telepon);
        pelanggan.setJumlah(Integer.parseInt(jumlah));

        dao.insertPelanggan(pelanggan);
        fireOnInsert(pelanggan);
    }

    public void updatePelanggan() throws SQLException, PelangganException {
        PelangganDao dao = PulsaBintangDatabase.getPelangganDao();

        Pelanggan pelanggan = new Pelanggan();
        pelanggan.setNama(nama);
        pelanggan.setAlamat(alamat);
        pelanggan.setTelepon(telepon);
        pelanggan.setJumlah(Integer.parseInt(jumlah));
        pelanggan.setId(id);

        dao.updatePelanggan(pelanggan);
        fireOnUpdate(pelanggan);
    }

    public void deletePelanggan() throws SQLException, PelangganException {
        PelangganDao dao = PulsaBintangDatabase.getPelangganDao();
        dao.deletePelanggan(id);
        fireOnDelete();
    }

    public void resetPelanggan() {
        setId(0);
        setNama("");
        setAlamat("");
        setTelepon("");
        setJumlah("");
    }
}
