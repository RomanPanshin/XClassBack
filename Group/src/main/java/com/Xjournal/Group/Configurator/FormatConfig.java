package com.Xjournal.Group.Configurator;

import com.Xjournal.Group.Converter.ArrayConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class FormatConfig extends WebMvcConfigurationSupport {
    @Override
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService f = super.mvcConversionService();
        f.addConverter(new ArrayConverter());
        return f;
    }
}