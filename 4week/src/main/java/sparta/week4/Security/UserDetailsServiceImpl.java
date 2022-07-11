package sparta.week4.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sparta.week4.Entity.User;
import sparta.week4.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

private final UserRepository userRepository;

@Autowired
public UserDetailsServiceImpl(UserRepository userRepository) {
this.userRepository = userRepository;
}
//loadUserByUsername username을 가지고 유저를 갖고와라
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    userRepository(회원관리하는 리포지토리)를 가지고 유저를 가져와서 일치하면 findByUsername 가져와서 UserDetailsImpl(user)에 리턴
User user = userRepository.findByUsername(username)
//        일치하지않으면 UsernameNotFoundException을 사용
.orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

return new UserDetailsImpl(user);
}
}