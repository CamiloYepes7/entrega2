package app.helpers;

import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.model.Guest;
import app.model.Invoice;
import app.model.InvoiceDetail;
import app.model.Partner;
import app.model.Person;
import app.model.User;
import java.sql.Date;

public abstract interface Helper {
    public static PersonDto parse(Person person) {
	PersonDto personDto = new PersonDto();
	personDto.setId(person.getId());
	personDto.setDocument(person.getDocument());
	personDto.setName(person.getName());
	personDto.setCelphone(person.getCelphone());
	return personDto;
    }
	
    public static Person parse(PersonDto personDto) {
	Person person = new Person();
	person.setId(personDto.getId());
	person.setDocument(personDto.getDocument());
	person.setName(personDto.getName());
	person.setCelphone(personDto.getCelphone());
	return person;
    }
	
    public static UserDto parse(User user) {
	UserDto userDto = new UserDto();
	userDto.setId(user.getId());
	userDto.setUserName(user.getUserName());
	userDto.setPassword(user.getPassword());
	userDto.setPersonId(parse(user.getPersonId()));
	userDto.setRole(user.getRole());
	return userDto;
    }
	
    public static User parse(UserDto userDto) {
	User user = new User();
	user.setId(userDto.getId());
	user.setPassword(userDto.getPassword());
	user.setPersonId(parse(userDto.getPersonId()));
	user.setRole(userDto.getRole());
	user.setUserName(userDto.getUserName());
	return user;
    }
     public static Partner parse( PartnerDto partnerDto) {
        Partner partner = new Partner();
        partner.setId( partnerDto.getId() );
        partner.setUserId(partnerDto.getUserId());
        partner.setType( partnerDto.getType() );
        partner.setAmount( partnerDto.getAmount() );
        partner.setCreationDate( partnerDto.getCreationDate() );
        return partner;
    }
    
    public static PartnerDto parse( Partner partner ){
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setId( partner.getId() );
        partnerDto.setUserId( partner.getUserId() );
        partnerDto.setType( partner.getType() );
        partnerDto.setAmount( partner.getAmount() );
        partnerDto.setCreationDate((Date) partner.getCreationDate());
        return partnerDto;
    }

    public static Invoice parse( InvoiceDto invoiceDto ) {
        Invoice invoice = new Invoice();
        invoice.setId( invoiceDto.getId() );
        invoice.setPersonId( invoiceDto.getPersonId() );
        invoice.setPartnerId( invoiceDto.getPartnerId());
        invoice.setCreationDate( invoiceDto.getCreationDate() );
        invoice.setAmount( invoiceDto.getAmount() );
        invoice.setStatus( invoiceDto.getStatus() );
        return invoice;
    }
    
    public static InvoiceDto parse( Invoice invoice ){
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId( invoice.getId() );
        invoiceDto.setPersonId( invoice.getPersonId() );
        invoiceDto.setPartnerId( invoice.getPartnerId() );
        invoiceDto.setCreationDate( invoice.getCreationDate() );
        invoiceDto.setAmount( invoice.getAmount() );
        invoiceDto.setStatus( invoice.getStatus() );
        return invoiceDto;
    }

    public static InvoiceDetail parse( InvoiceDetailDto invoiceDetailDto ) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setId( invoiceDetailDto.getId() );
        invoiceDetail.setInvoiceId( invoiceDetailDto.getInvoiceId() );
        invoiceDetail.setItem( invoiceDetailDto.getItem() );
        invoiceDetail.setDescription( invoiceDetailDto.getDescription() );
        invoiceDetail.setAmount( invoiceDetailDto.getAmount() );
        return invoiceDetail;
    }
    
    public static InvoiceDetailDto parse( InvoiceDetail invoiceDetail ){
        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto
        invoiceDetailDto.setId(invoiceDetail.getId());
        invoiceDetailDto.setInvoiceId( invoiceDetail.getInvoiceId() );
        invoiceDetailDto.setItem( invoiceDetail.getItem() );
        invoiceDetailDto.setDescription( invoiceDetail.getDescription() );
        invoiceDetailDto.setAmount( invoiceDetail.getAmount() );
        return invoiceDetailDto;
    }

    public static Guest parse( GuestDto GuestDto ) {
        Guest guest = new Guest();
        guest.setId( GuestDto.getId() );
        guest.setUserId( GuestDto.getUserId() );
        guest.setPartnerId( GuestDto.getPartnerId() );
        guest.setStatus( GuestDto.getStatus() );
        return guest;
    }
    
    public static GuestDto parse( Guest guest ){
        GuestDto guestDto = new GuestDto();
        guestDto.setId( guest.getId() );
        guestDto.setUserId( guest.getUserId() );
        guestDto.setPartnerId( guest.getPartnerId() );
        guestDto.setStatus( guest.getStatus() );
        return guestDto;
    }

}

