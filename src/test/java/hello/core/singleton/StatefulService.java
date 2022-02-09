package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;

public class StatefulService {

    private int price;

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {return price;}
}
