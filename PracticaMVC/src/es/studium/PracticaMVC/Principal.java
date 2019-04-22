package es.studium.PracticaMVC;


	public class Principal {

		public static void main(String[] args) {
		
			ClaseModelo Model = new ClaseModelo();
			MenuPrincipal MenuPrincipal = new MenuPrincipal();
			new ClaseControlador(Model, MenuPrincipal);
		}


}
