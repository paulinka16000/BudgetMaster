package pl.coderslab.budgetmaster.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.aspectj.lang.annotation.Before;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import pl.coderslab.budgetmaster.users.*;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {
    private static final Logger log= LoggerFactory.getLogger(UserServiceTest.class);
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test

    public void return_user_by_id() {
        //given
      final Long id=1L;
      User user=new User();
      user.setId(id);
      user.setUserFirstName("Paulina");
        UserDTO userDTO=new UserDTO();
        userDTO.setId(id);
        userDTO.setUserFirstName("Paulina");

        //when
        when (userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);
        UserDTO result=userService.getUserById(id);

        //then
        assertNotNull(result);
        assertEquals(userDTO, result);
    }

}
