package app.dao.interfaces;

import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.UserDto;
import java.util.List;
import org.springframework.stereotype.Repository;


public interface GuestDao {
    public boolean existsByUserId(UserDto userDto) throws Exception;
    public void createGuest(GuestDto guestDto) throws Exception;
    public void deleteGuest(GuestDto guestDto) throws Exception;
    public void updateGuestStatus(GuestDto guestDto) throws Exception;
    public GuestDto findByUserId(UserDto userDto) throws Exception;
    public GuestDto findById(long guestId) throws Exception;
    public List<GuestDto> findActiveGuests() throws Exception;
    public GuestDto findByDocument(String document) throws Exception;
}


