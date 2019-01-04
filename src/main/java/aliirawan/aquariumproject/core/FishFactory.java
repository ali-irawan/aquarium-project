package aliirawan.aquariumproject.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class FishFactory {

	private static FishFactory factory;

	private HashMap<String, FishType> map;

	private FishType[] typeList;

	private FishFactory() {
		map = new HashMap<String, FishType>();
		
		registerFishTypesFromIndex();
	}

	private void registerFishTypesFromIndex() {

		BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/index")));

		String input;
		try {
			input = reader.readLine();
			while(input!=null) {
				String[] tokens = input.split(",");
				registerFishType(tokens[0], new FishType(tokens[1], Class.forName(tokens[2])));
				input = reader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static FishFactory getInstance() {
		if (factory == null) {
			factory = new FishFactory();
		}
		return factory;
	}

	public void registerFishType(String fishTypeName, FishType clazz) {
		map.put(fishTypeName, clazz);
		typeList = null;
	}

	public FishType[] getFishTypeList() {
		if (typeList == null) {
			typeList = new FishType[map.size()];

			Iterator<String> it = map.keySet().iterator();
			int index = 0;
			while (it.hasNext()) {
				typeList[index] = map.get(it.next());
				index++;
			}
		}
		return typeList;
	}
}
