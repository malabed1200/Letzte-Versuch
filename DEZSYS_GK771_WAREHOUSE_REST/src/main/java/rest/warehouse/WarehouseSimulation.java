package rest.warehouse;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class WarehouseSimulation {

	private final Random random = new Random();

	public int randomQuantity(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
}
