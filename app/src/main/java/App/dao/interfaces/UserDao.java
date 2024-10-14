
package app.dao.interfaces;

import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;


public interface UserDao {
    public UserDto findByUserName(UserDto userDto) throws Exception;
    public boolean existsByUserName(UserDto userDto) throws Exception;
    public void createUser(UserDto userDto) throws Exception;
    public UserDto findByPersonId( PersonDto personDto ) throws Exception;
    public UserDto findByUserId( PartnerDto partnerDto ) throws Exception;
    public UserDto findByGuestUserId( GuestDto guestDto ) throws Exception;   
    public void updatePasswordUser( UserDto userDto ) throws Exception;    
    public void updateRoleUser( UserDto userDto ) throws Exception;    
    public void deleteUser( UserDto userDto ) throws Exception;    
}
