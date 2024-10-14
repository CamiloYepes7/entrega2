package app.controller;

import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.Service;
import app.service.interfaces.AdminService;

public  class AdminController implements ControllerInterface{
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private PartnerValidator partnerValidator;
    private AdminService service;
           private static final String MENU = "    private static final String MENU = \"ingrese la opcion que desea ejecutar: \\n 1. crear socio  \\n 2.ver historial de facturas \\n 3.ver historial facturas de socio \\n 4.ver historial facturas de invitado \\n 5.ejecutar promocion VIP \\n 6. cerrar sesion"  + "" ;

    public AdminController() {
        super();
        this.service = new Service();
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.partnerValidator = new PartnerValidator();
    }
    
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
            return this.options(option);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
		}
	}

    private boolean options(String option) throws Exception {
		switch (option) {
		case "1": {
			this.CreatePartner();
			return true;
		}
		case "2": {
			this.ClubInvoices();
			return true;
		}
		case "3": {
			this.PartnerInvoices();
			return true;
		}
		case "4": {
			this.GuestInvoices();
			return true;
		}
                case "5": {
			this.VipPromotion();
			return true;
                }
           
		case "6": {
			System.out.println("Se ha cerrado sesion");
			return false;
		}
		default: {
			System.out.println("ingrese una opcion valida");
			return true;
		}
                }
    }
                 private void ClubInvoices() throws Exception {
	}
        
       
        
        private void GuestInvoices() throws Exception {
        }
            private void VipPromotion() throws Exception {
	}
    
    private void CreatePartner() throws Exception{
        System.out.println("ingrese el nombre ");
        String name = Utils.getReader().nextLine();
	personValidator.validName(name);
	System.out.println("ingrese la cedula ");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
	System.out.println("ingrese el telefono celular");
	long cellPhone = personValidator.validCellphone(Utils.getReader().nextLine());
	System.out.println("ingrese el nombre de usuario ");
	String userName = Utils.getReader().nextLine();
	userValidator.validUserName(userName);
	System.out.println("ingrese la contraseña de");
	String password = Utils.getReader().nextLine();
	userValidator.validPassword(password);
        System.out.println("ingrese el monto inicial");  
        double amount = partnerValidator.validAmount(Utils.getReader().nextLine());
        
	PersonDto personDto = new PersonDto();
	personDto.setName(name);
	personDto.setDocument(document);
	personDto.setCelphone(cellPhone);
	UserDto userDto = new UserDto();
	userDto.setPersonId(personDto);
	userDto.setUserName(userName);
	userDto.setPassword(password);
	userDto.setRole("partner");          
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);
        partnerDto.setType(true);
        partnerDto.setAmount(amount);
        partnerDto.setCreationDate(Utils.getDate());
        this.service.createPartner(userDto);
	System.out.println("se ha creado el usuario exitosamente");
    }  

    private void PartnerInvoices() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
