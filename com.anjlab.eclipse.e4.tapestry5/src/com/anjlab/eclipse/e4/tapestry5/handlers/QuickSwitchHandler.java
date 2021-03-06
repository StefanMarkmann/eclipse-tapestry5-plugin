package com.anjlab.eclipse.e4.tapestry5.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.anjlab.eclipse.tapestry5.Activator;
import com.anjlab.eclipse.tapestry5.EclipseUtils;
import com.anjlab.eclipse.tapestry5.TapestryContext;
import com.anjlab.eclipse.tapestry5.TapestryUtils;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class QuickSwitchHandler extends AbstractHandler
{
    /**
     * The constructor.
     */
    public QuickSwitchHandler()
    {
    }

    /**
     * the command has been executed, so extract extract the needed information
     * from the application context.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        
        TapestryContext context = Activator.getDefault().getTapestryContext(window);
        
        if (context == null || context.isEmpty())
        {
            return null;
        }
        
        TapestryContextInformationControl informationControl =
                 new TapestryContextInformationControl(window, context);
        informationControl.setLocation(
                EclipseUtils.getPopupLocation(
                        window,
                        window.getActivePage().getActivePartReference(),
                        informationControl.computeSizeHint()));
        //  TODO When inside module class try to map current method to service instrumenter
        informationControl.setInput(TapestryUtils.getTapestryFileFromPage(window.getActivePage()));
        informationControl.open();
        informationControl.setFocus();
        
        return null;
    }
}
