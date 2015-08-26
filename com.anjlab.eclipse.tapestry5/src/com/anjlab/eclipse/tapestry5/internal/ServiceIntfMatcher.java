package com.anjlab.eclipse.tapestry5.internal;

import static com.anjlab.eclipse.tapestry5.TapestryUtils.getSimpleName;

import org.apache.commons.lang3.StringUtils;

import com.anjlab.eclipse.tapestry5.TapestryService;
import com.anjlab.eclipse.tapestry5.TapestryService.Matcher;

public class ServiceIntfMatcher implements Matcher
{
    private final String className;
    
    public ServiceIntfMatcher(String className)
    {
        this.className = className;
    }

    @Override
    public boolean matches(TapestryService service)
    {
        if (StringUtils.isEmpty(className))
        {
            return false;
        }
        
        if (StringUtils.isEmpty(service.getDefinition().getIntfClass()))
        {
            return false;
        }
        
        return StringUtils.equals(className, service.getDefinition().getIntfClass())
                || StringUtils.equals(getSimpleName(this.className),
                        getSimpleName(service.getDefinition().getIntfClass()));
    }

}
