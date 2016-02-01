package com.oosbt.majiang.model;

/**
 * Created by smldr on 1/31/2016.
 */
public enum Sex {

    M, F;

    public static Sex getEnum(String sexName) {

        if ("male".equalsIgnoreCase(sexName)) {
            return M;
        } else if ("m".equalsIgnoreCase(sexName)) {
            return M;
        } else if ("f".equalsIgnoreCase(sexName)) {
            return M;
        } else if ("female".equalsIgnoreCase(sexName)) {
            return M;
        } else {
            return null;
        }
    }
}
