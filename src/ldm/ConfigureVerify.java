package ldm;

import blufi.espressif.params.BlufiConfigureParams;
import blufi.espressif.params.BlufiParameter;

public class ConfigureVerify {
    private static final int OP_MODE_POS_STA = 0;
    private static final int OP_MODE_POS_SOFTAP = 1;
    private static final int OP_MODE_POS_STASOFTAP = 2;

    private static final int[] OP_MODE_VALUES = {
            BlufiParameter.OP_MODE_STA,
            BlufiParameter.OP_MODE_SOFTAP,
            BlufiParameter.OP_MODE_STASOFTAP
    };
    private static final int[] SOFTAP_SECURITY_VALUES = {
            BlufiParameter.SOFTAP_SECURITY_OPEN,
//            BlufiParameter.SOFTAP_SECURITY_WEP,
            BlufiParameter.SOFTAP_SECURITY_WPA,
            BlufiParameter.SOFTAP_SECURITY_WPA2,
            BlufiParameter.SOFTAP_SECURITY_WPA_WPA2
    };

    private BlufiConfigureParams checkInfo(BlufiConfigureParams params) {
//        BlufiConfigureParams params = new BlufiConfigureParams();
        int deviceMode = OP_MODE_VALUES[0];
        params.setOpMode(deviceMode);
        switch (deviceMode) {
            case BlufiParameter.OP_MODE_NULL:
                return params;
            case BlufiParameter.OP_MODE_STA:
                if (checkSta(params)) {
                    return params;
                } else {
                    return null;
                }
            case BlufiParameter.OP_MODE_SOFTAP:
                if (checkSoftAP(params)) {
                    return params;
                } else {
                    return null;
                }
            case BlufiParameter.OP_MODE_STASOFTAP:
                if (checkSoftAP(params) && checkSta(params)) {
                    return params;
                } else {
                    return null;
                }
        }

        return null;
    }

    private boolean checkSta(BlufiConfigureParams params) {
//        String ssid = mStationSsidET.getText().toString();
//        if (TextUtils.isEmpty(ssid)) {
//            mStationSsidET.setError(getString(R.string.configure_station_ssid_error));
//            return false;
//        }
//        byte[] ssidBytes = (byte[]) mStationSsidET.getTag();
//      params.setStaSSIDBytes(ssidBytes != null ? ssidBytes : ssid.getBytes());
    	String ssid = "CarrierPigeon";
    	params.setStaSSIDBytes(ssid.getBytes());
    	String password = "5FEC1890DF";
//        String password = mStationPasswordET.getText().toString();
        params.setStaPassword(password);

//        int freq = -1;
//        if (ssid.equals(getConnectionSSID())) {
//            freq = getConnectionFrequncy();
//        }
//        if (freq == -1) {
//            for (ScanResult sr : mWifiList) {
//                if (ssid.equals(sr.SSID)) {
//                    freq = sr.frequency;
//                    break;
//                }
//            }
//        }
//        if (is5GHz(freq)) {
//            mStationSsidET.setError(getString(R.string.configure_station_wifi_5g_error));
//            new AlertDialog.Builder(this)
//                    .setMessage(R.string.configure_station_wifi_5g_dialog_message)
//                    .setPositiveButton(R.string.configure_station_wifi_5g_dialog_continue, (dialog, which) -> {
//                        finishWithParams(params);
//                    })
//                    .setNegativeButton(R.string.configure_station_wifi_5g_dialog_cancel, null)
//                    .show();
//            return false;
//        }

        return true;
    }

    public boolean checkSoftAP(BlufiConfigureParams params) {
//        String ssid = mSoftAPSsidET.getText().toString();
    	String ssid = "CarrierPigeon";
        params.setSoftAPSSID(ssid);
//        String password = mSoftAPPAsswordET.getText().toString();
    	String password = "5FEC1890DF";
        params.setSoftAPPAssword(password);
//        int channel = mSoftAPChannelSp.getSelectedItemPosition();
        int channel = 7;
        params.setSoftAPChannel(channel);
//        int maxConnection = mSoftAPMaxConnectionSp.getSelectedItemPosition();
        int maxConnection = 10;
        params.setSoftAPMaxConnection(maxConnection);

//        int security = SOFTAP_SECURITY_VALUES[mSoftapSecuritSP.getSelectedItemPosition()];
        int security = SOFTAP_SECURITY_VALUES[0];
        params.setSoftAPSecurity(security);
        switch (security) {
            case BlufiParameter.SOFTAP_SECURITY_OPEN:
                return true;
            case BlufiParameter.SOFTAP_SECURITY_WEP:
            case BlufiParameter.SOFTAP_SECURITY_WPA:
            case BlufiParameter.SOFTAP_SECURITY_WPA2:
            case BlufiParameter.SOFTAP_SECURITY_WPA_WPA2:
//                if (TextUtils.isEmpty(password) || password.length() < 8) {
//                    mSoftAPPAsswordET.setError(getString(R.string.configure_softap_password_error));
//                    return false;
//                }

                return true;
        }

        return false;
    }
}
