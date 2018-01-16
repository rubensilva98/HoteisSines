package com.example.ruben.hoteissines;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruben.hoteissines.model.HoteisData;
import com.example.ruben.hoteissines.model.Hotel;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDescricao extends Fragment {

    private ImageView email = null;
    private ImageView phone = null;
    private ImageView gps = null;
    private ImageView imageHotel = null;





    public FragmentDescricao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_descricao, container, false);

    }


    @Override
    public void onStart() {
        super.onStart();


        Bundle args = getArguments();
        if (args !=null){

            int currentPosition = args.getInt("position");

            TextView descricaoTextView = (TextView) getActivity().findViewById(R.id.descricao_textview);
            final Hotel hotel = HoteisData.getHotels().get(currentPosition);
            descricaoTextView.setText(hotel.getDescription());

            TextView nameHotelTextView = (TextView) getActivity().findViewById(R.id.textViewName);
            nameHotelTextView.setText(hotel.getName());

            email = (ImageView) getActivity().findViewById(R.id.imageViewEmail);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "testee", Toast.LENGTH_SHORT).show();
                    Uri email = Uri.parse(hotel.getEmail());
                    Intent webIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                    webIntent.putExtra(Intent.EXTRA_EMAIL, email);
                    startActivity(webIntent);

                }
            });


            phone = (ImageView) getActivity().findViewById(R.id.imageViewPhone);
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "phone", Toast.LENGTH_SHORT).show();

                    Uri phone = Uri.parse(hotel.getPhoneNumber());

                    Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                    phoneIntent.setData(Uri.parse("tel:"+ phone));
                    phoneIntent.putExtra(Intent.EXTRA_EMAIL, phone);
                    startActivity(phoneIntent);

                }
            });


            gps = (ImageView) getActivity().findViewById(R.id.imageViewGps);
            gps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "" +hotel.getLocation(), Toast.LENGTH_LONG).show();

                    Uri gps = Uri.parse(hotel.getLocation());
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + hotel.getLocation()+ "("+hotel.getName()+")");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);




                }
            });

            imageHotel = (ImageView) getActivity().findViewById(R.id.imageViewHotel);


           Picasso.with(getContext()).load(hotel.getImage())
                 //  .resize(1080,1080)
               //    .resizeDimen(80,30)
                  // .centerInside()
                   .into(imageHotel);



        }
    }


}
