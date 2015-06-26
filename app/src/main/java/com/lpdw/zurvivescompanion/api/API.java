package com.lpdw.zurvivescompanion.api;

import com.lpdw.zurvivescompanion.response.EquipmentsReponse;

import retrofit.http.GET;

/**
 * Created by CAJUSTE Alain on 15/06/2015.
 */
public interface API {
    @GET("/api/equipments")
    EquipmentsReponse getEquipments();
}
