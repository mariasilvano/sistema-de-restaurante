package org.example.cardapio;

import java.util.List;

public class LancheFactory {

    public static Lanche xBurger() {
        return new Lanche("X-Burger", 16);
    }

    public static Lanche xSalada() {
        return new Lanche("X-Salada", 18);
    }

    public static Lanche xBacon() {
        return new Lanche("X-Bacon", 22);
    }

    public static List<String> getIngredientes(int opcaoLanche) {
        switch (opcaoLanche) {
            case 1:
                return List.of( "queijo", "alface", "tomate");

            case 2:
                return List.of( "queijo", "alface", "tomate", "cebola");

            case 3:
                return List.of("queijo", "alface", "tomate", "cebola");

            default:
                throw new IllegalArgumentException("Opção de lanche inválida!");
        }
    }
}
