package aliirawan.aquariumproject.core;

public class FishType {

	private String name;
	@SuppressWarnings("rawtypes")
	private Class clazz;
	
	@SuppressWarnings("rawtypes")
	public FishType(String name, Class clazz) {
		super();
		this.name = name;
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("rawtypes")
	public Class getClazz() {
		return clazz;
	}

	@SuppressWarnings("rawtypes")
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	
}
