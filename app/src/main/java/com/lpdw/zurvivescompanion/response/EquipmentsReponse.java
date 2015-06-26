package com.lpdw.zurvivescompanion.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by CAJUSTE Alain on 26/06/2015.
 */
public class EquipmentsReponse {

    @SerializedName("equipments")
    private ArrayList<Equipments> equipments;

    public ArrayList<Equipments> getEquipments() {
        return equipments;
    }

    public class Equipments {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("description")
        private String description;
        @SerializedName("actable_id")
        private int actableId;
        @SerializedName("actable_type")
        private String actableType;
        @SerializedName("special_ability_id")
        private String specialAbilityId;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getActableId() {
            return actableId;
        }

        public String getActableType() {
            return actableType;
        }

        public String getSpecialAbilityId() {
            return specialAbilityId;
        }
    }
}
