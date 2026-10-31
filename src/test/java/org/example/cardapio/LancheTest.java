package org.example.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LancheTest {

    @Test
    void deveRecusarPrecoNegativo(){
        assertThrows(IllegalArgumentException.class, () ->
                new Lanche("X-Burger", -10.00)
        );
    }
}