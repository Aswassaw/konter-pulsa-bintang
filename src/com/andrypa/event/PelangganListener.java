package com.andrypa.event;

import com.andrypa.entity.Pelanggan;
import com.andrypa.model.PelangganModel;

public interface PelangganListener {

    public void onChange(PelangganModel model);

    public void onInsert(Pelanggan pelanggan);

    public void onUpdate(Pelanggan pelanggan);

    public void onDelete();
}
