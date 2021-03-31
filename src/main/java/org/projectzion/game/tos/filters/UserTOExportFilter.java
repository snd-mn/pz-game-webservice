package org.projectzion.game.tos.filters;

import org.projectzion.game.tos.UserTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserTOExportFilter implements Converter<UserTO, UserTO> {
    @Override
    public UserTO convert(UserTO userTO) {
        userTO.setPassword(null);
        userTO.setRoles(null);
        return userTO;
    }
}
