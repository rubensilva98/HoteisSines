package com.example.ruben.hoteissines.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruben on 03/01/2018.
 */

public class HoteisData {

    private static List<Hotel> hotels;

    public static List<Hotel> getHotels() {
        if (hotels == null) {
            hotels = new ArrayList<>();
            hotels.add(new Hotel("Hotel Apartamento Sinerama", "Este alojamento fica a 5 minutos a pé da praia. Localizado no centro de Sines, o Hotel Apartamento Sinerama dispõe de quartos e apartamentos auto-suficientes, alguns com vistas panorâmicas para o mar. O pequeno-almoço gratuito inclui sumos de frutas, ovos, bolo, pão e queijo.", "269 000 100", "mail", "37.956041 -8.869475"));
            hotels.add(new Hotel("Dom Vasco", "Escolher um Hotel não se resume à simples escolha de um quarto para dormir. A experiência de ficar, e viver o ambiente que o rodeia, vai muito além das quatro paredes. Logo, da excelência do serviço, à atenção dedicada a cada pormenor, o Hotel Dom Vasco eleva cada estadia a uma experiência única. Acima de tudo, partilhe este modo de estar", "269 630 960", "hotel@domvasco.com", "37.957798 -8.8753941" ));
            hotels.add(new Hotel("Hotel Veleiro","O Hotel Veleiro beneficia de uma localização privilegiada com vista para a Baía de Sines. Disponibiliza quartos com 1 cama de casal ou com 2 camas individuais, uma casa de banho privada e acesso Wi-Fi gratuito em todas as áreas. A Praia Vasco da Gama encontra-se a uma caminhada de 2 minutos.","269 634 751","veleiro@iol.pt","37.9547622 -8.8683794"));
        }

        return hotels;
    }
}
