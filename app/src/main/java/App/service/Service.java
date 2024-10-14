package app.service;

import App.dao.interfaces.InvoiceDetailDao;
import app.controller.Utils;
import app.dao.PersonDaoImplementation;
import app.dao.UserDaoImplementation;
import app.dao.interfaces.GuestDao;
import app.dao.interfaces.InvoiceDao;
import app.dao.interfaces.PartnerDao;
import app.dao.interfaces.PersonDao;
import app.dao.interfaces.UserDao;
import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.interfaces.AdminService;
import app.service.interfaces.LoginService;
import app.service.interfaces.PartnerService;
import java.util.ArrayList;

public class Service implements LoginService, AdminService, PartnerService {

    private UserDao userDao;
    private PersonDao personDao;
    private PartnerDao partnerDao;
    private GuestDao guestDao;
    private InvoiceDao invoiceDao;
    private InvoiceDetailDao invoiceDetailDao;
   
    public static UserDto user;
    private Object userService;
    
    public Service(){
        this.userDao = new UserDaoImplementation();
        this.personDao = new PersonDaoImplementation();
        this.partnerDao =  (PartnerDao) new PersonDaoImplementation();
    }
    
    
    public void login(UserDto userDto) throws Exception {
        UserDto validateDto = userDao.findByUserName(userDto);
        if (validateDto == null) {
            throw new Exception("no existe usuario registrado");
        }
	if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("usuario o contraseña incorrecto");
	}
	userDto.setRole(validateDto.getRole());
	user = validateDto;
    }

    @Override
    public void logout() {
        user = null;
        System.out.println("Se ha cerrado session");
    }

    @Override
    public void createPartner(UserDto userDto) throws Exception {
        this.createUser(userDto);
    }

    @Override
    public void createGuest(UserDto userDto) throws Exception {
        this.createUser(userDto);
    }
    
    private void createUser(UserDto userDto) throws Exception{
        this.createPerson(userDto.getPersonId());
        userDto.setPersonId(personDao.findByDocument(userDto.getPersonId()));
	if(this.userDao.existsByUserName(userDto)) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("ya existe un usuario con ese user name");
            }
            this.userDao.createUser(userDto);
	}
    private void createPerson(PersonDto personDto)throws Exception{
	if(this.personDao.existsByDocument(personDto)) {
        throw new Exception("ya existe una persona con ese documento");
	}
        this.personDao.createPerson(personDto);
    }

  
    @Override
    public void promotiontovip(PersonDto personDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void desactivateGuest(long document) throws Exception {
        GuestDto guestDto = new GuestDto();
        guestDto.setStatus(false);
    }

public void createPartner( ) throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument( personDtoLocale );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }

        UserDto userDtoLocate = this.userDao.findByPersonId( personDtoLocale );
        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDtoLocate );
        if ( partnerDto != null ){
            throw new Exception( personDtoLocale.getName() + " ya es SOCIO del club");
        }
        
        partnerDto.getPartnerAmountDto();

        this.partnerDao.createPartner( partnerDto );
    }

public void deletePartner( ) throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument( personDtoLocale );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }
        
        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices( personDtoLocale );
        if ( amountActiveInvoices > 0 ){
            throw new Exception( personDtoLocale.getName() + " tiene facturas pendientes de pago");
        }
        
        UserDto userDtoLocate = this.userDao.findByPersonId( personDtoLocale );
        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDtoLocate );
        
        if ( partnerDto == null ){
            throw new Exception( "No existe el socio");                            
        }
        
        InvoiceDto invoiceDto = this.invoiceDao.firstInvoiceByPartnerId( partnerDto );
        while ( invoiceDto != null ){
            this.invoiceDetailDao.deleteInvoiceDetail( invoiceDto );
            this.invoiceDao.deleteInvoice( invoiceDto );
            invoiceDto = this.invoiceDao.firstInvoiceByPartnerId( partnerDto );
        }

        this.partnerDao.deletePartner( partnerDto );
}

  public void createGuest(UserDto userDto) throws Exception {
        // Verificar si el usuario ya es invitado
        if (guestDao.findByUserId(userDto) != null) {
            throw new Exception("El usuario ya es un invitado");
        }

        PersonDto personDto = this.personDao.findByUserId(userDto);
        if (personDto == null) {
            throw new Exception("No se encontró la persona asociada al usuario");
        }

        GuestDto guestDto = new GuestDto();
        guestDto.setUserId(userDto.getId());
        guestDto.setPartnerId(personDto.getId());
        guestDto.setStatus("ACTIVO");

        this.guestDao.createGuest(guestDto);
    }

   public void deleteGuest() throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument(personDtoLocale);

        if (personDtoLocale == null) {
            throw new Exception("No existe la persona");
        }

        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices(personDtoLocale);
        if (amountActiveInvoices > 0) {
            throw new Exception(personDtoLocale.getName() + " tiene facturas pendientes de pago");
        }

        UserDto userDtoLocate = this.userDao.findByPersonId(personDtoLocale);
        if (userDtoLocate == null) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");
        }

        GuestDto guestDto = this.guestDao.findByUserId(userDtoLocate);
        if (guestDto == null) {
            throw new Exception("No existe el invitado");
        }
   }
        public void BecomeApartner(UserDto userDto) throws Exception {
        PersonDto personDtoLocale = this.personDao.findByUserId(userDto);

        if (personDtoLocale == null) {
            throw new Exception("No existe la persona");
        }

        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices(personDtoLocale);
        if (amountActiveInvoices > 0) {
            throw new Exception(personDtoLocale.getName() + " tiene facturas pendientes de pago");
        }

        GuestDto guestDto = this.guestDao.findByUserId(userDto);
        if (guestDto == null) {
            throw new Exception(personDtoLocale.getName() + " no es un invitado");
        }

        PartnerDto partnerDto = this.partnerDao.findByUserId(userDto);
        if (partnerDto != null) {
            throw new Exception(personDtoLocale.getName() + " ya es SOCIO del club");
        }

        partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto.getId());
        partnerDto.getPartnerTypeDto(); 

        if (partnerDto.getType().equals("VIP")) {
            long numberVIP = this.partnerDao.numberPertnersVIP();
            if (numberVIP >= 5) {
                throw new Exception("Cupo de socios VIP copado");
            }
        }

        partnerDto.getPartnerAmountDto(); 

        userDto.setRole("SOCIO");

        
        InvoiceDto invoiceDto = this.invoiceDao.firstInvoiceByPersonId(personDtoLocale);
        while (invoiceDto != null) {
            this.invoiceDetailDao.deleteInvoiceDetail(invoiceDto);
            this.invoiceDao.deleteInvoice(invoiceDto);
            invoiceDto = this.invoiceDao.firstInvoiceByPersonId(personDtoLocale);
        }

        this.guestDao.deleteGuest(guestDto); 
        this.partnerDao.createPartner(partnerDto); 
        this.userDao.updateRoleUser(userDto); 
    }
        public void createInvoice() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        
        if ( !this.personDao.existsByDocument( personDto ) ) {
            throw new Exception( "No existe ninguna persona con el documento: " + personDto.getDocument() );
        }
        
        personDto = this.personDao.findByDocument( personDto );
        
        UserDto userDto = this.userDao.findByPersonId( personDto ) ;
        
        if ( userDto == null ){
            throw new Exception( personDto.getName() + " no tiene USUARIO " );
        }
        
        InvoiceDto invoiceDto = new InvoiceDto();
        PartnerDto partnerDto = new PartnerDto();
        
        if ( userDto.getRole().equals( "SOCIO" ) ){
            partnerDto = this.partnerDao.findByUserId( userDto );
            if ( partnerDto == null ){
                throw new Exception( personDto.getName() + " no es SOCIO " );                
            }
            invoiceDto.setPersonId( personDto.getId() );
            invoiceDto.setPartnerId( partnerDto.getId() );
        }
        else{
            GuestDto guestDto = this.guestDao.findByUserId( userDto );
            if ( guestDto == null ){
                throw new Exception( personDto.getName() + " no es INVITADO " );                
            }
            partnerDto = this.partnerDao.findByGuestPartnerId( guestDto );
            invoiceDto.setPersonId( personDto.getId() );
            invoiceDto.setPartnerId( partnerDto.getId() );
        }
        
        this.invoiceDao.createInvoice( invoiceDto );
        invoiceDto.setId( this.invoiceDao.lastInvoiceByPersonId( personDto ));

        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setInvoiceId( invoiceDto.getId() );
        
        boolean continueRead = true;
        while ( continueRead ){
            invoiceDetailDto.getDescription();
            invoiceDetailDto.getInvoiceDetailAmountDto();
            invoiceDetailDto.setItemNumber( this.invoiceDetailDao.countInvoiceDetails( invoiceDto ) );
            this.invoiceDetailDao.createInvoiceDetail( invoiceDetailDto );
            
            System.out.println("1. Para más detalles");
            String continueReadConsole = Utils.getReader().nextLine();
            if ( continueReadConsole.equals( "1" ) ){
                continueRead = true;
            }
            else{
                continueRead = false;                
            }
        }
        invoiceDto.setAmount( this.invoiceDetailDao.totalInvoiceDetails( invoiceDto ) );
        this.invoiceDao.updateInvoiceAmount( invoiceDto );
        if ( partnerDto.getAmount() >= invoiceDto.getAmount() ){
            this.invoiceDao.cancelInvoice(invoiceDto);
            invoiceDto.setAmount( partnerDto.getAmount() - invoiceDto.getAmount() );
            this.partnerDao.updateAmountPartner(partnerDto);
        }
    }

    @Override
    public void createPartnerInvoice( PartnerDto partnerDto ) throws Exception {
        UserDto userDto = this.userDao.findByUserId( partnerDto );
        PersonDto personDto = this.personDao.findByUserId( userDto );
        InvoiceDto invoiceDto = new InvoiceDto();

        invoiceDto.setPersonId( personDto.getId() );
        invoiceDto.setPartnerId( partnerDto.getId() );
        
        this.invoiceDao.createInvoice( invoiceDto );
        invoiceDto.setId( this.invoiceDao.lastInvoiceByPersonId( personDto ));

        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setInvoiceId( invoiceDto.getId() );
        
        boolean continueRead = true;
        while ( continueRead ){
            invoiceDetailDto.getDescription();
            invoiceDetailDto.getInvoiceDetailAmountDto();
            invoiceDetailDto.setItemNumber( this.invoiceDetailDao.countInvoiceDetails( invoiceDto ) );
            this.invoiceDetailDao.createInvoiceDetail( invoiceDetailDto );
            
            System.out.println("1. Para más detalles");
            String continueReadConsole = Utils.getReader().nextLine();
            if ( continueReadConsole.equals( "1" ) ){
                continueRead = true;
            }
            else{
                continueRead = false;                
            }
        }
        invoiceDto.setAmount( this.invoiceDetailDao.totalInvoiceDetails( invoiceDto ) );
        this.invoiceDao.updateInvoiceAmount( invoiceDto );
        if ( partnerDto.getAmount() >= invoiceDto.getAmount() ){
            this.invoiceDao.cancelInvoice(invoiceDto);
            invoiceDto.setAmount( partnerDto.getAmount() - invoiceDto.getAmount() );
            this.partnerDao.updateAmountPartner(partnerDto);
        }
    }

    public void createGuestInvoice( GuestDto guestDto ) throws Exception {
        UserDto userDto = this.userDao.findByGuestUserId( guestDto );
        PersonDto personDto = this.personDao.findByUserId( userDto );
        InvoiceDto invoiceDto = new InvoiceDto();
        PartnerDto partnerDto = this.partnerDao.findByGuestPartnerId( guestDto );
        invoiceDto.setPersonId( personDto.getId( ) );
        invoiceDto.setPartnerId( partnerDto.getId() );

        this.invoiceDao.createInvoice( invoiceDto );
        invoiceDto.setId( this.invoiceDao.lastInvoiceByPersonId( personDto ));

        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setInvoiceId( invoiceDto.getId() );

        boolean continueRead = true;
        while ( continueRead ){
            invoiceDetailDto.getDescription();
            invoiceDetailDto.getInvoiceDetailAmountDto();
            invoiceDetailDto.setItemNumber( this.invoiceDetailDao.countInvoiceDetails( invoiceDto ) );
            this.invoiceDetailDao.createInvoiceDetail( invoiceDetailDto );
            
            System.out.println("1. Para más detalles");
            String continueReadConsole = Utils.getReader().nextLine();
            if ( continueReadConsole.equals( "1" ) ){
                continueRead = true;
            }
            else{
                continueRead = false;                
            }
        }
        invoiceDto.setAmount( this.invoiceDetailDao.totalInvoiceDetails( invoiceDto ) );
        this.invoiceDao.updateInvoiceAmount( invoiceDto );
        if ( partnerDto.getAmount() >= invoiceDto.getAmount() ){
            this.invoiceDao.cancelInvoice(invoiceDto);
            invoiceDto.setAmount( partnerDto.getAmount() - invoiceDto.getAmount() );
            this.partnerDao.updateAmountPartner(partnerDto);
        }
    }
    public void historyInvoice() throws Exception {
        ArrayList<InvoiceDto> listInvoices = this.invoiceDao.listClubInvoices();
        if ( listInvoices.isEmpty() ){
            throw new Exception( "No hay historial de facturación" );                
        }
        for ( int i=0; i < listInvoices.size(); i++){
            InvoiceDto invoiceDto = listInvoices.get( i );
            PersonDto personDto = this.personDao.findByPersonId( invoiceDto );
            PartnerDto partnerDto = this.partnerDao.findByPartnerId( invoiceDto );
            UserDto userDto = this.userDao.findByUserId( partnerDto );
            PersonDto personPartnerDto = this.personDao.findByUserId( userDto );
            System.out.println( "Responsable: " + personDto.getName() + ", Socio; " + personPartnerDto.getName()  + ", Fecha: " + invoiceDto.getCreationDate() + ", Monto: " + invoiceDto.getAmount() + ", Estado: " + invoiceDto.getStatus() );
            
            
        }
        
    }
    }




    