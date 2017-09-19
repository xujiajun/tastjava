package cn.xujiajun.tastjava.rest.filter;

import cn.xujiajun.tastjava.provider.jwt.JWTProvider;
import org.pmw.tinylog.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;

import cn.xujiajun.tastjava.rest.exception.NotAuthorizedException;

@Provider
public class ResourceAuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        UriInfo info = containerRequestContext.getUriInfo();
        if (info.getPath().contains("user/login")) {
            return;
        }

        if (!isAuthTokenValid(containerRequestContext)) {
            throw new NotAuthorizedException("You Don't Have Permission");
        }

        return;

    }

    private boolean isAuthTokenValid(ContainerRequestContext containerRequestContext) {
        Map<String, Cookie> cookies = containerRequestContext.getCookies();

        if (cookies.get("jwt-authToken") != null) {
            String authToken = cookies.get("jwt-authToken").getValue();
            Logger.info(authToken);
            Integer uid = JWTProvider.verifyToken(authToken).getClaim("uid").asInt();
            Logger.info(uid);
            return true;
        }
        return false;
    }
}
