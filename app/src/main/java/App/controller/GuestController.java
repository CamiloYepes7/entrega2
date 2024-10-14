
package app.controller;

import app.controller.validator.GuestValidator;
import app.controller.validator.PersonValidator;

public class GuestController implements ControllerInterface {
private PersonValidator personValidator;
    private GuestValidator GuestValidator;
        private static final String MENU = "    private static final String MENU = \"ingrese la opcion que desea ejecutar: \\n 1. crear factura  \\n 2.convertirse en socio  \\n 3.cerrar session"  +
"" ;
@Override
	public void session() throws Exception {
		boolean session = true;
		while (session) {
			session = menu();
		}

	}

	private boolean menu() {
		try {
			System.out.println(MENU);
			String option = Utils.getReader().nextLine();
			return options(option);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return true;
		}
	}

	private boolean options(String option) throws Exception {
		switch (option) {
		case "1": {
			this.CreateInvoice();
			return true;
		}
		case "2": {
			this.BecomePartner();
			return true;
		}
		case "3": {
			System.out.println("Se ha cerrado sesion");
			return false;
		}
		default: {
			System.out.println("ingrese una opcion valida");
			return true;
		}
		}
               
        }

         private void CreateInvoice() throws Exception {
        System.out.println("Ingrese el monto de la factura:");
        double amount = Utils.getReader().nextDouble();
        System.out.println("Ingrese el concepto de la factura:");
        String concept = Utils.getReader().nextLine();

      
        if (amount <= 0) {
            throw new Exception("El monto debe ser mayor que 0");
        }

        

        System.out.println("Factura creada con éxito. Monto: " + amount + ", Concepto: " + concept);
    }

    private void BecomePartner() {
        System.out.println("asendio a socio:");
    }
}
   

    
        
        
	