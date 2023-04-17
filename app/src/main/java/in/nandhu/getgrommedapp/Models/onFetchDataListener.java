package in.nandhu.getgrommedapp.Models;

import java.util.ArrayList;

import in.nandhu.getgrommedapp.Models.MakeUpApiProductResponse;

public interface onFetchDataListener {
    void onFetchData(ArrayList<MakeUpApiProductResponse> responseArrayList,String message);
    void onError(String message);
}
