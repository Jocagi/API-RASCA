package com.rasca.rascaapi.services;

import com.rasca.rascaapi.domain.User;
import com.rasca.rascaapi.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

}
