package com.lpdw.zurvivescompanion.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.lpdw.zurvivescompanion.R;
import com.lpdw.zurvivescompanion.activity.MainActivity;
import com.lpdw.zurvivescompanion.request.EquipmentsRequest;
import com.lpdw.zurvivescompanion.request.RegisterRequest;
import com.lpdw.zurvivescompanion.response.EquipmentsReponse;
import com.lpdw.zurvivescompanion.response.UserRegisterResponse;
import com.lpdw.zurvivescompanion.views.adapters.EquipmentsRecyclerViewAdapter;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import retrofit.RetrofitError;

/**
 * Created by CAJUSTE Alain on 26/06/2015.
 */
public class EquipmentsFragment  extends BaseFragment {

    private RecyclerView fragmentEquipmentsList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public EquipmentsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_equipments, container, false);
        initView(mView);
        return mView;
    }

    public void initView(View mView) {
        fragmentEquipmentsList = (RecyclerView) mView.findViewById(R.id.fragment_equipments_list);
        fragmentEquipmentsList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        fragmentEquipmentsList.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        EquipmentsRequest request = new EquipmentsRequest();
        getSpiceManager().execute(request,
                "message_cache",
                DurationInMillis.ALWAYS_EXPIRED, new RequestListener<EquipmentsReponse>() {
                    @Override
                    public void onRequestFailure(SpiceException spiceException) {
                        String message = "Failed";
                        /*if (spiceException.getCause() != null && ((RetrofitError) spiceException.getCause()).getBody() != null) {
                            message = ((UserRegisterResponse) ((RetrofitError) spiceException.getCause()).getBody()).getErrors().getFullMessages()[0];
                        } else if (spiceException.getCause() != null) {
                            message = ((RetrofitError) spiceException.getCause()).getMessage();

                        }*/
                        requestFailure(message);
                    }

                    @Override
                    public void onRequestSuccess(EquipmentsReponse equipmentsReponse) {
                        Log.v("onRequestSuccess","equipmentsReponse :"+equipmentsReponse.getEquipments().size());
                        mAdapter = new EquipmentsRecyclerViewAdapter(getActivity(), equipmentsReponse.getEquipments());
                        fragmentEquipmentsList.setAdapter(mAdapter);
                    }
                });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public Boolean onBackPressed() {
        MainActivity.updateContent(1);
        return true;
    }

}
