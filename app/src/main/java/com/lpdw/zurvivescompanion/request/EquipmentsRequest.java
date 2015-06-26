package com.lpdw.zurvivescompanion.request;

import com.lpdw.zurvivescompanion.api.API;
import com.lpdw.zurvivescompanion.response.EquipmentsReponse;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by CAJUSTE Alain on 26/06/2015.
 */
public class EquipmentsRequest extends RetrofitSpiceRequest<EquipmentsReponse, API> {


    public EquipmentsRequest() {
        super(EquipmentsReponse.class, API.class);
    }

    @Override
    public EquipmentsReponse loadDataFromNetwork() {
        return getService().getEquipments();
    }

}