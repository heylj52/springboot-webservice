package com.heylj.book.config.auth;

import com.heylj.book.config.auth.dto.SessionUser;
import com.heylj.book.domain.user.Users;
import com.heylj.book.domain.user.UserRepository;
import com.heylj.book.config.auth.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
@Slf4j

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 구글 or 네이버 or ..
        String userNameAttributeName = userRequest.getClientRegistration()
                    .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // 로그인의 키가 되는 필드값

        //OAuth2User의 속성값을 담는 클래스
        OAuthAttributes attributes =
                OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Users user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user)); //직렬화기능을 가진 SessionUser 클래스 새로 생성

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private Users saveOrUpdate(OAuthAttributes attributes) {
        log.info(attributes.getEmail());
        Users user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}