package com.andrypa.main;

import com.andrypa.database.PulsaBintangDatabase;
import com.andrypa.entity.Pelanggan;
import com.andrypa.error.PelangganException;
import com.andrypa.service.PelangganDao;
import com.andrypa.view.MainViewPelanggan;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class ProjectJavaMVC {

    public static void main(String[] args) throws SQLException, PelangganException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainViewPelanggan pelanggan = new MainViewPelanggan();
                    pelanggan.loadDatabase();
                    pelanggan.setVisible(true);
                } catch (SQLException ex) {
                } catch (PelangganException ex) {
                }

            }
        });
    }
}
