package com.andrypa.controller;

import com.andrypa.model.PelangganModel;
import com.andrypa.view.PelangganView;
import javax.swing.JOptionPane;

public class PelangganController {

    private PelangganModel model;

    public void setModel(PelangganModel model) {
        this.model = model;
    }

    public void resetPelanggan(PelangganView view) {
        model.resetPelanggan();
    }

    public void insertPelanggan(PelangganView view) {
        String nama = view.getTxtNama().getText();
        String alamat = view.getTxtAlamat().getText();
        String telepon = view.getTxtTelepon().getText();
        String jumlah = view.getTxtJumlah().getText();

        if (nama.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Nama Masih Kosong");
        } else if (nama.length() > 255) {
            JOptionPane.showMessageDialog(view, "Nama tidak boleh lebih dari 255");
        } else if (alamat.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Alamat Masih Kosong");
        } else if (telepon.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Telepon Masih Kosong");
        } else if (!telepon.matches("[0-9]*")) {
            JOptionPane.showMessageDialog(view, "Telepon hanya boleh diisi oleh angka");
        } else if (telepon.length() != 12) {
            JOptionPane.showMessageDialog(view, "Panjang telepon harus berjumlah 12 digit");
        } else if (jumlah.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Jumlah Pulsa Masih Kosong");
        } else if (Integer.parseInt(jumlah) > 100000 || Integer.parseInt(jumlah) < 0) {
            JOptionPane.showMessageDialog(view, "Pulsa maksimal 100.000");
        } else {
            model.setNama(nama);
            model.setAlamat(alamat);
            model.setTelepon(telepon);
            model.setJumlah(jumlah);
            try {
                model.insertPelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan Berhasil Ditambahkan");
                model.resetPelanggan();
            } catch (Throwable throwable) {
                JOptionPane.showMessageDialog(view, new Object[]{
                    "Terjadi error di database dengan pesan ", throwable.getMessage()
                });
            }
        }
    }

    public void updatePelanggan(PelangganView view) {

        //jika tidak ada yang diseleksi kasih peringatan
        if (view.getTable().getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Silahkan Seleksi baris data yang akan diubah");
            return;
        }

        Integer id = Integer.parseInt(view.getTxtId().getText());
        String nama = view.getTxtNama().getText();
        String alamat = view.getTxtAlamat().getText();
        String telepon = view.getTxtTelepon().getText();
        String jumlah = view.getTxtJumlah().getText();

        if (nama.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Nama Masih Kosong");
        } else if (nama.length() > 255) {
            JOptionPane.showMessageDialog(view, "Nama tidak boleh lebih dari 255");
        } else if (alamat.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Alamat Masih Kosong");
        } else if (telepon.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Telepon Masih Kosong");
        } else if (telepon.length() > 12) {
            JOptionPane.showMessageDialog(view, "Panjang telepon tak boleh lebih dari 12 digit");
        } else if (jumlah.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Jumlah Pulsa Masih Kosong");
        } else if (Integer.parseInt(jumlah) > 100000 || Integer.parseInt(jumlah) < 0) {
            JOptionPane.showMessageDialog(view, "Pulsa maksimal 100.000");
        } else {
            model.setId(id);
            model.setNama(nama);
            model.setAlamat(alamat);
            model.setTelepon(telepon);
            model.setJumlah(jumlah);
            try {
                model.updatePelanggan();
                JOptionPane.showMessageDialog(view, "Data Pelanggan Berhasil Di Ubah");
                model.resetPelanggan();
            } catch (Throwable throwable) {
                JOptionPane.showMessageDialog(view, new Object[]{
                    "Terjadi error di database dengan pesan ", throwable.getMessage()
                });
            }
        }
    }

    public void deletePelanggan(PelangganView view) {

        //jika tidak ada yang diseleksi kasih peringatan
        if (view.getTable().getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Silahkan Seleksi baris data yang akan dihapus");
            return;
        }

        if (JOptionPane.showConfirmDialog(view, "Anda yakin akan menghapus?") == JOptionPane.OK_OPTION) {

            Integer id = Integer.parseInt(view.getTxtId().getText());
            model.setId(id);

            try {
                model.deletePelanggan();
                JOptionPane.showMessageDialog(view, "Data Pelanggan Berhasil Di Hapus");
                model.resetPelanggan();
            } catch (Throwable throwable) {
                JOptionPane.showMessageDialog(view, new Object[]{
                    "Terjadi error di database dengan pesan ", throwable.getMessage()
                });
            }
        }
    }
}
