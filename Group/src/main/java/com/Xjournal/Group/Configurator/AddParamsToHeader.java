package com.Xjournal.Group.Configurator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class AddParamsToHeader extends HttpServletRequestWrapper {
    public AddParamsToHeader(HttpServletRequest request) {
        super(request);
    }

    public String getHeader(String name) {
        if ("accept".equals(name)) {
            return null; //or any valid value
        }
        String header = super.getHeader(name);
        return (header != null) ? header : super.getParameter(name);
    }

    public Enumeration getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(Collections.list(super.getParameterNames()));
        return Collections.enumeration(names);
    }
}
