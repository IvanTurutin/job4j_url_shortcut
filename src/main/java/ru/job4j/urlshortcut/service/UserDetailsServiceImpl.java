package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SiteRepository sites;

    @Override
    public UserDetails loadUserByUsername(String siteName) throws UsernameNotFoundException {
        Optional<Site> user = sites.findByLogin(siteName);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(siteName);
        }
        return new User(user.get().getLogin(), user.get().getPassword(), emptyList());
    }
}
