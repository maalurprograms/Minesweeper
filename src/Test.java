
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Zelle zelle = new Zelle();
		Spielfeld spielfeld = new Spielfeld();
		if (zelle.classOk && spielfeld.classOk) {
			System.out.println("OK");
		} else {
			System.out.println(zelle.classOk + " " + spielfeld.classOk);
		}
	}
}
