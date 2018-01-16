package com.example.ruben.hoteissines;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ruben.hoteissines.model.HoteisData;
import com.example.ruben.hoteissines.model.Hotel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHoteis extends ListFragment {
    private static final String fakeResponse = "[\n" +
            "   {\n" +
            "       \"Name\":\"Dom Vasco\",\n" +
            "       \"Description\":\"Escolher um Hotel não se resume à simples escolha de um quarto para dormir. A experiência de ficar, e viver o ambiente que o rodeia, vai muito além das quatro paredes. Logo, da excelência do serviço, à atenção dedicada a cada pormenor, o Hotel Dom Vasco eleva cada estadia a uma experiência única. Acima de tudo, partilhe este modo de estar.\",\n" +
            "       \"PhoneNumber\":\"269 630 960\",\n" +
            "       \"Email\":\"hotel@domvasco.com\",\n" +
            "       \"Location\":\"37.957798, -8.8753941\",\n" +
            "       \"Image\":\"https://t-ec.bstatic.com/images/hotel/max1024x768/279/27906370.jpg\"\n" +
            "   },\n" +
            "   {\n" +
            "       \"Name\":\"Hotel Apartamento Sinerama\",\n" +
            "       \"Description\":\"Este alojamento fica a 5 minutos a pé da praia. Localizado no centro de Sines, o Hotel Apartamento Sinerama dispõe de quartos e apartamentos auto-suficientes, alguns com vistas panorâmicas para o mar. O pequeno-almoço gratuito inclui sumos de frutas, ovos, bolo, pão e queijo.\",\n" +
            "       \"PhoneNumber\":\"269 000 100\",\n" +
            "       \"Email\":\"sinerama@gmail.com\",\n" +
            "       \"Location\":\"37.956041, -8.869475\",\n" +
            "       \"Image\":\"https://t-ec.bstatic.com/images/hotel/max1024x768/245/24518691.jpg\"\n" +
            "   },\n" +
            "   {\n" +
            "       \"Name\":\"Hotel Veleiro\",\n" +
            "       \"Description\":\"O Hotel Veleiro beneficia de uma localização privilegiada com vista para a Baía de Sines. Disponibiliza quartos com 1 cama de casal ou com 2 camas individuais, uma casa de banho privada e acesso Wi-Fi gratuito em todas as áreas. A Praia Vasco da Gama encontra-se a uma caminhada de 2 minutos.\",\n" +
            "       \"PhoneNumber\":\"269 634 751\",\n" +
            "       \"Email\":\"veleiro@iol.pt\",\n" +
            "       \"Location\":\"37.9547622, -8.8683794\",\n" +
            "       \"Image\":\"https://aff.bstatic.com/images/hotel/840x460/499/49986191.jpg\"\n" +
            "   }\n" +
            "]";


    private HoteisAdapter adapter;

    public FragmentHoteis() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_expandable_list_item_1, HoteisData.Hoteis);
        this.adapter = new HoteisAdapter(getActivity(), HoteisData.getHotels());
        setListAdapter(this.adapter);

        new FetchHoteisAsyncTask().execute();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if (getActivity().findViewById(R.id.fragment_container) !=null){

            FragmentDescricao newFragment = new FragmentDescricao();
            Bundle args = new Bundle();
            args.putInt("position", position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else {
            Hotel hotel = HoteisData.getHotels().get(position);
            TextView descricaoTextView = (TextView) getActivity().findViewById(R.id.descricao_textview);
            descricaoTextView.setText(hotel.getDescription());
        }
    }

    private class FetchHoteisAsyncTask extends AsyncTask<Void, Void, List<Hotel>> {

        @Override
        protected List<Hotel> doInBackground(Void... voids) {
            List<Hotel> hotels = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(fakeResponse);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("Name");
                    String description = jsonObject.optString("Description");
                    String phoneNumber = jsonObject.getString("PhoneNumber");
                    String email = jsonObject.optString("Email");
                    String location = jsonObject.optString("Location");
                    String image = jsonObject.optString("Image");

                    Hotel hotel = new Hotel(name, description, phoneNumber, email, location, image);
                    hotels.add(hotel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return hotels;
        }

        @Override
        protected void onPostExecute(List<Hotel> hotels) {
            HoteisData.setHotels(hotels);
            FragmentHoteis.this.adapter.updateList(HoteisData.getHotels());
        }
    }
}
