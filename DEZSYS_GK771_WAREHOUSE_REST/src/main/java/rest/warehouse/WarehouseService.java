package rest.warehouse;

import org.springframework.stereotype.Service;
import rest.model.Product;
import rest.model.WarehouseData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {

    public WarehouseData getWarehouseData(String warehouseID) {
        WarehouseData warehouse = new WarehouseData();

        warehouse.setWarehouseID(warehouseID);
        warehouse.setWarehouseName("Linz Bahnhof");
        warehouse.setWarehouseAddress("Bahnhofsstrasse 27/9");
        warehouse.setWarehousePostalCode("4020");
        warehouse.setWarehouseCity("Linz");
        warehouse.setWarehouseCountry("Austria");
        warehouse.setTimestamp(LocalDateTime.now().toString());

        List<Product> products = new ArrayList<>();
        products.add(new Product("00-443175", "Bio Orangensaft Sonne", "Getraenk", 2500, "Packung 1L"));
        products.add(new Product("00-871895", "Bio Apfelsaft Gold", "Getraenk", 3420, "Packung 1L"));
        products.add(new Product("01-926885", "Ariel Waschmittel Color", "Waschmittel", 478, "Packung 3KG"));
        products.add(new Product("00-316253", "Persil Discs Color", "Waschmittel", 1430, "Packung 700G"));

        warehouse.setProductData(products);
        return warehouse;
    }
}
