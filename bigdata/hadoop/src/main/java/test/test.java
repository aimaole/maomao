package test;

public class test {

	public static void main(String[] args) {

		Integer i = 10;

		for (int j = 1; j < i; j++) {
			System.out.println("{\"id\":"+j+",\"label\":\"person\",\"inE\":{\"knows\":[{\"id\":7,\"outV\":1,\"properties\":{\"weight\":0.5}}]},\"properties\":{\"name\":[{\"id\":2,\"value\":\"vadas\"}],\"age\":[{\"id\":3,\"value\":27}]}}");
		}

	}

}
