package app.dao.interfaces;

import app.dto.GuestDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;

public interface PersonDao {
    public boolean existsByDocument(PersonDto personDto ) throws Exception;
    public void createPerson(PersonDto personDto ) throws Exception;
    public void deletePerson(PersonDto personDto ) throws Exception;
    public PersonDto findByDocument(PersonDto personDto ) throws Exception;

    public PersonDto findByUserId(UserDto userDto);

    public PersonDto findByPersonId(InvoiceDto invoiceDto);
 
}
